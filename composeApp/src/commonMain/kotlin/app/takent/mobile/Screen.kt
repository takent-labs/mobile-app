package app.takent.mobile

sealed class Screen(val route: String) {
    data object SignIn : Screen("SignIn")
    data object SignUp : Screen("SignUp")
}