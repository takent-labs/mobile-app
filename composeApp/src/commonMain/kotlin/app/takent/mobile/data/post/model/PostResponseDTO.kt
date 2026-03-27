package app.takent.mobile.data.post.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val id: String,
    val username: String,
    val imageUrl: String? = null
)

@Serializable
data class PostResponseDTO(
    val id: String,
    val userId: String,
    val content: String,
    val imageUrl: String? = null,
    val createdAt: String,
    val updatedAt: String,
    val user: UserDTO,
    val hashtags: List<String> = emptyList()
)