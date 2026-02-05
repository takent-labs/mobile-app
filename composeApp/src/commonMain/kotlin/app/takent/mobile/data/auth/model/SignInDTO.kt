package app.takent.mobile.data.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class SignInDTO(
    val email: String,
    val password: String,
)