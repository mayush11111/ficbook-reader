package ru.blays.ficbook.reader.shared.components.searchComponents.declaration

import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.blays.ficbook.reader.shared.data.SearchedCharacterModel
import ru.blays.ficbook.reader.shared.data.SearchedCharactersGroup
import ru.blays.ficbook.reader.shared.data.SearchedPairingModel

interface SearchPairingsComponent {
    val state: Value<State>

    val defaultCharacterModifiers: Array<String>

    fun sendIntent(intent: Intent)

    sealed class Intent {
        data class SelectPairing(val select: Boolean, val pairing: SearchedPairingModel) : Intent()
        data class ExcludePairing(val exclude: Boolean, val pairing: SearchedPairingModel) : Intent()
        data class AddCharacterToPairing(val character: SearchedCharacterModel) : Intent()
        data object ClearBuiltPairing : Intent()
        data class ChangeCharacterModifier(val character: SearchedPairingModel.Character, val modifier: String) : Intent()
    }

    @Serializable
    data class State(
        val searchedCharacters: List<SearchedCharactersGroup>,
        val buildedPairing: SearchedPairingModel?,
        val selectedPairings: Set<SearchedPairingModel>,
        val excludedPairings: Set<SearchedPairingModel>,
        val loading: Boolean,
        val error: Boolean,
        val errorMessage: String?
    )
}

interface InternalSearchPairingsComponent: SearchPairingsComponent {
    fun update(fandomIDs: List<String>)

    fun excludeNotLinkedPairings(fandomIds: List<String>)

    fun clean()
}