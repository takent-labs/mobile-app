package app.takent.mobile.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    data object Home : NavigationItem(
        route = Screen.Feed.route,
        icon = Icons.Default.Home,
        label = "Inicio"
    )
    
    data object Profile : NavigationItem(
        route = Screen.Profile.route,
        icon = Icons.Default.Person,
        label = "Perfil"
    )
}