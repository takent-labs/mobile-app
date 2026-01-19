package app.takent.mobile.ui.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.takent.mobile.ui.components.PrimaryIconButton
import takentmobileapp.composeapp.generated.resources.Res
import takentmobileapp.composeapp.generated.resources.ic_apple_dark
import takentmobileapp.composeapp.generated.resources.ic_google

@Composable
fun ProvidersIconGroup(
    onGoogleClick: () -> Unit,
    onAppleClick: () -> Unit
) {
    Column(
        modifier = Modifier .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PrimaryIconButton(
                onClick = onGoogleClick,
                description = "Google",
                modifier = Modifier.weight(1f),
                icon = Res.drawable.ic_google
            )

            PrimaryIconButton(
                onClick = onAppleClick,
                description = "Apple",
                modifier = Modifier.weight(1f),
                icon = Res.drawable.ic_apple_dark
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )

            Text(
                text = "O",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
    }
}