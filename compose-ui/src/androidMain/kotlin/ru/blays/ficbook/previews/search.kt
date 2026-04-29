package ru.blays.ficbook.components.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import ru.blays.ficbook.components.searchContent.*
import ru.blays.ficbook.reader.shared.components.fanficListComponents.declaration.FanficQuickActionsComponent
import ru.blays.ficbook.reader.shared.components.fanficListComponents.declaration.FanficsListComponent
import ru.blays.ficbook.reader.shared.components.searchComponents.declaration.*
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent
import ru.blays.ficbook.reader.shared.data.*
import ru.blays.ficbook.reader.shared.data.dto.SectionWithQuery

private val fakeFandomsState = SearchFandomsComponent.State(
    searchedName = "Гарри",
    searchedFandoms = setOf(
        SearchedFandomModel(title = "Гарри Поттер", description = "Книги Дж. Роулинг", fanficsCount = 12345, id = "1"),
        SearchedFandomModel(title = "Гарри Стайлс", description = "Real person fiction", fanficsCount = 34, id = "2"),
    ),
    selectedFandoms = setOf(
        SearchedFandomModel(title = "Властелин Колец", description = "Толкин", fanficsCount = 4321, id = "3")
    ),
    excludedFandoms = emptySet()
)

private val fakeTagsState = SearchTagsComponent.State(
    searchedName = "ангст",
    searchedTags = setOf(
        SearchedTagModel(title = "Ангст", description = "Тяжёлые переживания", usageCount = 8900, isAdult = false, id = "1"),
        SearchedTagModel(title = "Лёгкий ангст", description = "", usageCount = 3400, isAdult = false, id = "2"),
    ),
    selectedTags = setOf(
        SearchedTagModel(title = "Романтика", description = "", usageCount = 15000, isAdult = false, id = "3")
    ),
    excludedTags = setOf(
        SearchedTagModel(title = "AU", description = "Альтернативная вселенная", usageCount = 7600, isAdult = false, id = "4")
    ),
    behavior = SearchParams.TAGS_ANY_SELECTED
)

private val fakePairingsState = SearchPairingsComponent.State(
    searchedCharacters = listOf(
        SearchedCharactersGroup(
            fandomName = "Гарри Поттер",
            characters = listOf(
                SearchedCharacterModel(fandomId = "1", id = "1", name = "Гарри Поттер", aliases = emptyList()),
                SearchedCharacterModel(fandomId = "1", id = "2", name = "Гермиона Грейнджер", aliases = emptyList()),
                SearchedCharacterModel(fandomId = "1", id = "3", name = "Северус Снейп", aliases = listOf("Принц-полукровка")),
            )
        )
    ),
    buildedPairing = null,
    selectedPairings = emptySet(),
    excludedPairings = emptySet(),
    loading = false,
    error = false,
    errorMessage = null
)

private val fakeSavedState = SearchSaveComponent.State(saved = emptyList())

private val fakeFanficsListState = FanficsListComponent.State(
    section = SectionWithQuery(name = "Поиск", href = ""),
    list = emptyList(),
    isLoading = false
)

private val fakeQuickActionsFactory: (String, String) -> FanficQuickActionsComponent =
    { _, _ -> throw NotImplementedError() }

@Preview(showBackground = true)
@Composable
private fun SearchContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            SearchContent(
                state = SearchParams.Default,
                onSearchIntent = {},
                fandomsState = fakeFandomsState,
                onFandomsIntent = {},
                tagsState = fakeTagsState,
                onTagsIntent = {},
                pairingsState = fakePairingsState,
                defaultCharacterModifiers = emptyArray(),
                onPairingsIntent = {},
                savedState = fakeSavedState,
                onSavedIntent = {},
                fanficsListState = fakeFanficsListState,
                onFanficsIntent = {},
                onFanficsOutput = {},
                quickActionsFactory = fakeQuickActionsFactory,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchMenuRootPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            SearchMenuRoot(
                state = SearchParams.Default,
                onSearchIntent = {},
                fandomsState = fakeFandomsState,
                onFandomsIntent = {},
                tagsState = fakeTagsState,
                onTagsIntent = {},
                pairingsState = fakePairingsState,
                defaultCharacterModifiers = emptyArray(),
                onPairingsIntent = {},
                savedState = fakeSavedState,
                onSavedIntent = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FindFandomDialogPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            FindFandomDialog(
                state = fakeFandomsState,
                onIntent = {},
                onDismiss = {},
                onSelected = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FindTagDialogPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            FindTagDialog(
                state = fakeTagsState,
                onIntent = {},
                onDismiss = {},
                onSelected = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectCharacterDialogPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            SelectCharacterDialog(
                state = fakePairingsState,
                defaultModifiers = emptyArray(),
                onIntent = {},
                onDismiss = {}
            )
        }
    }
}
