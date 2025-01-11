package example.alaa.home.ui.component.tabs

import example.alaa.base.ViewEvent
import example.alaa.base.ViewState
import example.alaa.home.ui.model.PassengerUiModel
import example.alaa.home.ui.model.SearchModel

data class BaseTabState(
    val passengers : List<PassengerUiModel> = listOf(),
    val planeClasses : List<String> = listOf(),
    val selectedPassengerIndex : Int = 0,
    val isPassengerBottomSheetExpanded : Boolean = false,
    val isClassDropDownExpanded : Boolean = false,
    val selectedClassPosition : Int = 0,
    val isTotalPassengerCountValid : Boolean = true,
    val invalidTotalPassengerCountErrorMessage : String = "",
    val numOfPassengers : Int = 1,
) : ViewState


sealed class TabEvent: ViewEvent {
    data class NavigateToSearch<T>(val searchDate : T) : TabEvent()
}