package app.takent.mobile.data.auth.model

data class SignUpDTO(
    val username: String,
    val email: String,
    val password: String,
    val firstName: String? = null,
    val lastName: String? = null,
)