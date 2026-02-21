package app.takent.mobile.ui.feed

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.takent.mobile.data.post.PostRepository
import app.takent.mobile.data.post.model.PostResponseDTO
import kotlinx.coroutines.launch

class FeedScreenViewModel(
    private val repository: PostRepository = PostRepository()
) : ViewModel() {

    var posts by mutableStateOf<List<PostResponseDTO>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            isLoading = true
            repository.getAllPosts()
                .onSuccess { posts = it }
            isLoading = false
        }
    }
}