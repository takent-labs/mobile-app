package app.takent.mobile.data.post.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdatePostDTO(
    val postId: String,
    val content: String? = null,
    val imageUrl: String? = null,
    val hashtags: List<String>? = null
)