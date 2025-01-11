package example.alaa.home.ui.component.tabs.multicity

import dagger.hilt.android.lifecycle.HiltViewModel
import example.alaa.base.BaseViewModel
import example.alaa.home.ui.model.Action
import example.alaa.home.ui.model.PassengerUiModel
import example.alaa.home.ui.DatePickerActions
import example.alaa.home.ui.DropDownListActions
import example.alaa.home.ui.PassengerDialogActions
import example.alaa.home.ui.component.tabs.BaseTabState
import example.alaa.home.ui.component.tabs.TabEvent
import example.alaa.home.ui.component.tabs.singletrip.SingleTripState
import example.alaa.home.ui.model.FieldValidation
import example.alaa.home.ui.model.SearchModel
import example.alaa.home.ui.model.TripType
import javax.inject.Inject

@HiltViewModel
class MultiCityViewModel @Inject constructor(): BaseViewModel<
        MultiCityTripIntent, MultiCityTripState , TabEvent>() {

    override val initialState: MultiCityTripState
        get() = MultiCityTripState(
            multiCityData = mutableListOf(
                SingleTripState(
                    baseTabState = BaseTabState(
                        passengers = generateListOfPassengers(),
                        planeClasses = generateListOfClasses()
                    )
                ),
                SingleTripState(
                    baseTabState = BaseTabState(
                        passengers = generateListOfPassengers(),
                        planeClasses = generateListOfClasses()
                    )
                )
            ),
        )

    override fun handleIntent(intent: MultiCityTripIntent) {
        when (intent) {
            is MultiCityTripIntent.ValidateOriginField -> validateOriginField(intent.value , intent.index)
            is MultiCityTripIntent.ValidateDestinationField -> validateDestinationField(intent.value , intent.index)
            is MultiCityTripIntent.ExchangeFields -> exchangeFields(intent.origin, intent.destination , intent.index)
            is MultiCityTripIntent.ValidatePassengerCount -> validatePassengerCount(intent.action , intent.count , intent.passengerIndex , tripIndex = intent.tripIndex)
            is MultiCityTripIntent.ConfirmNumOfPassenger -> confirmNumOfPassenger(intent.tripIndex)
            is MultiCityTripIntent.Search -> {
                validateForm()
                val numberOfForms = state.value.multiCityData.size
                val numberOfValidation = state.value.multiCityData.count { it.isFormValid }
                if (numberOfForms == numberOfValidation){
                    val listOfSearchModel = mutableListOf<SearchModel>()
                    state.value.multiCityData.forEach { data ->
                       listOfSearchModel.add(
                           SearchModel(
                               tripType = TripType.SINGLE_TRIP,
                               origin = data.origin.value.orEmpty(),
                               destination = data.destination.value.orEmpty(),
                               selectedStartDate = data.date.selectedStartDate,
                               selectedEndDate = data.date.selectedEndDate.orEmpty(),
                               selectedPassenger = data.baseTabState.passengers[data.baseTabState.selectedPassengerIndex],
                               selectedClass = data.baseTabState.planeClasses[data.baseTabState.selectedClassPosition],
                           )
                       )
                    }
                    sendEvent(TabEvent.NavigateToSearch(listOfSearchModel))
                }
            }
            is MultiCityTripIntent.AddExchangeRateField -> addExchangeRateField()
            is MultiCityTripIntent.RemoveExchangeRateField -> removeExchangeRateField()
        }
    }

    private fun removeExchangeRateField() {
        val updatedList = state.value.multiCityData.toMutableList().apply {
            if (isNotEmpty()) removeAt(lastIndex)
        }

        setState {
            copy(
                multiCityData = updatedList
            )
        }

    }

    private fun addExchangeRateField() {
        val updatedList = state.value.multiCityData.toMutableList().apply {
            add(
                SingleTripState(
                    baseTabState = BaseTabState(
                        passengers = generateListOfPassengers(),
                        planeClasses = generateListOfClasses()
                    )
            )
            )
        }
        setState {
            copy(multiCityData = updatedList)
        }
    }

    fun handleDatePickerActions(action: DatePickerActions, index: Int) {
        when (action) {
            is DatePickerActions.OnClick -> updateShowDatePicker(index, action.isClicked)
            is DatePickerActions.OnDateSelected -> updateSelectedDate(index, action.selectedStartDate , action.selectedEndDate)
        }
    }

    private fun updateShowDatePicker(index: Int, isClicked: Boolean) {
        setState {
            copy(
                multiCityData = state.value.multiCityData.mapIndexed { currIndex, singleTripState ->
                    if (index == currIndex) {
                        singleTripState.copy(
                            date = multiCityData[index].date.copy(
                                showDatePicker = isClicked
                            ),
                        )
                    } else singleTripState
                } as MutableList<SingleTripState>
            )
        }
    }

    private fun updateSelectedDate(index: Int, selectedStartDate: String , selectedEndDate: String?) {
        setState {
            copy(
                multiCityData = multiCityData.mapIndexed { currIndex, singleTripState ->
                    if (index == currIndex) {
                        singleTripState.copy(
                            date = multiCityData[index].date.copy(
                                selectedStartDate = selectedStartDate,
                                selectedEndDate = selectedEndDate
                            ),
                        )
                    } else singleTripState
                } as MutableList<SingleTripState>
            )
        }
    }

    private fun confirmNumOfPassenger(index: Int) {
        val sumNumberOfTravellers = state.value.multiCityData[index].baseTabState.passengers.sumOf { it.numberOfTravellers }

        setState {
            copy(
                multiCityData = state.value.multiCityData.mapIndexed { currIndex, singleTripState ->
                    if (index == currIndex){
                        singleTripState.copy(
                            baseTabState = singleTripState.baseTabState.copy(
                                numOfPassengers = sumNumberOfTravellers,
                                isPassengerBottomSheetExpanded = false
                            )
                        )
                    } else singleTripState
                }  as MutableList<SingleTripState>
            )
        }
    }

    private fun validatePassengerCount(action: Action, count: Int, index : Int, tripIndex: Int) {
        val newCount = when (action) {
            Action.DECREASE -> (count - 1).coerceIn(0, 9)
            Action.INCREASE -> (count + 1).coerceIn(0, 9)
        }

        updateActualNumber(index, newCount, tripIndex)

    }

    private fun updateActualNumber(passengerIndex: Int, result: Int , tripIndex : Int) {
        setState {
            copy(
                multiCityData = multiCityData.mapIndexed { index, trip ->
                    if (index == tripIndex) {
                        trip.copy(
                            baseTabState = trip.baseTabState.copy(
                                selectedPassengerIndex = passengerIndex,
                                passengers = trip.baseTabState.passengers.mapIndexed { currIndex, passenger ->
                                    if (currIndex == passengerIndex) {
                                        passenger.copy(numberOfTravellers = result)
                                    } else {
                                        passenger
                                    }
                                }
                            )
                        )
                    } else trip

                } as MutableList<SingleTripState>
            )
        }
        countTotalActualNumber(tripIndex)
    }

    private fun countTotalActualNumber(tripIndex : Int){
        setState {
            copy(
                multiCityData = multiCityData.mapIndexed { index, trip ->
                    if (index == tripIndex) {
                        val numOfTravellers = trip.baseTabState.passengers.sumOf { it.numberOfTravellers }
                        trip.copy(
                            baseTabState = trip.baseTabState.copy(
                                isTotalPassengerCountValid = numOfTravellers in 1..16,
                                invalidTotalPassengerCountErrorMessage = when {
                                    numOfTravellers > 16 -> "Cannot have more than 16 travellers"
                                    numOfTravellers < 1 -> "Please select one or more passengers"
                                    else -> ""
                                }
                            )
                        )
                    } else trip
                } as MutableList<SingleTripState>
            )
        }
    }

    private fun exchangeFields(origin: String?, destination: String? , index: Int?) {
        val destinationValidation = isValid(destination)
        val originValidation = isValid(origin)

        setState {
            copy(
                multiCityData = state.value.multiCityData.mapIndexed { currIndex, singleTripState ->
                    when (currIndex) {
                        index -> singleTripState.copy(
                            destination = FieldValidation(
                                value = origin,
                                isValid = originValidation.first,
                                errorMessage = originValidation.second
                            ),
                            origin = FieldValidation(
                                value = destination,
                                isValid = destinationValidation.first,
                                errorMessage = destinationValidation.second
                            ),
                        )
                        else -> singleTripState
                    }
                } as MutableList<SingleTripState>
            )
        }
    }

    private fun validateDestinationField(value: String, index: Int?) {
        val valueValidation = isValid(value)
        setState {
            copy(
                multiCityData = state.value.multiCityData.mapIndexed { currIndex, singleTripState ->
                    singleTripState.takeIf { currIndex != index }
                        ?: singleTripState.copy(destination = FieldValidation(
                            value = value,
                            isValid = valueValidation.first,
                            errorMessage = valueValidation.second
                        ))
                } as MutableList<SingleTripState>
            )
        }
    }

    private fun validateOriginField(value: String, index: Int?) {
        val valueValidation = isValid(value)
        setState {
            copy(
                multiCityData = state.value.multiCityData.mapIndexed { currIndex, singleTripState ->
                    singleTripState.takeIf { currIndex != index }
                        ?: singleTripState.copy(origin = FieldValidation(
                            value = value,
                            isValid = valueValidation.first,
                            errorMessage = valueValidation.second
                        ))
                } as MutableList<SingleTripState>
            )
        }
    }



    private fun isValid(value: String?): Pair<Boolean,String?> {
        return if (value.isNullOrEmpty()){
            Pair(false , "couldn't be empty")
        } else if (value.length < 3){
            Pair(false , "invalid value")
        } else Pair(true , null)
    }

    private fun validateForm() {
        setState {
           copy(
               multiCityData=  multiCityData.map { multiCityData ->
                   val originValid = isValid(multiCityData.origin.value)
                   val destinationValid = isValid(multiCityData.destination.value)
                   val isValidDate = multiCityData.date.selectedStartDate.isNotEmpty()

                   multiCityData.copy(
                       origin = multiCityData.origin.copy(
                           isValid = originValid.first,
                           errorMessage = originValid.second
                       ),
                       destination = multiCityData.destination.copy(
                           isValid = destinationValid.first,
                           errorMessage = destinationValid.second
                       ),
                       date=  multiCityData.date.copy(
                           isValid = isValidDate,
                           dateErrorMessage = if (!isValidDate) "Departure date couldn't be empty" else null
                       ) ,
                       isFormValid = originValid.first && destinationValid.first && isValidDate
                   )

               } as MutableList<SingleTripState>
           )

        }
    }

    fun handleActions(action: DropDownListActions, tripIndex: Int) {
        when (action) {
            is DropDownListActions.OnDismissRequest -> onDismissRequest(tripIndex)
            is DropDownListActions.OnDropDownClicked -> onDropDownClicked(tripIndex)
            is DropDownListActions.OnItemSelected -> onItemSelected(action.item , tripIndex)
        }
    }

    fun handleDialogActions(actions: PassengerDialogActions, tripIndex: Int) {
        when (actions) {
            PassengerDialogActions.OnClick -> setState {
                copy(
                    multiCityData = multiCityData.mapIndexed { index, trip ->
                    if (index == tripIndex) {
                        trip.copy(
                            baseTabState = trip.baseTabState.copy(
                                isPassengerBottomSheetExpanded = true
                            )
                        )
                    } else trip
                    } as MutableList<SingleTripState>
                )
            }
        }
    }

    private fun onItemSelected(positionItem: Int , tripIndex: Int) {
        setState {
            copy(
                multiCityData = multiCityData.mapIndexed { index, trip ->
                    if (index == tripIndex) {
                        trip.copy(
                            baseTabState = trip.baseTabState.copy(
                                selectedClassPosition = positionItem
                            )
                        )
                    } else trip
                } as MutableList<SingleTripState>
            )
        }

    }

    private fun onDropDownClicked(tripIndex: Int) {
        setClassDropDownValue(tripIndex , true)
    }

    private fun onDismissRequest(tripIndex: Int) {
        setClassDropDownValue(tripIndex , false)
    }

    private fun setClassDropDownValue(tripIndex: Int , isClassDropDownExpanded : Boolean) {
        setState {
            copy(
                multiCityData = multiCityData.mapIndexed { index, trip ->
                    if (index == tripIndex) {
                        trip.copy(
                            baseTabState = trip.baseTabState.copy(
                                isClassDropDownExpanded = isClassDropDownExpanded
                            )
                        )
                    } else trip
                } as MutableList<SingleTripState>
            )
        }
    }

    private fun generateListOfPassengers() : List<PassengerUiModel>{
        return  listOf(
            PassengerUiModel(
                "Adults",
                "18-64",
                1,
            ),
            PassengerUiModel(
                "Seniors",
                "over 65",
                0,

                ),
            PassengerUiModel(
                "Youth",
                "12-17",
                0,

                ),
            PassengerUiModel(
                "Children",
                "2-11",
                0
            ),
            PassengerUiModel(
                "Toddlers in own seat",
                "under 2",
                0,

                ),
            PassengerUiModel(
                "Infants on lap",
                "under 2",
                0,
            ),

            )

    }

    private fun generateListOfClasses() : List<String>{
        return  listOf("Premium Economy","Economy","Business","First")

    }
}