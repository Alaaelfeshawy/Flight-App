package example.alaa.home.ui.component.tabs.singletrip

import dagger.hilt.android.lifecycle.HiltViewModel
import example.alaa.base.BaseViewModel
import example.alaa.home.ui.DatePickerActions
import example.alaa.home.ui.DropDownListActions
import example.alaa.home.ui.PassengerDialogActions
import example.alaa.home.ui.component.tabs.BaseTabState
import example.alaa.home.ui.component.tabs.TabEvent
import example.alaa.home.ui.model.Action
import example.alaa.home.ui.model.FieldValidation
import example.alaa.home.ui.model.PassengerUiModel
import example.alaa.home.ui.model.SearchModel
import example.alaa.home.ui.model.TripType
import javax.inject.Inject

@HiltViewModel
class SingleTripViewModel @Inject constructor(): BaseViewModel<
        SingleTripIntent, TabState, TabEvent>() {
    override val initialState: TabState
        get() = TabState(
            singleTripState = SingleTripState(
                baseTabState = BaseTabState(
                    passengers = generateListOfPassengers(),
                    planeClasses = generateListOfClasses(),
                )
            ) ,
            roundedTripState = SingleTripState(
                baseTabState = BaseTabState(
                    passengers = generateListOfPassengers(),
                    planeClasses = generateListOfClasses(),
                )
            ) ,
            mutliCityTripState = listOf()

        )

    init {
        validateTotalNumberOfTravellers()
    }

    override fun handleIntent(intent: SingleTripIntent) {
        when (intent) {
            is SingleTripIntent.ValidateDestinationField -> validateDestinationField(intent.value)
            is SingleTripIntent.ValidateOriginField -> validateOriginField(intent.value)
            is SingleTripIntent.ExchangeFields -> exchangeFields(intent.origin, intent.destination)
            is SingleTripIntent.ValidatePassengerCount -> validatePassengerCount(intent.action , intent.count , intent.index)
            is SingleTripIntent.ConfirmNumOfPassenger -> confirmNumOfPassenger()
            is SingleTripIntent.Search -> search()
            is SingleTripIntent.SetTripType -> setState {
                copy(
                    tripType = intent.tripType
                )
            }
        }
    }

    private fun search() {
        validateForm()
        val currentState = state.value
        val searchModel = getSearchParamsForTripType(currentState.tripType)
        if (searchModel != null) {
            sendEvent(TabEvent.NavigateToSearch(searchModel))
        }
    }

    private fun getSearchParamsForTripType(tripType: TripType): SearchModel? {
        val currentState = state.value
        return when (tripType) {
            TripType.SINGLE_TRIP -> {
                if (currentState.singleTripState.isFormValid) {
                    currentState.singleTripState.prepareSearchModel(TripType.SINGLE_TRIP)
                } else {
                    null
                }
            }
            TripType.ROUNDED_TRIP -> {
                if (currentState.roundedTripState.isFormValid) {
                    currentState.roundedTripState.prepareSearchModel(TripType.SINGLE_TRIP)
                } else {
                    null
                }
            }
            else -> null
        }
    }

    private fun SingleTripState.prepareSearchModel(tripType: TripType) : SearchModel {
       return SearchModel(
            tripType = tripType,
            origin = origin.value.orEmpty(),
            destination = destination.value.orEmpty(),
            selectedStartDate = date.selectedStartDate,
            selectedEndDate = date.selectedEndDate.orEmpty(),
            selectedPassenger = baseTabState.passengers[baseTabState.selectedPassengerIndex],
            selectedClass = baseTabState.planeClasses[baseTabState.selectedClassPosition]
        )
    }

    fun handleDatePickerActions(action: DatePickerActions) {
        when (action) {
            is DatePickerActions.OnClick -> handleOnClickDatePicker(action)
            is DatePickerActions.OnDateSelected -> handleOnDateSelected(action)
        }
    }

    private fun handleOnDateSelected(action: DatePickerActions.OnDateSelected) {
        setState {
            when (state.value.tripType) {
                TripType.SINGLE_TRIP -> copy(singleTripState = singleTripState.updateDates(action.selectedStartDate , action.selectedEndDate))
                TripType.ROUNDED_TRIP -> copy(roundedTripState = roundedTripState.updateDates(action.selectedStartDate, action.selectedEndDate))
                TripType.MULTI_CITY_TRIP -> TODO()
            }
        }
    }

    private fun SingleTripState.updateDates(selectedStartDate : String, selectedEndDate : String?): SingleTripState {
        return this.copy(
            date =this.date.copy(
                selectedStartDate = selectedStartDate,
                selectedEndDate = selectedEndDate,
            )
        )
    }

    private fun handleOnClickDatePicker(action: DatePickerActions.OnClick) {
        setState {
            when (tripType) {
                TripType.SINGLE_TRIP -> copy(singleTripState = singleTripState.updateOnClick(action.isClicked))
                TripType.ROUNDED_TRIP -> copy(roundedTripState = roundedTripState.updateOnClick(action.isClicked))
                TripType.MULTI_CITY_TRIP -> TODO()
            }
        }
    }

    private fun SingleTripState.updateOnClick(isClicked : Boolean): SingleTripState {
        return copy(
            date =  date.copy(
               showDatePicker = isClicked
            )
        )
    }

    private fun confirmNumOfPassenger() {
        setState {
            val newNumOfPassengers = when (tripType) {
                TripType.SINGLE_TRIP -> singleTripState.baseTabState.passengers.sumOf { it.numberOfTravellers }
                TripType.ROUNDED_TRIP -> roundedTripState.baseTabState.passengers.sumOf { it.numberOfTravellers }
                TripType.MULTI_CITY_TRIP -> TODO()
            }
            when (tripType) {
                TripType.SINGLE_TRIP -> copy(singleTripState = singleTripState.updateNumOfPassengers(newNumOfPassengers,false))
                TripType.ROUNDED_TRIP -> copy(roundedTripState = roundedTripState.updateNumOfPassengers( newNumOfPassengers,  false))
                TripType.MULTI_CITY_TRIP -> TODO()
            }
        }
    }

    private fun SingleTripState.updateNumOfPassengers(newNumOfPassengers : Int, isPassengerBottomSheetExpanded : Boolean): SingleTripState {
        return this.copy(
           baseTabState = baseTabState.copy(
               numOfPassengers = newNumOfPassengers,
               isPassengerBottomSheetExpanded = isPassengerBottomSheetExpanded
           )
        )
    }

    private fun validatePassengerCount(action: Action, count: Int, index : Int) {
        when(action){
            Action.DECREASE -> {
                if (count in 1..9){
                    val result = count.minus(1)
                    updateActualNumber(index, result)
                }

            }
            Action.INCREASE -> {
                if (count < 9){
                    val result = count.plus(1)
                    updateActualNumber(index, result)
                }

            }
        }
    }

    private fun updateActualNumber(index: Int, result: Int) {
        setState {
            when (tripType) {
                TripType.SINGLE_TRIP -> copy(singleTripState = singleTripState.updateNumberOfTravellers(index,result))
                TripType.ROUNDED_TRIP -> copy(roundedTripState = roundedTripState.updateNumberOfTravellers(index,result))
                TripType.MULTI_CITY_TRIP -> TODO()
            }
        }
        validateTotalNumberOfTravellers()
    }

    private fun SingleTripState.updateNumberOfTravellers(index: Int, result: Int) : SingleTripState {
        return copy(
            baseTabState = this.baseTabState.copy(
                selectedPassengerIndex = index,
                passengers = this.baseTabState.passengers.mapIndexed { currIndex, passenger ->
                    if (currIndex == index) {
                        passenger.copy(numberOfTravellers = result)
                    } else {
                        passenger
                    }
                },
            )
        )
    }

    private fun validateTotalNumberOfTravellers(){
        setState {
            val numOfTravellers = when (tripType) {
                TripType.SINGLE_TRIP -> singleTripState.baseTabState.passengers.sumOf { it.numberOfTravellers }
                TripType.ROUNDED_TRIP -> roundedTripState.baseTabState.passengers.sumOf { it.numberOfTravellers }
                TripType.MULTI_CITY_TRIP -> TODO()
            }
            when (tripType) {
                TripType.SINGLE_TRIP -> copy(singleTripState = singleTripState.validateTotalNumberOfTravellers(numOfTravellers))
                TripType.ROUNDED_TRIP -> copy(roundedTripState = roundedTripState.validateTotalNumberOfTravellers(numOfTravellers))
                TripType.MULTI_CITY_TRIP -> TODO()
            }
        }

    }

    private fun SingleTripState.validateTotalNumberOfTravellers(numOfTravellers : Int): SingleTripState {
        return copy(
            baseTabState = this.baseTabState.copy(
                    isTotalPassengerCountValid = numOfTravellers in 1..16,
                    invalidTotalPassengerCountErrorMessage = if (numOfTravellers > 16)
                        "cannot have more than 16 travellers"
                    else "please select on or more passenger"
                ),
        )
    }

    private fun exchangeFields(origin: String?, destination: String?) {
        if (!destination.isNullOrEmpty() && !origin.isNullOrEmpty()) {
            val destinationValidation = isValid(destination)
            val originValidation = isValid(origin)
            updateFieldValidation( "origin", destination, destinationValidation.first, destinationValidation.second)
            updateFieldValidation("destination", origin, originValidation.first, originValidation.second)
        }
    }

    private fun validateOriginField(value: String) {
        val valueValidation = isValid(value)
        updateFieldValidation("origin", value, valueValidation.first, valueValidation.second)
    }

    private fun validateDestinationField(value: String) {
        val valueValidation = isValid(value)
        updateFieldValidation("destination", value, valueValidation.first, valueValidation.second)
    }

    private fun updateFieldValidation(
        field: String, // "origin" or "destination"
        value: String,
        isValid: Boolean,
        errorMessage: String?
    ) {
        setState {
            when (tripType) {
                TripType.SINGLE_TRIP -> copy(
                    singleTripState = singleTripState.copy(
                        origin = if (field == "origin") FieldValidation(value, isValid, errorMessage) else singleTripState.origin,
                        destination = if (field == "destination") FieldValidation(value, isValid, errorMessage) else singleTripState.destination
                    )
                )
                TripType.ROUNDED_TRIP -> copy(roundedTripState = roundedTripState.copy(
                    origin = if (field == "origin") FieldValidation(value, isValid, errorMessage) else roundedTripState.origin,
                    destination = if (field == "destination") FieldValidation(value, isValid, errorMessage) else roundedTripState.destination
                ))
                TripType.MULTI_CITY_TRIP -> TODO()
            }
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
            when (tripType) {
                TripType.SINGLE_TRIP -> copy(singleTripState = singleTripState.validateForm())
                TripType.ROUNDED_TRIP -> copy(roundedTripState = roundedTripState.validateForm())
                TripType.MULTI_CITY_TRIP -> TODO()
            }
        }
    }

    private fun SingleTripState.validateForm(): SingleTripState {
        val originValid = isValid(origin.value)
        val destinationValid = isValid(destination.value)
        val isValidDate = date.selectedStartDate.isNotEmpty()
       return this.copy(
           origin = origin.copy(
               isValid = originValid.first,
               errorMessage = originValid.second
           ),
           destination = destination.copy(
               isValid = destinationValid.first,
               errorMessage = destinationValid.second
           ),
           date = date.copy(
               isValid = isValidDate,
               dateErrorMessage = if (!isValidDate) "Departure date couldn't be empty" else null
           ),
           isFormValid = originValid.first && destinationValid.first && isValidDate
       )
    }

    fun handleActions(action: DropDownListActions) {
        when (action) {
            is DropDownListActions.OnDismissRequest -> updateDropDownRequests(isClassDropDownExpanded = false)
            is DropDownListActions.OnDropDownClicked -> updateDropDownRequests(isClassDropDownExpanded = true)
            is DropDownListActions.OnItemSelected -> onItemSelected(action.item)
        }
    }

    fun handleDialogActions(actions: PassengerDialogActions) {
        when (actions) {
            PassengerDialogActions.OnClick -> setState {
                when (tripType) {
                    TripType.SINGLE_TRIP -> copy(
                        singleTripState = singleTripState.copy(
                            baseTabState = singleTripState.baseTabState.copy(
                                isPassengerBottomSheetExpanded = true
                            ),
                        )
                    )

                    TripType.ROUNDED_TRIP -> copy(
                        roundedTripState = roundedTripState.copy(
                            baseTabState = roundedTripState.baseTabState.copy(
                                isPassengerBottomSheetExpanded = true
                            ),
                        )
                    )

                    TripType.MULTI_CITY_TRIP -> TODO()
                }
            }
        }
    }

    private fun onItemSelected(positionItem: Int) {
        setState {
            when (tripType) {
                TripType.SINGLE_TRIP -> copy(
                    singleTripState = singleTripState.copy(
                        baseTabState = singleTripState.baseTabState.copy(
                            selectedClassPosition = positionItem
                        ),
                    )
                )

                TripType.ROUNDED_TRIP -> copy(
                    roundedTripState = roundedTripState.copy(
                        baseTabState = roundedTripState.baseTabState.copy(
                            selectedClassPosition = positionItem
                        ),
                    )
                )

                TripType.MULTI_CITY_TRIP -> TODO()
            }
        }
    }

    private fun updateDropDownRequests(isClassDropDownExpanded : Boolean){
        setState {
            when (tripType) {
                TripType.SINGLE_TRIP -> copy(
                    singleTripState = singleTripState.setDropDownValue(isClassDropDownExpanded)
                )

                TripType.ROUNDED_TRIP -> copy(
                    roundedTripState = roundedTripState.setDropDownValue(isClassDropDownExpanded)
                )

                TripType.MULTI_CITY_TRIP -> TODO()
            }
        }

    }

   private fun SingleTripState.setDropDownValue(isClassDropDownExpanded : Boolean): SingleTripState {
      return copy(
           baseTabState = this.baseTabState.copy(
               isClassDropDownExpanded = isClassDropDownExpanded
           ),
       )
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