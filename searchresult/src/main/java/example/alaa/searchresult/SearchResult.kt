package example.alaa.searchresult

import example.alaa.base.ViewEvent
import example.alaa.base.ViewIntent
import example.alaa.base.ViewState

data class SearchResultState(
    val selectedTab : Int = 0,
    val tabs : List<String>
) : ViewState


sealed class SearchResultIntent : ViewIntent{
    data class SetSelected(val selectedTab : Int) : SearchResultIntent()
}

sealed class SearchResultEvent : ViewEvent{

}