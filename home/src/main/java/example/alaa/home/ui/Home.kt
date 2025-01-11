package example.alaa.home.ui

import example.alaa.base.ViewIntent
import example.alaa.base.ViewState
import example.alaa.home.ui.model.OffersUiModel

data class HomeState(
    val selectedTab : Int = 0,
    val tabs : List<String> = listOf(),
    val offers : List<OffersUiModel> = listOf(),
) : ViewState

sealed class HomeIntent: ViewIntent{
    data object Search : HomeIntent()
    data class SetSelectedTab(val selectedTab : Int) : HomeIntent()
}


sealed class DropDownListActions {
    data class OnItemSelected(val item : Int) : DropDownListActions()
    data object OnDismissRequest : DropDownListActions()
    data object OnDropDownClicked : DropDownListActions()
}

sealed class PassengerDialogActions {
    data object OnClick : PassengerDialogActions()
}

sealed class DatePickerActions {
    data class OnDateSelected(val selectedStartDate : String , val selectedEndDate : String?=null) : DatePickerActions()
    data class OnClick(val isClicked : Boolean ) : DatePickerActions()
}