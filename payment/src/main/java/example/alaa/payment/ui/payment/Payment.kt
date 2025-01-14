package example.alaa.payment.ui.payment

import example.alaa.base.ViewEvent
import example.alaa.base.ViewIntent
import example.alaa.base.ViewState

data class PaymentState(
    val cardNumber : Pair<String,String?> = Pair("",null),
    val cardHolderName : Pair<String,String?> = Pair("",null),
    val cvv : Pair<String,String?> = Pair("",null),
    val expiryDate : Pair<String,String?> = Pair("",null),
) : ViewState

sealed class PaymentIntent : ViewIntent {
    data class SetCardNumber (val cardNumber : String) : PaymentIntent()
    data class SetCardHolder(val cardHolderName : String) : PaymentIntent()
    data class SetCvv (val cvv : String) : PaymentIntent()
    data class SetExpiryDate (val expiryDate : String) : PaymentIntent()
    data object Confirm : PaymentIntent()
}

sealed class PaymentEvent : ViewEvent {

    data object NavigateToBoardingPass : PaymentEvent()
}
