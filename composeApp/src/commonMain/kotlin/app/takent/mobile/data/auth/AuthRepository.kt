package app.takent.mobile.data.auth

import app.takent.mobile.data.auth.model.AuthResponse
import app.takent.mobile.data.auth.model.SignInDTO
import app.takent.mobile.data.auth.model.SignUpDTO
import app.takent.mobile.data.network.KtorClient
import com.russhwolf.settings.Settings
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthRepository {
    private val client = KtorClient.httpClient
    private val settings = Settings()

    //Lo tengo así solo para desarrollo, en producción hay que ocultar la URL
    private val baseUrl = "http://localhost:3001/api/v1"

    suspend fun signIn(dto: SignInDTO): Result<AuthResponse> {
        return try {
            val response: AuthResponse = client.post("$baseUrl/auth/sign-in") {
                contentType(ContentType.Application.Json)
                setBody(dto)
            }.body()

            saveSession(response)

            return Result.success(response)
        } catch (e: Exception) {
            Result.failure(Exception(e))
        }
    }

    suspend fun signUp(dto: SignUpDTO): Result<AuthResponse> {
        return try {
            val response: AuthResponse = client.post("$baseUrl/auth/sign-up") {
                contentType(ContentType.Application.Json)
                setBody(dto)
            }.body()

            saveSession(response)

            return Result.success(response)
        } catch (e: Exception) {
            Result.failure(Exception(e))
        }
    }

    private fun saveSession(response: AuthResponse) {
        settings.putString("token", response.accessToken)
        settings.putString("user_id", response.user.id)
        settings.putString("username", response.user.username)
        settings.putString("user_email", response.user.email)
    }

    fun getToken(): String? = settings.getStringOrNull("token")

    fun logout() {
        settings.clear()
    }
}