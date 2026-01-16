package app.takent.mobile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.takent.mobile.ui.auth.SignIn.SignInScreen
import app.takent.mobile.ui.auth.SignUp.SignUpScreen
import app.takent.mobile.ui.theme.TakentTheme

@Composable
@Preview
fun App() {
    TakentTheme {
        val navHostController = rememberNavController()

        NavHost(
            navController = navHostController,
            startDestination = Screen.SignIn.route,
        ) {
            composable(route = Screen.SignIn.route) {
                SignInScreen(
                    onNavigateToSignUp = {
                        navHostController.navigate(Screen.SignUp.route)
                    }
                )
            }

            composable(route = Screen.SignUp.route) {
                SignUpScreen(
                    onBack = {
                        navHostController.popBackStack()
                    },
                )
            }
        }
    }
}