package app.takent.mobile.ui.navigation

sealed class Screen(val route: String) {
    data object Welcome : Screen("Welcome")
    data object Splash : Screen("Splash")
    data object SignIn : Screen("SignIn")
    data object SignUp : Screen("SignUp")
    data object Home : Screen("Home")
    data object Profile : Screen("Profile")
}