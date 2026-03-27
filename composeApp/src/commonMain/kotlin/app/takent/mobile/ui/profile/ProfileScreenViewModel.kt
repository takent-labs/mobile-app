package app.takent.mobile.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.takent.mobile.data.post.model.PostResponseDTO
import app.takent.mobile.data.post.model.UserDTO
import kotlinx.coroutines.launch

data class ProfileUiState(
    val isLoading: Boolean = false,
    val user: UserDTO? = null,
    val posts: List<PostResponseDTO> = emptyList(),
    val followersCount: Int = 0,
    val followingCount: Int = 0,
    val likesCount: Int = 0,
    val bio: String = "",
    val error: String? = null
)

class ProfileScreenViewModel : ViewModel() {
    var uiState by mutableStateOf(ProfileUiState())
        private set

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            // Simulación de carga de datos para la UI siguiendo el patrón MVVM
            uiState = uiState.copy(
                isLoading = false,
                user = UserDTO(id = "1", username = "oscar_dev", imageUrl = null),
                followersCount = 1250,
                followingCount = 450,
                likesCount = 8900,
                bio = "Android Developer | KMP Enthusiast \uD83D\uDE80\nBuilding the future of mobile apps.",
                posts = emptyList() 
            )
        }
    }
}