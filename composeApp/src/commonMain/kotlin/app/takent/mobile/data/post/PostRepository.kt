package app.takent.mobile.data.post

import app.takent.mobile.data.network.KtorClient
import app.takent.mobile.data.post.model.CreatePostDTO
import app.takent.mobile.data.post.model.PostResponseDTO
import app.takent.mobile.data.post.model.PresignedUrlResponseDTO
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.url
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlin.time.Clock

class PostRepository {
    private val client = KtorClient.httpClient

    //Ocultar en prod y en dev en android usar 10.0.2.2 en vez de localhost.
    private val baseUrl = "http://localhost:3001/api/v1"

    suspend fun getAllPosts(): Result<List<PostResponseDTO>> {
        return try {
            val response: List<PostResponseDTO> = client.get("$baseUrl/posts") {
                contentType(ContentType.Application.Json)
            }.body()

            return Result.success(response)
        } catch (e: Exception) {
            Result.failure(Exception(e))
        }
    }

    suspend fun createPost(content: String, imageBytes: ByteArray?, mimeType: String? = "image/png"): Result<Unit> {
        return try {
            var finalImagePath: String? = null

            if (imageBytes != null && mimeType != null) {
                println("DEBUG: Iniciando subida. Tamaño imagen: ${imageBytes.size} bytes")

                val extension = if (mimeType == "image/png") "png" else "jpg"
                val fileName = "image_${Clock.System.now().toEpochMilliseconds()}.$extension"

                val presignedResponse = client.post("$baseUrl/posts/upload-url") {
                    contentType(ContentType.Application.Json)
                    setBody(mapOf(
                        "fileName" to fileName,
                        "contentType" to mimeType
                    ))
                }

                if (!presignedResponse.status.isSuccess()) {
                    val errorBody = presignedResponse.body<String>()
                    return Result.failure(Exception("Paso 1 falló: ${presignedResponse.status} - $errorBody"))
                }

                val presignedData: PresignedUrlResponseDTO = presignedResponse.body()
                println("DEBUG: URL firmada obtenida: ${presignedData.uploadUrl}")

                val uploadResponse = io.ktor.client.HttpClient().use { tempClient ->
                    val urlString = presignedData.uploadUrl

                    tempClient.put(urlString) {
                        // 1. Limpiamos headers automáticos
                        headers.clear()

                        // 2. Enviamos el Content-Type (aunque no esté firmado, es buena práctica para R2)
                        header(HttpHeaders.ContentType, mimeType)

                        // 3. Este valor es obligatorio en R2 si no firmas el payload
                        header("x-amz-content-sha256", "UNSIGNED-PAYLOAD")

                        // 4. Metemos el cuerpo directamente
                        setBody(imageBytes)
                    }
                }

                if (!uploadResponse.status.isSuccess()) {
                    val errorText = uploadResponse.body<String>()
                    println("DEBUG R2 XML: $errorText")
                    return Result.failure(Exception("Error R2 detallado: $errorText"))
                }

                if (uploadResponse.status.isSuccess()) {
                    finalImagePath = presignedData.fileName
                    println("DEBUG: Imagen subida correctamente a R2.")
                } else {
                    return Result.failure(Exception("Paso 2 falló (R2): ${uploadResponse.status}"))
                }
            }

            println("DEBUG: Creando post en la base de datos...")
            val createPostDto = CreatePostDTO(
                content = content,
                imageUrl = finalImagePath,
                hashtags = extractHashtags(content)
            )

            val response = client.post("$baseUrl/posts") {
                contentType(ContentType.Application.Json)
                setBody(createPostDto)
            }

            if (response.status.isSuccess()) {
                println("DEBUG: Post creado exitosamente")
                Result.success(Unit)
            } else {
                val finalError = response.body<String>()
                Result.failure(Exception("Paso 3 falló: ${response.status} - $finalError"))
            }

        } catch (e: Exception) {
            println("DEBUG: Error crítico: ${e.message}")
            Result.failure(e)
        }
    }

    private fun extractHashtags(content: String): List<String> {
        return Regex("#(\\w+)").findAll(content).map { it.groupValues[1] }.toList()
    }
}