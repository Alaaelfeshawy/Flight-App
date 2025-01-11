package example.alaa.registration.login

import example.alaa.base.BaseViewModel
import example.alaa.base.component.otp.BaseOtpViewModel
import example.alaa.base.component.otp.OtpAction
import example.alaa.registration.isValidEmail
import example.alaa.registration.phoneNumberIsValidate

class LoginViewModel: BaseViewModel<LoginIntent, LoginState, LoginEvent>() {

    override val initialState: LoginState
        get() = LoginState()

    override fun handleIntent(intent: LoginIntent) {
        when(intent){
            LoginIntent.Login -> performLogin()
            is LoginIntent.ValidatePassword -> validatePassword(intent.password)
            is LoginIntent.ValidateUsername -> validateUsername(intent.userName)
        }
    }

    private fun validateUsername(userName: String) {
        setState {
            copy(
                userName = Pair(
                    userName,
                    (userName.isValidEmail() || userName.phoneNumberIsValidate(
                        numberLength = 11,
                        prefix = "01"
                    )) && userName.isNotBlank()
                )
            )
        }
        isButtonEnabled()
    }

    private fun validatePassword(password: String) {
        setState {
            copy(
                password = Pair(
                    password,
                    (password.isNotBlank())
                )
            )
        }
        isButtonEnabled()
    }

    private fun performLogin() {
        sendEvent(LoginEvent.NavigateToHome)
    }

    private fun isButtonEnabled(){
        setState {
            copy(
                isButtonEnabled = userName.second && password.second
            )
        }
    }

}