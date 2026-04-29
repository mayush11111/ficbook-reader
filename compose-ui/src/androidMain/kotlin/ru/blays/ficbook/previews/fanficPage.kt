package ru.blays.ficbook.components.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.blays.ficbook.components.fanficPage.FanficPageInfoContent
import ru.blays.ficbook.reader.shared.components.fanficPageComponents.declaration.FanficPageActionsComponent
import ru.blays.ficbook.reader.shared.components.fanficPageComponents.declaration.FanficPageCollectionsComponent
import ru.blays.ficbook.reader.shared.components.fanficPageComponents.declaration.FanficPageInfoComponent
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent
import ru.blays.ficbook.reader.shared.data.dto.*

private val fakeFanficPage = FanficPageModelStable(
    fanficID = "1",
    name = "Название фанфика: очень длинное и красивое",
    status = FanficStatusStable(
        direction = FanficDirection.GEN,
        rating = FanficRating.G,
        status = FanficCompletionStatus.IN_PROGRESS,
        hot = true,
        likes = 150,
        trophies = 10
    ),
    authors = listOf(
        FanficAuthorModelStable(
            user = UserModelStable(name = "Автор", href = "authors/1", avatarUrl = ""),
            role = "Автор"
        )
    ),
    fandoms = listOf(FandomModelStable(href = "", name = "Фэндом 1")),
    pairings = listOf(
        PairingModelStable(character = "Персонаж А", href = "", isHighlighted = false),
        PairingModelStable(character = "Персонаж Б", href = "", isHighlighted = true)
    ),
    tags = listOf(
        FanficTagStable(name = "Тег 1", isAdult = false, href = ""),
        FanficTagStable(name = "Тег 2", isAdult = false, href = "")
    ),
    coverUrl = "",
    description = "Это подробное описание фанфика. Здесь рассказывается о сюжете, персонажах и мире произведения. Очень интересная история.",
    dedication = null,
    authorComment = null,
    publicationRules = "Правила публикации",
    subscribersCount = 230,
    commentCount = 47,
    pagesCount = 5,
    liked = false,
    subscribed = true,
    inCollectionsCount = 12,
    chapters = FanficChapterStable.SeparateChaptersModel(
        chapters = listOf(
            FanficChapterStable.SeparateChaptersModel.Chapter(
                chapterID = "ch1",
                href = "chapter/1",
                name = "Глава 1: Начало",
                date = "01.01.2026",
                commentsCount = 10,
                lastWatchedCharIndex = 0,
                readed = true
            ),
            FanficChapterStable.SeparateChaptersModel.Chapter(
                chapterID = "ch2",
                href = "chapter/2",
                name = "Глава 2: Продолжение",
                date = "15.01.2026",
                commentsCount = 5,
                lastWatchedCharIndex = 0,
                readed = false
            )
        ),
        chaptersCount = 2
    ),
    rewards = listOf(
        RewardModelStable(
            message = "Отличная работа!",
            fromUser = "Читатель",
            awardDate = "05.02.2026"
        )
    )
)

@Suppress("UNCHECKED_CAST")
private val fakeActionsComponent: FanficPageActionsComponent = object : FanficPageActionsComponent {
    override val state: Value<FanficPageActionsComponent.State> = MutableValue(
        FanficPageActionsComponent.State(follow = true, mark = false)
    )
    override val slot: Value<ChildSlot<*, FanficPageCollectionsComponent>> =
        MutableValue(ChildSlot<Any, FanficPageCollectionsComponent>()) as Value<ChildSlot<*, FanficPageCollectionsComponent>>

    override fun sendIntent(intent: FanficPageActionsComponent.Intent) {}
    override fun onOutput(output: FanficPageActionsComponent.Output) {}
}

private val fakeInfoComponent: FanficPageInfoComponent = object : FanficPageInfoComponent {
    override val state: Value<FanficPageInfoComponent.State> = MutableValue(
        FanficPageInfoComponent.State(
            fanfic = fakeFanficPage,
            reverseOrderEnabled = false,
            isLoading = false
        )
    )
    override val fanficHref: String = "fanfic/1"
    override val actionsComponent: FanficPageActionsComponent = fakeActionsComponent

    override fun sendIntent(intent: FanficPageInfoComponent.Intent) {}
    override fun onOutput(output: FanficPageInfoComponent.Output) {}
}

@Preview(showBackground = true)
@Composable
private fun FanficPageInfoContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            FanficPageInfoContent(component = fakeInfoComponent)
        }
    }
}
