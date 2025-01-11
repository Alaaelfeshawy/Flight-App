package example.alaa.registration.phonenumber

import example.alaa.base.ViewEvent
import example.alaa.base.ViewIntent
import example.alaa.base.ViewState

data class PhoneNumberState(
    val phoneNumber: String = "",
    val isValidate: Boolean = true,
) : ViewState

sealed class PhoneNumberIntent : ViewIntent{
    data class ValidatePhoneNumber(val phoneNumber : String) : PhoneNumberIntent()
    data object VerifyPhoneNumber : PhoneNumberIntent()
}
sealed class PhoneNumberEvent : ViewEvent {
    data object NavigateToOtp : PhoneNumberEvent()
}
