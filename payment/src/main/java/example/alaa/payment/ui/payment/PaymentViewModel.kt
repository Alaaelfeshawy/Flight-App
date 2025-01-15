package example.alaa.payment.ui.payment

import dagger.hilt.android.lifecycle.HiltViewModel
import example.alaa.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor() : BaseViewModel<PaymentIntent, PaymentState, PaymentEvent>() {

    override val initialState: PaymentState
        get() = PaymentState()

    override fun handleIntent(intent: PaymentIntent) {
        when (intent) {
            is PaymentIntent.SetCardHolder -> setCardHolder(intent.cardHolderName)
            is PaymentIntent.SetCardNumber -> setCardNumber(intent.cardNumber)
            is PaymentIntent.SetCvv -> setCvv(intent.cvv)
            is PaymentIntent.SetExpiryDate -> setExpiryDate(intent.expiryDate)
            is PaymentIntent.Confirm -> confirmPayment()
        }
    }

    private fun setCardHolder(cardHolderName: String) {
        setState { copy(cardHolderName = cardHolderName to null) }
    }

    private fun setCardNumber(cardNumber: String) {
        val card = cardNumber.take(16)
        setState { copy(cardNumber = card to null) }
    }

    private fun setCvv(cvv: String) {
        setState { copy(cvv = cvv.take(3) to null) }
    }

    private fun setExpiryDate(expiryDate: String) {
        val filteredText = expiryDate.filter { it.isDigit() }
        val text = when {
            filteredText.length >= 4 -> {
                val day = filteredText.substring(0, 2)
                val month = filteredText.substring(2, 4)
                "$day/$month"
            }
            filteredText.length == 3 -> {
                val day = filteredText.substring(0, 2)
                val month = filteredText.substring(2, 3)
                "$day/$month"
            }
            filteredText.length == 2 -> {
                val day = filteredText.substring(0, 2)
                "$day/"
            }
            else -> filteredText
        }
        setState {
            copy(
                expiryDate = text to null
            )
        }
    }

    private fun confirmPayment() {
        val expiryDateError = if (state.value.expiryDate.first.isBlank()) "couldn't be empty" else null
        val cardHolderNameError = if (state.value.cardHolderName.first.isBlank()) "couldn't be empty" else null
        val cardNumberError = when {
            state.value.cardNumber.first.isBlank() -> "couldn't be empty"
            state.value.cardNumber.first.length < 16 -> "enter valid card number"
            else -> null
        }
        val cvvError = when {
            state.value.cvv.first.isBlank() -> "couldn't be empty"
            state.value.cvv.first.length < 3 -> "please enter valid cvv"
            else -> null
        }

        val hasErrors = listOf(expiryDateError, cardHolderNameError, cardNumberError, cvvError)
            .any { it != null }

        if (hasErrors) {
            setState {
                copy(
                    cardNumber = cardNumber.copy(second = cardNumberError),
                    cardHolderName = cardHolderName.copy(second = cardHolderNameError),
                    cvv = cvv.copy(second = cvvError),
                    expiryDate = expiryDate.copy(second = expiryDateError)
                )
            }
        } else {
            sendEvent(PaymentEvent.NavigateToBoardingPass)
        }
    }
}