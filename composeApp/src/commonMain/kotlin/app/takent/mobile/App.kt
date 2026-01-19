package app.takent.mobile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import app.takent.mobile.ui.navigation.AppNavHost
import app.takent.mobile.ui.theme.TakentTheme

@Composable
@Preview
fun App() {
    TakentTheme {
        val navHostController = rememberNavController()
        AppNavHost(navHostController)
    }
}