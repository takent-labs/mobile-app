package app.takent.mobile.data.post.model

import kotlinx.serialization.Serializable

@Serializable
data class DeletePostDTO(
    val postId: String
)