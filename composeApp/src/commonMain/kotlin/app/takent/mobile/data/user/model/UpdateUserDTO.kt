package app.takent.mobile.data.user.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDto(
    val username: String? = null,
    val email: String? = null,
    val password: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val imageUrl: String? = null
)