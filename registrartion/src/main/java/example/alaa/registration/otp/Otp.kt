package example.alaa.registration.otp

import example.alaa.base.ViewEvent
import example.alaa.base.ViewIntent
import example.alaa.base.component.otp.BaseOtpState

data class OTPState(
    override val code: List<Int?> =(1..6).map { null },
    override val focusedIndex: Int? = null,
    override val isValid: Boolean? =null
): BaseOtpState

sealed class OTPIntent : ViewIntent{
    data object VerifyOTP : OTPIntent()
}
sealed class OTPEvent : ViewEvent {
    data object NavigateToInfo : OTPEvent()
}