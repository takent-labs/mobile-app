package app.takent.mobile.ui.auth.SignIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.takent.mobile.data.auth.model.SignInDTO
import io.konform.validation.Invalid
import io.konform.validation.Validation
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minLength
import io.konform.validation.jsonschema.pattern
import io.konform.validation.messagesAtPath

class SignInViewModel : ViewModel (

) {
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)

    private val validateSignIn = Validation<SignInDTO> {
        SignInDTO::email {
            maxLength(255) hint "El correo no puede exceder los 255 caracteres"
            pattern(".+@.+\\..+") hint "El formato del email no es válido"
        }

        SignInDTO::password {
            maxLength(128) hint "La contraseña no puede exceder los 128 caracteres"
            minLength(8) hint "La contraseña debe tener al menos 8 caracteres"
        }
    }

    fun onSignInClick(onSuccess: () -> Unit) {
        val dto = SignInDTO(email, password)
        val result = validateSignIn(dto)

        if (result is Invalid) {
            emailError = result.errors.messagesAtPath(SignInDTO::email).firstOrNull();
            passwordError = result.errors.messagesAtPath(SignInDTO::password).firstOrNull();
        } else {
            emailError = null;
            passwordError = null;
            onSuccess()
        }
    }
}