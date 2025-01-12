package example.alaa.searchresult

import dagger.hilt.android.lifecycle.HiltViewModel
import example.alaa.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor() : BaseViewModel<SearchResultIntent,SearchResultState,SearchResultEvent>() {
    override val initialState: SearchResultState
        get() = SearchResultState(
            tabs = listOf(
                "All",
                "Lowest - Highest",
                "Highest - Lowest",
                "Discount",
            )
        )

    override fun handleIntent(intent: SearchResultIntent) {
        when(intent){
            is SearchResultIntent.SetSelected -> setSelectedTab(intent.selectedTab)
        }
    }

    private fun setSelectedTab(selectedTab: Int) {
        setState {
            copy(
                selectedTab = selectedTab
            )
        }
    }
}