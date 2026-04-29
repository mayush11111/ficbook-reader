package ru.blays.ficbook.components.previews

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import ru.blays.ficbook.components.notifications.NotificationsContent
import ru.blays.ficbook.reader.shared.components.notificationComponents.NotificationComponent
import ru.blays.ficbook.reader.shared.data.dto.NotificationCategoryStable
import ru.blays.ficbook.reader.shared.data.dto.NotificationModelStable
import ru.blays.ficbook.reader.shared.data.dto.NotificationType
import ru.blays.ficbook.components.previews.AppThemePreview
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent


@Preview(showBackground = true)
@Composable
private fun NotificationsContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    val state = NotificationComponent.State(
        list = listOf(
            NotificationModelStable(
                href = "href1",
                title = "Новый комментарий",
                date = "12.10.2023 15:30",
                text = "Пользователь User123 оставил комментарий к вашей работе \u0027Заголовок работы\u0027",
                readed = false,
                type = NotificationType.NEW_COMMENTS
            ),
            NotificationModelStable(
                href = "href2",
                title = "Обновление в фанфике",
                date = "12.10.2023 14:00",
                text = "Вышла новая глава в фанфике \u0027Другой заголовок\u0027",
                readed = true,
                type = NotificationType.UPDATES_IN_FANFICS
            )
        ),
        availableCategories = listOf(
            NotificationCategoryStable(NotificationType.ALL_NOTIFICATIONS, 10),
            NotificationCategoryStable(NotificationType.NEW_COMMENTS, 5),
            NotificationCategoryStable(NotificationType.UPDATES_IN_FANFICS, 5)
        ),
        selectedCategory = NotificationType.ALL_NOTIFICATIONS,
        loading = false,
        error = false,
        errorMessage = null
    )

    AppThemePreview(state = theme) {
        Surface {
            NotificationsContent(
                state = state,
                slotInstance = null,
                onIntent = {},
                onOutput = {},
                lazyListState = rememberLazyListState()
            )
        }
    }
}
