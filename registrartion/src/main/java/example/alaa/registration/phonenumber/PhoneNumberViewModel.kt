package example.alaa.registration.phonenumber

import dagger.hilt.android.lifecycle.HiltViewModel
import example.alaa.base.BaseViewModel
import example.alaa.registration.phoneNumberIsValidate
import javax.inject.Inject

@HiltViewModel
class PhoneNumberViewModel @Inject constructor(): BaseViewModel<PhoneNumberIntent , PhoneNumberState, PhoneNumberEvent>()
{

    override fun handleIntent(intent: PhoneNumberIntent) {
        when(intent){
            is PhoneNumberIntent.ValidatePhoneNumber -> validatePhoneNumber(intent.phoneNumber)
            is PhoneNumberIntent.VerifyPhoneNumber -> verifyPhoneNumber()
        }
    }

    private fun verifyPhoneNumber() {
        val phoneNumber = state.value.phoneNumber
        sendEvent(PhoneNumberEvent.NavigateToOtp)

    }


    private fun validatePhoneNumber(phoneNumber: String) {
        if (phoneNumber.length <= 10 && phoneNumber.all { it.isDigit() }) {
            setState {
                copy(
                    phoneNumber = phoneNumber,
                    isValidate = phoneNumber.phoneNumberIsValidate(
                        numberLength = 10,
                        prefix = "1"
                    )
                )
            }
        }
    }

    override val initialState: PhoneNumberState
        get() = PhoneNumberState()


}