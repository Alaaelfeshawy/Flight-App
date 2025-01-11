package example.alaa.registration.userinformation

import example.alaa.base.ViewEvent
import example.alaa.base.ViewIntent
import example.alaa.base.ViewState
import example.alaa.registration.userinformation.Validator.PasswordCriteria

data class UserInformationState(
    val userName: String = "",
    val isUserNameValid : Boolean = false,
    val email: String = "",
    val isEmailValid: Boolean = false,
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordValid: Boolean = false,
    val passwordCriteria: List<PasswordCriteria> = emptyList(),
    val isButtonEnabled : Boolean = false
) : ViewState

sealed class UserInformationIntent : ViewIntent{
    data class SetUsername(val userName : String) : UserInformationIntent()
    data class ValidateEmail(val email : String) : UserInformationIntent()
    data class ValidatePassword(val password : String) : UserInformationIntent()
    data class ValidateConfirmPassword(val confirmPassword : String) : UserInformationIntent()
    data object CreateAccount : UserInformationIntent()
}
sealed class UserInformationEvent : ViewEvent {
    data object NavigateToSuccess : UserInformationEvent()
}
