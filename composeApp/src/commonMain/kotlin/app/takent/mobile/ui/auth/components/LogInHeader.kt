package app.takent.mobile.ui.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import takentmobileapp.composeapp.generated.resources.Res
import takentmobileapp.composeapp.generated.resources.logo_isotipo_dark

@Composable
fun AuthHeader(
    title: String,
    subtitle: String,
) {
    Image(
        painter = painterResource(Res.drawable.logo_isotipo_dark),
        contentDescription = "Logo Takent",
        modifier = Modifier
            .padding(bottom = 12.dp)
            .size(68.dp)
            .clip(RoundedCornerShape(14.dp))
    )

    Text(
        text = title,
        style = MaterialTheme.typography.displayLarge,
        color = MaterialTheme.colorScheme.primary
    )

    Text(
        text = subtitle,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
        modifier = Modifier.padding(top = 8.dp)
    )

    Spacer(modifier = Modifier.height(40.dp))
}