package example.alaa.base.component.otp

import example.alaa.base.ViewState

interface BaseOtpState : ViewState{
    val code: List<Int?>
    val focusedIndex: Int?
    val isValid: Boolean?
}

