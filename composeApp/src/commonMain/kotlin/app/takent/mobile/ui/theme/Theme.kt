package app.takent.mobile.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val TakentLightColorScheme = lightColorScheme(
    primary = TakentStoneDark,
    onPrimary = TakentSandLight,
    background = TakentSandLight,
    surface = TakentSandLight,
    secondary = TakentRoseSupport,
    onSecondary = TakentWhite,
    onBackground = TakentStoneDark,
    onSurface = TakentStoneDark
)

@Composable
fun TakentTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = TakentLightColorScheme,
        typography = getTypography(),
        shapes = TakentShapes,
        content = content
    )
}