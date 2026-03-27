package app.takent.mobile.ui.feed.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.takent.mobile.data.post.model.PostResponseDTO
import app.takent.mobile.data.post.model.UserDTO
import app.takent.mobile.ui.theme.TakentTheme

@Preview
@Composable
fun PostItemPreview() {
    TakentTheme {
        PostItem(
            post = PostResponseDTO(
                id = "1",
                userId = "123",
                content = "¡Esto es una previsualización sin emulador de un post realista de como se vería en la app ! 🚀",
                createdAt = "2026-02-21T18:00:00Z",
                updatedAt = "2026-02-21T18:00:00Z",
                user = UserDTO(
                    id = "123",
                    username = "oskirove",
                    imageUrl = null
                ),
                hashtags = listOf("Compose", "Kotlin", "otro", "otro", "otro")
            )
        )
    }
}