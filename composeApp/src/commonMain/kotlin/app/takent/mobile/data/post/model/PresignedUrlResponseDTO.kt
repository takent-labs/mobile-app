package app.takent.mobile.data.post.model

import kotlinx.serialization.Serializable

@Serializable
data class PresignedUrlResponseDTO(
    val uploadUrl: String,
    val fileName: String
)