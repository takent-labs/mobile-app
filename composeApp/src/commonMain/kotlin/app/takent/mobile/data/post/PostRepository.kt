package app.takent.mobile.data.post

import app.takent.mobile.data.network.KtorClient
import app.takent.mobile.data.post.model.PostResponseDTO
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

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
}