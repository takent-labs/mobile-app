package app.takent.mobile.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.takent.mobile.ui.auth.SignIn.SignInScreen
import app.takent.mobile.ui.auth.SignIn.SignInViewModel
import app.takent.mobile.ui.auth.SignUp.SignUpScreen
import app.takent.mobile.ui.auth.SignUp.SignUpViewModel
import app.takent.mobile.ui.auth.welcome.SplashScreen
import app.takent.mobile.ui.auth.welcome.WelcomeScreen
import app.takent.mobile.ui.feed.FeedScreen
import app.takent.mobile.ui.feed.FeedScreenViewModel
import app.takent.mobile.ui.profile.ProfileScreen
import app.takent.mobile.ui.profile.ProfileScreenViewModel

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    showCreateSheet: Boolean = false,
    onDismissCreateSheet: () -> Unit = {}
) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route,
        modifier = modifier
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
                    navHostController.navigate(Screen.Feed.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.SignUp.route) {
            SignUpScreen(
                viewModel = SignUpViewModel(),
                onNavigateToHome = {
                    navHostController.navigate(Screen.Feed.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                },
                onBack = {
                    navHostController.popBackStack()
                },
            )
        }

        composable(route = Screen.Feed.route) {
            val viewModel = remember { FeedScreenViewModel() }

            FeedScreen(
                posts = viewModel.posts,
                followingPosts = viewModel.posts,
                isCreatingPost = viewModel.isCreatingPost,
                isLoading = viewModel.isLoading,
                isRefreshing = viewModel.isRefreshing,
                onRefresh = { viewModel.refresh() },
                onCreatePost = { content, imageBytes ->
                    viewModel.createPost(
                        content = content,
                        imageBytes = imageBytes,
                        onUploadSuccess = { }
                    )
                },
                onPostClick = { _ -> },
                showCreateSheet = showCreateSheet,
                onDismissCreateSheet = onDismissCreateSheet
            )
        }

        composable(route = Screen.Profile.route) {
            val viewModel = remember { ProfileScreenViewModel() }
            ProfileScreen(viewModel = viewModel)
        }
    }
}