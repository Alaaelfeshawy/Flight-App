package example.alaa.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<I : ViewIntent, S : ViewState, E : ViewEvent> : ViewModel() {

    abstract val initialState : S

    // StateFlow to manage the UI state
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    // Channel to emit one-time events
    private val _event = Channel<E>(Channel.BUFFERED)
    val events: Flow<E> = _event.receiveAsFlow()

    // Processing user intents
    private val _intent = MutableSharedFlow<I>()

    init {
        viewModelScope.launch {
            _intent.collect { intent ->
                handleIntent(intent)
            }
        }
    }

    // Collect intents from the UI
    fun processIntent(intent: I) {
        viewModelScope.launch {
            _intent.emit(intent)
        }
    }

    // Update the state
    protected fun setState(reducer: S.() -> S) {
        _state.value = _state.value.reducer()
    }

    // Emit one-time events
    protected fun sendEvent(event: E) {
        viewModelScope.launch {
            _event.send(event)
        }
    }

    // Abstract function to handle each Intent
    protected abstract fun handleIntent(intent: I)
}
