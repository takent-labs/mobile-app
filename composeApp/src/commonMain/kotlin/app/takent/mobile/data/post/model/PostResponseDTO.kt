package app.takent.mobile.data.post.model

import kotlinx.serialization.Serializable

@Serializable
data class PostResponseDTO(
    val id: String,
    val userId: String,
    val content: String,
    val hashtags: List<String> = emptyList(),
    val imageUrl: String? = null,
    val createdAt: String,
    val updatedAt: String
)