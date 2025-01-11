package example.alaa.registration.login

import example.alaa.base.ViewEvent
import example.alaa.base.ViewIntent
import example.alaa.base.ViewState

data class LoginState(
    val userName : Pair<String,Boolean> = Pair("",false),
    val password : Pair<String,Boolean> = Pair("",false),
    val isButtonEnabled : Boolean = false,
): ViewState

sealed class LoginIntent : ViewIntent {
    data class ValidateUsername(val userName : String) : LoginIntent()
    data class ValidatePassword(val password : String) : LoginIntent()
    data object Login : LoginIntent()
}
sealed class LoginEvent : ViewEvent {
    data object NavigateToHome : LoginEvent()
}