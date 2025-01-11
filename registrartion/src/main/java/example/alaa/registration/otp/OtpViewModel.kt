package example.alaa.registration.otp

import example.alaa.base.component.otp.BaseOtpViewModel
import example.alaa.base.component.otp.OtpAction

class OtpViewModel: BaseOtpViewModel<OTPIntent, OTPState, OTPEvent>() {

    override val initialState: OTPState
        get() = OTPState()

    override fun handleIntent(intent: OTPIntent) {
        when(intent){
            is OTPIntent.VerifyOTP -> verifyOtp()
        }
    }

    override fun onAction(action: OtpAction) {
        when(action) {
            is OtpAction.OnChangeFieldFocused -> {
                setState {
                    copy(
                        focusedIndex = action.index
                    )
                }
            }
            is OtpAction.OnEnterNumber -> {
                enterNumber(action.number, action.index)
            }
            OtpAction.OnKeyboardBack -> {
                val previousIndex = getPreviousFocusedIndex(state.value.focusedIndex)
                setState {
                    copy(
                        code = state.value.code.mapIndexed { index, number ->
                            if(index == previousIndex) {
                                null
                            } else {
                                number
                            }
                        },
                        focusedIndex = previousIndex,
                    )
                }
            }
        }
    }

    private fun enterNumber(number: Int?, index: Int) {
        val newCode = state.value.code.mapIndexed { currentIndex, currentNumber ->
            if(currentIndex == index) {
                number
            } else {
                currentNumber
            }
        }
        val wasNumberRemoved = number == null
        setState {
            copy(
                code = newCode,
                focusedIndex = if(wasNumberRemoved || state.value.code.getOrNull(index) != null) {
                    state.value.focusedIndex
                } else {
                    getNextFocusedTextFieldIndex(
                        currentCode = state.value.code,
                        currentFocusedIndex = state.value.focusedIndex
                    )
                },
                isValid = if(newCode.none { it == null }) {
                    newCode.joinToString("").length == 6
                } else null
            )
        }
    }

    private fun verifyOtp() {
        sendEvent(OTPEvent.NavigateToInfo)

    }

}