package ru.blays.ficbook.components.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import ru.blays.ficbook.components.authorProfile.AuthorProfileContent
import ru.blays.ficbook.reader.shared.components.authorProfileComponents.declaration.AuthorProfileComponent
import ru.blays.ficbook.reader.shared.components.authorProfileComponents.implementation.DefaultAuthorFollowComponent
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent
import ru.blays.ficbook.reader.shared.data.dto.*

private val previewLifecycle = LifecycleRegistry()
private val previewComponentContext = DefaultComponentContext(lifecycle = previewLifecycle)

private val fakeProfile = AuthorProfileModelStable(
    authorMain = AuthorMainInfoStable(
        name = "Имя Автора",
        realID = "123",
        relativeID = "author123",
        avatarUrl = "",
        profileCoverUrl = "",
        subscribers = 1250,
        subscribed = false
    ),
    authorInfo = AuthorInfoModelStable(
        about = "Пишу фанфики с 2020 года. Обожаю фэнтези и романтику.",
        contacts = "@author_telegram",
        support = ""
    ),
    availableTabs = emptyList()
)

private val fakeAuthorProfileComponent: AuthorProfileComponent = object : AuthorProfileComponent {
    override val state: Value<AuthorProfileComponent.State> = MutableValue(
        AuthorProfileComponent.State(
            loading = false,
            error = false,
            errorMessage = null,
            profile = fakeProfile,
            availableTabs = emptyList()
        )
    )
    override val tabs: Value<ChildPages<AuthorProfileComponent.TabConfig, AuthorProfileComponent.Tabs>> =
        MutableValue(ChildPages(items = emptyList(), selectedIndex = 0))
    override val followComponent: DefaultAuthorFollowComponent =
        DefaultAuthorFollowComponent(componentContext = previewComponentContext, initialValue = false)

    override fun sendIntent(intent: AuthorProfileComponent.Intent) {}
    override fun onOutput(output: AuthorProfileComponent.Output) {}
}

@Preview(showBackground = true)
@Composable
private fun AuthorProfileContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            AuthorProfileContent(component = fakeAuthorProfileComponent)
        }
    }
}
