package app.takent.mobile.ui.auth.SignIn

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AlternateEmail
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import app.takent.mobile.ui.auth.components.AuthHeader
import app.takent.mobile.ui.auth.components.ProvidersIconGroup
import app.takent.mobile.ui.components.PrimaryButton

@Composable
fun SignInScreen(
    onNavigateToSignUp: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.15f))

            AuthHeader(title = "Inicia Sesión", subtitle = "Introduce tus datos para continuar")

            ProvidersIconGroup(
                onAppleClick = { /* Acción de inicio con Apple */ },
                onGoogleClick = { /* Acción de inicio con Google */ }
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", style = MaterialTheme.typography.labelLarge) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.AlternateEmail,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                    )
                },
                shape = MaterialTheme.shapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    cursorColor = MaterialTheme.colorScheme.primary
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña", style = MaterialTheme.typography.labelLarge) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = MaterialTheme.shapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    cursorColor = MaterialTheme.colorScheme.primary
                ),
                singleLine = true
            )

            TextButton(
                onClick = { /* Acción  de recuperar contraseña*/ },
                modifier = Modifier.align(Alignment.End).padding(top = 4.dp)
            ) {
                Text(
                    text = "¿Olvidaste tu ontraseña?",
                    style = MaterialTheme.typography.labelLarge.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            PrimaryButton(
                text = "Iniciar Sesión",
                onClick = { /* Acción de inicio de sesión */ }
            )

            TextButton(
                onClick = { onNavigateToSignUp() },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 6.dp)
            ) {
                Text(
                    text = "¿Aún no tienes una cuenta?",
                    style = MaterialTheme.typography.labelLarge.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                )
            }
        }
    }
}