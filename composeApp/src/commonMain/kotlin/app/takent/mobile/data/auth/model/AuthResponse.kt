package app.takent.mobile.data.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class UserPayload(
    val id: String,
    val username: String,
    val email: String,
)

@Serializable
data class AuthResponse(
    val accessToken: String,
    val user: UserPayload
)
