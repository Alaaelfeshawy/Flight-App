package example.alaa.home.ui.model


data class DatePickerData(
    val selectedStartDate : String = "",
    val selectedEndDate : String?=null,
    val showDatePicker : Boolean = false,
    val isValid : Boolean ?= null,
    val dateErrorMessage : String?=null
)