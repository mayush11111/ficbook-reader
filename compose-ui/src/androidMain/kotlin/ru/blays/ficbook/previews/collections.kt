package ru.blays.ficbook.components.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import ru.blays.ficbook.components.collectionContent.CollectionsScreenContent
import ru.blays.ficbook.reader.shared.components.collectionComponents.declaration.CollectionsListComponent
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent
import ru.blays.ficbook.reader.shared.data.dto.CollectionCardModelStable
import ru.blays.ficbook.reader.shared.data.dto.UserModelStable

private val fakeCollectionsState = CollectionsListComponent.State(
    list = listOf(
        CollectionCardModelStable.Own(
            relativeID = "rel1",
            realID = "1",
            name = "Моя коллекция",
            size = 15,
            public = true
        ),
        CollectionCardModelStable.Own(
            relativeID = "rel2",
            realID = "2",
            name = "Приватная коллекция",
            size = 3,
            public = false
        ),
        CollectionCardModelStable.Other(
            relativeID = "rel3",
            realID = "3",
            name = "Чужая коллекция",
            size = 42,
            owner = UserModelStable(name = "Пользователь", href = "authors/1", avatarUrl = ""),
            subscribed = true
        )
    ),
    isLoading = false
)

@Preview(showBackground = true)
@Composable
private fun CollectionsScreenContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            CollectionsScreenContent(
                state = fakeCollectionsState,
                onIntent = {},
                onOutput = {}
            )
        }
    }
}
