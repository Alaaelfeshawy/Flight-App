package example.alaa.registration.userinformation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import example.alaa.base.BaseViewModel
import example.alaa.registration.isValidEmail
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInformationViewModel @Inject constructor()
    : BaseViewModel<UserInformationIntent, UserInformationState, UserInformationEvent>() {

    override val initialState: UserInformationState
        get() = UserInformationState(
            passwordCriteria = Validator.getPasswordCriteriaList()
        )

    init {
        resetPasswordCriteria()
    }

    override fun handleIntent(intent: UserInformationIntent) {
        when (intent) {
            UserInformationIntent.CreateAccount -> createAccount()
            is UserInformationIntent.ValidateConfirmPassword ->
                validatePassword(confirmPassword = intent.confirmPassword, password = state.value.password)
            is UserInformationIntent.ValidateEmail ->
                validateEmail(intent.email)
            is UserInformationIntent.ValidatePassword ->
                validatePassword(password = intent.password)
            is UserInformationIntent.SetUsername ->
                validateUsername(intent.userName)
        }
    }

    private fun validateUsername(userName: String) {
        setState {
            copy(
                userName = userName,
                isUserNameValid = userName.length >= 3
            )
        }
        updateButtonState()
    }

    private fun validateEmail(email: String) {
        setState {
            copy(
                email = email,
                isEmailValid = email.isValidEmail()
            )
        }
        updateButtonState()
    }

    private fun createAccount() {
        sendEvent(UserInformationEvent.NavigateToSuccess)

    }

    private fun validatePassword(password: String, confirmPassword: String? = null) =
        viewModelScope.launch {
            val confirmPass = confirmPassword ?: state.value.confirmPassword
            val validationResults = validatePasswordCriteria(password, confirmPass)
            val allCriteriaMet = validationResults.all { it.isMet }

            setState {
                copy(
                    password = password,
                    confirmPassword = confirmPass,
                    isPasswordValid = allCriteriaMet
                )
            }
            updateButtonState()
        }

    private fun validatePasswordCriteria(password: String, confirmPassword: String?): List<Validator.PasswordCriteria> {
        return Validator.getPasswordCriteriaList().onEach { criteria ->
            criteria.isMet = when (criteria) {
                Validator.PasswordCriteria.LONG -> password.length in 8..20
                Validator.PasswordCriteria.CAPITAL_LETTER_CHAR -> password.contains("[A-Z]".toRegex())
                Validator.PasswordCriteria.SPECIAL_CHAR -> password.contains("[@#\$%^&]".toRegex()) && "_" !in password
                Validator.PasswordCriteria.NO_SPACE -> !password.contains("\\s".toRegex())
                Validator.PasswordCriteria.IS_MATCH_CONFIRM_PASSWORD -> password == confirmPassword
            }
        }
    }

    private fun resetPasswordCriteria() {
        Validator.getPasswordCriteriaList().forEach { it.isMet = false }
    }

    private fun updateButtonState() {
        setState {
            copy(
                isButtonEnabled = state.value.isEmailValid &&
                        state.value.isPasswordValid &&
                        state.value.isUserNameValid
            )
        }
    }
}
