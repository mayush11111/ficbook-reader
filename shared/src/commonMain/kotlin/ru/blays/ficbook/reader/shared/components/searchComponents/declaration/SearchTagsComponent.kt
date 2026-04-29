package ru.blays.ficbook.reader.shared.components.searchComponents.declaration

import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.blays.ficbook.reader.shared.data.SearchedTagModel

interface SearchTagsComponent {
    val state: Value<State>

    fun sendIntent(intent: Intent)

    sealed class Intent {
        data class SelectTag(val select: Boolean, val tag: SearchedTagModel) : Intent()
        data class ExcludeTag(val exclude: Boolean, val tag: SearchedTagModel) : Intent()
        data class ChangeSearchedName(val name: String) : Intent()
        data class ChangeSearchBehavior(val behavior: Int) : Intent()
        data object Clear : Intent()
    }

    @Serializable
    data class State(
        val searchedName: String,
        val searchedTags: Set<SearchedTagModel>,
        val selectedTags: Set<SearchedTagModel>,
        val excludedTags: Set<SearchedTagModel>,
        val behavior: Int
    )
}