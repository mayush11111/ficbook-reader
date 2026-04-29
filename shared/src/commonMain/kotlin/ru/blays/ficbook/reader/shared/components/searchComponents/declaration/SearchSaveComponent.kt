package ru.blays.ficbook.reader.shared.components.searchComponents.declaration

import com.arkivanov.decompose.value.Value
import ru.blays.ficbook.reader.shared.data.SearchParamsEntityShortcut

interface SearchSaveComponent {
    val state: Value<State>

    fun sendIntent(intent: Intent)

    sealed class Intent {
        data class Save(val name: String, val description: String) : Intent()
        data class Delete(val shortcut: SearchParamsEntityShortcut) : Intent()
        data class Select(val shortcut: SearchParamsEntityShortcut) : Intent()
        data class Update(val shortcut: SearchParamsEntityShortcut, val newName: String, val newDescription: String, val updateParams: Boolean) : Intent()
    }

    data class State(
        val saved: List<SearchParamsEntityShortcut>
    )
}