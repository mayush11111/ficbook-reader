package ru.blays.ficbook.reader.shared.components.searchComponents.declaration

import com.arkivanov.decompose.value.Value
import ru.blays.ficbook.reader.shared.components.fanficListComponents.declaration.FanficsListComponent
import ru.blays.ficbook.reader.shared.data.IntRangeSimple
import ru.blays.ficbook.reader.shared.data.SearchParams

interface SearchComponent {
    val state: Value<SearchParams>

    val fanficsListComponent: FanficsListComponent
    val searchFandomsComponent: SearchFandomsComponent
    val searchTagsComponent: SearchTagsComponent
    val searchCharactersComponent: SearchPairingsComponent
    val savedSearchesComponent: SearchSaveComponent

    fun sendIntent(intent: Intent)

    sealed class Intent {
        data object Search : Intent()
        data object Clear : Intent()
        data class SetTitle(val value: String) : Intent()
        data class SetSearchOriginals(val value: Boolean) : Intent()
        data class SetSearchFanfics(val value: Boolean) : Intent()
        data class SetPagesCountRange(val value: IntRangeSimple) : Intent()
        data class SetStatus(val value: List<Int>) : Intent()
        data class SetRating(val value: List<Int>) : Intent()
        data class SetDirection(val value: List<Int>) : Intent()
        data class SetOnlyTranslations(val value: Boolean) : Intent()
        data class SetOnlyPremium(val value: Boolean) : Intent()
        data class SetLikesRange(val value: IntRangeSimple) : Intent()
        data class SetMinRewards(val value: Int) : Intent()
        data class SetMinComments(val value: Int) : Intent()
        data class SetDateRange(val value: LongRange) : Intent()
        data class SetFilterReaded(val value: Boolean) : Intent()
        data class SetSort(val value: Int) : Intent()
    }

    sealed class Output {
        data object NavigateBack: Output()
    }
}