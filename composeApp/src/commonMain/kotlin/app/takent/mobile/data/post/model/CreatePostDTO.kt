package app.takent.mobile.data.post.model

import kotlinx.serialization.Serializable

@Serializable
data class CreatePostDTO(
    val content: String,
    val imageUrl: String? = null,
    val hashtags: List<String> = emptyList()
)