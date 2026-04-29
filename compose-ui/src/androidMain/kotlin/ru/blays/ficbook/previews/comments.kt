package ru.blays.ficbook.components.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import ru.blays.ficbook.components.commentsContent.CommentsContent
import ru.blays.ficbook.reader.shared.components.commentsComponent.declaration.CommentsComponent
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent
import ru.blays.ficbook.reader.shared.data.dto.*

private val fakeCommentsState = CommentsComponent.State(
    loading = false,
    error = false,
    errorMessage = null,
    comments = listOf(
        CommentModelStable(
            commentID = "1",
            user = UserModelStable(name = "Пользователь1", href = "authors/1", avatarUrl = ""),
            isOwnComment = false,
            isLiked = false,
            likedBy = emptyList(),
            date = "10.04.2026 12:00",
            blocks = listOf(
                CommentBlockModelStable(
                    quote = null,
                    text = "Отличный фанфик! Очень понравилось, продолжайте в том же духе."
                )
            ),
            likes = 5,
            forFanfic = null
        ),
        CommentModelStable(
            commentID = "2",
            user = UserModelStable(name = "Читатель42", href = "authors/2", avatarUrl = ""),
            isOwnComment = true,
            isLiked = true,
            likedBy = emptyList(),
            date = "09.04.2026 18:30",
            blocks = listOf(
                CommentBlockModelStable(
                    quote = QuoteModelStable(
                        quote = null,
                        userName = "Автор",
                        text = "Спасибо за отзыв!"
                    ),
                    text = "Согласен, жду продолжения!"
                )
            ),
            likes = 2,
            forFanfic = null
        )
    )
)

@Preview(showBackground = true)
@Composable
private fun CommentsContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            CommentsContent(
                state = fakeCommentsState,
                onIntent = {},
                onOutput = {}
            )
        }
    }
}
