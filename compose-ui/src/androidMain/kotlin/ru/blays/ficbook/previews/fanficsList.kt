package ru.blays.ficbook.components.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import ru.blays.ficbook.components.fanficsList.FanficsListContent
import ru.blays.ficbook.reader.shared.components.fanficListComponents.declaration.FanficsListComponent
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent
import ru.blays.ficbook.reader.shared.data.dto.*

private val fakeFanficCard = FanficCardModelStable(
    href = "fanfic/1",
    id = "1",
    title = "Название фанфика: Длинное и красивое название",
    status = FanficStatusStable(
        direction = FanficDirection.GEN,
        rating = FanficRating.G,
        status = FanficCompletionStatus.IN_PROGRESS,
        hot = false,
        likes = 42,
        trophies = 5
    ),
    author = UserModelStable(name = "Автор123", href = "authors/1", avatarUrl = ""),
    originalAuthor = null,
    fandoms = listOf(FandomModelStable(href = "fandoms/1", name = "Фэндом")),
    pairings = listOf(PairingModelStable(character = "Персонаж А", href = "", isHighlighted = false)),
    updateDate = "10.04.2026",
    size = "100 000 знаков",
    readInfo = null,
    tags = listOf(
        FanficTagStable(name = "Тег 1", isAdult = false, href = ""),
        FanficTagStable(name = "Тег 2", isAdult = false, href = "")
    ),
    description = "Краткое описание фанфика. Это очень интересная история о приключениях и дружбе.",
    coverUrl = ""
)

private val fakeFanficsListState = FanficsListComponent.State(
    section = SectionWithQuery(name = "Все фанфики", href = ""),
    list = listOf(fakeFanficCard, fakeFanficCard, fakeFanficCard),
    isLoading = false
)

private val fakeFanficsListLoadingState = FanficsListComponent.State(
    section = SectionWithQuery(name = "Все фанфики", href = ""),
    list = emptyList(),
    isLoading = true
)

@Preview(showBackground = true)
@Composable
private fun FanficsListContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            FanficsListContent(
                state = fakeFanficsListState,
                onIntent = {},
                onOutput = {},
                quickActionsFactory = { _, _ -> throw NotImplementedError() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FanficsListContentLoadingPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            FanficsListContent(
                state = fakeFanficsListLoadingState,
                onIntent = {},
                onOutput = {},
                quickActionsFactory = { _, _ -> throw NotImplementedError() }
            )
        }
    }
}
