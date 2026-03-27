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

    var isCreatingPost by mutableStateOf(false)
        private set

    var isRefreshing by mutableStateOf(false)
        private set

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            isLoading = true
            repository.getAllPosts()
                .onSuccess { posts = it }
                .onFailure { /* Gestionar el error con un snackbar mas adelante*/ }
            isLoading = false
        }
    }

    fun refresh() {
        viewModelScope.launch {
            isRefreshing = true
            repository.getAllPosts()
                .onSuccess { posts = it }
                .onFailure { /* Mas de lo mismo */ }

            isRefreshing = false
        }
    }

    fun createPost(content: String, imageBytes: ByteArray?, onUploadSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                isCreatingPost = true
                val result = repository.createPost(content, imageBytes)

                result.onSuccess {
                    onUploadSuccess()
                    refresh()
                }.onFailure { error ->
                    println("DEBUG VIEWMODEL: Falló el repositorio: ${error.message}")
                }
            } catch (e: Exception) {
                println("DEBUG VIEWMODEL: Error fatal en la corrutina: ${e.message}")
            } finally {
                isCreatingPost = false
            }
        }
    }
}