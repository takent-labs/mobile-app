package app.takent.mobile.ui.feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import takentmobileapp.composeapp.generated.resources.Res
import takentmobileapp.composeapp.generated.resources.avatar_fallback

@Composable
fun ProfileMenu(
    showMenu: Boolean,
    onMenuToggle: (Boolean) -> Unit,
    userImageUrl: String? = null
) {
    Box(modifier = Modifier.padding(end = 8.dp)) {
        IconButton(
            onClick = { onMenuToggle(true) }
        ) {
            AsyncImage(
                model = userImageUrl,
                contentDescription = "Perfil",
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentScale = ContentScale.Crop,
                error = painterResource(
                    Res.drawable.avatar_fallback
                )
            )
        }

        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp))
        ) {
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { onMenuToggle(false) },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .border(
                        width = 0.5.dp,
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                DropdownMenuItem(
                    text = { Text("Mi Perfil", style = MaterialTheme.typography.bodyLarge) },
                    onClick = { onMenuToggle(false) },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )
                DropdownMenuItem(
                    text = { Text("Ajustes", style = MaterialTheme.typography.bodyLarge) },
                    onClick = { onMenuToggle(false) },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                DropdownMenuItem(
                    text = {
                        Text(
                            "Cerrar sesión",
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    onClick = { onMenuToggle(false) }
                )
            }
        }
    }
}