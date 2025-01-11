package example.alaa.base.component.otp

import example.alaa.base.BaseViewModel
import example.alaa.base.ViewEvent
import example.alaa.base.ViewIntent
import example.alaa.base.ViewState

abstract class BaseOtpViewModel<Intent : ViewIntent, State : ViewState ,Event : ViewEvent>
    : BaseViewModel<Intent, State, Event>() {
    abstract  fun onAction(action: OtpAction)

    protected fun getPreviousFocusedIndex(currentIndex: Int?): Int? {
        return currentIndex?.minus(1)?.coerceAtLeast(0)
    }

    protected fun getNextFocusedTextFieldIndex(
        currentCode: List<Int?>,
        currentFocusedIndex: Int?
    ): Int? {
        if(currentFocusedIndex == null) {
            return null
        }

        return getFirstEmptyFieldIndexAfterFocusedIndex(
            code = currentCode,
            currentFocusedIndex = currentFocusedIndex
        )
    }

    private fun getFirstEmptyFieldIndexAfterFocusedIndex(
        code: List<Int?>,
        currentFocusedIndex: Int
    ): Int {
        code.forEachIndexed { index, number ->
            if(index <= currentFocusedIndex) {
                return@forEachIndexed
            }
            if(number == null) {
                return index
            }
        }
        return currentFocusedIndex
    }
}