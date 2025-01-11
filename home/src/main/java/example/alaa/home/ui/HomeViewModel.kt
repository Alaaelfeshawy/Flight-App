package example.alaa.home.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import example.alaa.base.BaseViewModel
import example.alaa.home.R
import example.alaa.home.ui.model.OffersUiModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeIntent, HomeState, Nothing>(){
    override val initialState: HomeState
        get() = HomeState(
            offers = generateListOfOffers(),
            tabs = generateListOfTabs()
     )

    private fun generateListOfTabs() = listOf("One way", "Round Trip", "Multi-city")

    override fun handleIntent(intent: HomeIntent) {
      when(intent){
          HomeIntent.Search -> collectData()
          is HomeIntent.SetSelectedTab -> setSelectedTab(intent.selectedTab)
      }
    }

    private fun setSelectedTab(selectedTab: Int) {
        setState {
            copy(
                selectedTab = selectedTab
            )
        }
    }

    private fun generateListOfOffers() : List<OffersUiModel>{
        return listOf(
            OffersUiModel(
                logo = R.drawable.mastercard_logo,
                backgroundColor = example.alaa.base.R.color.white_01,
                cardName = "mastercard",
                offer = 20.0
            ),
            OffersUiModel(
                logo = R.drawable.visa_logo,
                backgroundColor = example.alaa.base.R.color.blue_01,
                cardName = "visa",
                offer = 25.0
            ),
        )
    }

    private fun collectData() {
    }

}