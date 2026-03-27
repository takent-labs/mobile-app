package app.takent.mobile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.takent.mobile.ui.navigation.FloatingNavBar
import app.takent.mobile.ui.navigation.AppNavHost
import app.takent.mobile.ui.navigation.Screen
import app.takent.mobile.ui.theme.TakentTheme

@Composable
fun App() {
    TakentTheme {
        val navHostController = rememberNavController()
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        
        var showCreateSheet by remember { mutableStateOf(false) }

        val authScreens = listOf(
            Screen.Splash.route,
            Screen.Welcome.route,
            Screen.SignIn.route,
            Screen.SignUp.route
        )

        Box(modifier = Modifier.fillMaxSize()) {
            AppNavHost(
                navHostController = navHostController,
                modifier = Modifier.fillMaxSize(),
                showCreateSheet = showCreateSheet,
                onDismissCreateSheet = { showCreateSheet = false }
            )

            if (currentRoute !in authScreens) {
                FloatingNavBar(
                    navController = navHostController,
                    onAddClick = { showCreateSheet = true },
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}
