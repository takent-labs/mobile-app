package app.takent.mobile.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.takent.mobile.data.post.model.PostResponseDTO
import app.takent.mobile.ui.components.SecondaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileScreenViewModel
) {
    val uiState = viewModel.uiState

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        text = uiState.user?.username ?: "Perfil",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    ) 
                }
            )
        }
    ) { padding ->
        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            ProfileContent(
                modifier = Modifier.fillMaxSize().padding(padding),
                uiState = uiState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileContent(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf(Icons.Default.GridOn, Icons.Default.Lock, Icons.Default.FavoriteBorder)

    Column(modifier = modifier) {
        // Header Section
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Username
            Text(
                text = "@${uiState.user?.username ?: ""}",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Stats Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStatItem(count = uiState.followingCount.toString(), label = "Siguiendo")
                ProfileStatItem(count = uiState.followersCount.toString(), label = "Seguidores")
                ProfileStatItem(count = uiState.likesCount.toString(), label = "Me gusta")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Actions
            SecondaryButton(
                text = "Editar perfil",
                onClick = { /* Accion de edicion */ },
                modifier = Modifier.height(48.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Bio
            Text(
                text = uiState.bio,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Tabs Section
        SecondaryTabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            divider = { 
                HorizontalDivider(
                    thickness = 0.5.dp, 
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                ) 
            }
        ) {
            tabs.forEachIndexed { index, icon ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    icon = { Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp)) }
                )
            }
        }

        // Grid Content
        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                0 -> PostsGrid(uiState.posts)
                else -> EmptyStateTab()
            }
        }
    }
}

@Composable
private fun ProfileStatItem(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold, 
                fontSize = 18.sp
            )
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun PostsGrid(posts: List<PostResponseDTO>) {
    if (posts.isEmpty()) {
        EmptyStateTab()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            items(posts) { _ ->
                Box(
                    modifier = Modifier
                        .aspectRatio(3f / 4f)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    // Placeholder para miniatura de video
                }
            }
        }
    }
}

@Composable
private fun EmptyStateTab() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No hay contenido todavía",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
    }
}