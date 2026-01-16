package app.takent.mobile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.takent.mobile.ui.auth.LogIn.LoginScreen
import app.takent.mobile.ui.theme.TakentTheme

@Composable
@Preview
fun App() {
    TakentTheme {
        LoginScreen()
    }
}