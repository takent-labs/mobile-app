package app.takent.mobile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.takent.mobile.ui.auth.SignIn.SignInScreen
import app.takent.mobile.ui.auth.SignIn.SignInViewModel
import app.takent.mobile.ui.auth.SignUp.SignUpScreen
import app.takent.mobile.ui.auth.SignUp.SignUpViewModel
import app.takent.mobile.ui.auth.welcome.SplashScreen
import app.takent.mobile.ui.auth.welcome.WelcomeScreen
import app.takent.mobile.ui.home.HomeScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route,
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(
                onFinished = {
                    navHostController.navigate(Screen.Welcome.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Welcome.route) {
            WelcomeScreen(
                onNavigateToSignIn = {
                    navHostController.navigate(Screen.SignIn.route)
                },
                onNavigateToSignUp = {
                    navHostController.navigate(Screen.SignUp.route)
                }
            )
        }

        composable(route = Screen.SignIn.route) {
            SignInScreen(
                viewModel = SignInViewModel(),
                onNavigateToSignUp = {
                    navHostController.navigate(Screen.SignUp.route)
                },
                onNavigateToHome = {
                    navHostController.navigate(Screen.Home.route)
                }
            )
        }

        composable(route = Screen.SignUp.route) {
            SignUpScreen(
                viewModel = SignUpViewModel(),
                onBack = {
                    navHostController.popBackStack()
                },
            )
        }

        composable(route = Screen.Home.route) {
            HomeScreen(

            )
        }
    }
}