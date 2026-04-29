package ru.blays.ficbook.reader.shared.components.searchComponents.declaration

import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.blays.ficbook.reader.shared.data.SearchedFandomModel

interface SearchFandomsComponent {
    val state: Value<State>

    fun sendIntent(intent: Intent)

    sealed class Intent {
        data class SelectFandom(val select: Boolean, val fandom: SearchedFandomModel) : Intent()
        data class ExcludeFandom(val exclude: Boolean, val fandom: SearchedFandomModel) : Intent()
        data class ChangeSearchedName(val name: String) : Intent()
        data object Clear : Intent()
    }

    @Serializable
    data class State(
        val searchedName: String,
        val searchedFandoms: Set<SearchedFandomModel>,
        val selectedFandoms: Set<SearchedFandomModel>,
        val excludedFandoms: Set<SearchedFandomModel>
    )
}