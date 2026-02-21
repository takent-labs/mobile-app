package app.takent.mobile.ui.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.takent.mobile.data.post.model.PostResponseDTO
import app.takent.mobile.ui.feed.components.PostItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen (
    viewModel: FeedScreenViewModel,
    posts: List<PostResponseDTO>
) {

    val posts = viewModel.posts
    val isLoading = viewModel.isLoading

    Scaffold(
        topBar = { TopAppBar(title = { Text("Takent") }) }
    ) { paddingValues ->
        if (isLoading && posts.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(posts, key = { it.id }) { post ->
                    PostItem(post = post)
                }
            }
        }
    }
}