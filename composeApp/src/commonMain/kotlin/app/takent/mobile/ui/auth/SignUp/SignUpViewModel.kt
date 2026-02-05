package app.takent.mobile.ui.auth.SignUp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.takent.mobile.data.auth.model.SignInDTO
import app.takent.mobile.data.auth.model.SignUpDTO
import io.konform.validation.Invalid
import io.konform.validation.Validation
import io.konform.validation.Validation.Companion.invoke
import io.konform.validation.constraints.minimum
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minLength
import io.konform.validation.jsonschema.pattern
import io.konform.validation.messagesAtPath

class SignUpViewModel : ViewModel(

) {

    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var usernameError by mutableStateOf<String?>(null)
    var firstNameError by mutableStateOf<String?>(null)
    var lastNameError by mutableStateOf<String?>(null)
    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)

    private val validateSignUp = Validation<SignUpDTO> {

        SignUpDTO::firstName {
        }

        SignUpDTO::lastName {
        }

        SignUpDTO::username {
            maxLength(50) hint "El usuario no puede exceder los 50 caracteres"
            minLength(3) hint "El usuario debe tener al menos 3 caracteres"
        }

        SignUpDTO::email {
            maxLength(255) hint "El correo no puede exceder los 255 caracteres"
            pattern(".+@.+\\..+") hint "El formato del email no es válido"
        }

        SignUpDTO::password {
            maxLength(128) hint "La contraseña no puede exceder los 128 caracteres"
            minLength(8) hint "La contraseña debe tener al menos 8 caracteres"
        }
    }

    fun onSignUpClick(onSuccess: () -> Unit) {
        val dto = SignUpDTO(username, email, password, firstName, lastName)
        val result = validateSignUp(dto)

        if (result is Invalid) {
            usernameError = result.errors.messagesAtPath(SignUpDTO::username).firstOrNull();
            firstNameError = result.errors.messagesAtPath(SignUpDTO::firstName).firstOrNull();
            lastNameError = result.errors.messagesAtPath(SignUpDTO::lastName).firstOrNull();
            emailError = result.errors.messagesAtPath(SignUpDTO::email).firstOrNull();
            passwordError = result.errors.messagesAtPath(SignUpDTO::password).firstOrNull();
        } else {
            usernameError = null;
            firstNameError = null;
            lastNameError = null;
            emailError = null;
            passwordError = null;
            onSuccess()
        }
    }
}