package ru.blays.ficbook.components.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import ru.blays.ficbook.components.users.UsersRootContent
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent
import ru.blays.ficbook.reader.shared.components.usersComponent.declaration.*
import ru.blays.ficbook.reader.shared.data.dto.PopularAuthorModelStable
import ru.blays.ficbook.reader.shared.data.dto.UserModelStable

private val fakeUsers = listOf(
    UserModelStable(name = "Автор1", href = "authors/1", avatarUrl = ""),
    UserModelStable(name = "Автор2", href = "authors/2", avatarUrl = ""),
    UserModelStable(name = "Автор3", href = "authors/3", avatarUrl = "")
)

private fun fakeFavouriteComponent(): UsersFavouriteComponent = object : UsersFavouriteComponent {
    override val state: Value<UsersFavouriteComponent.State> = MutableValue(
        UsersFavouriteComponent.State(
            list = fakeUsers,
            loading = false,
            error = false,
            errorMessage = null
        )
    )
    override fun sendIntent(intent: UsersFavouriteComponent.Intent) {}
    override fun onOutput(output: UsersRootComponent.Output) {}
}

private fun fakePopularComponent(): UsersPopularComponent = object : UsersPopularComponent {
    override val state: Value<UsersPopularComponent.State> = MutableValue(
        UsersPopularComponent.State(
            list = fakeUsers.mapIndexed { index, user ->
                PopularAuthorModelStable(user = user, position = index + 1, subscribersInfo = "${(index + 1) * 100} подписчиков")
            },
            loading = false,
            error = false,
            errorMessage = null
        )
    )
    override fun onOutput(output: UsersRootComponent.Output) {}
}

private fun fakeSearchComponent(): UsersSearchComponent = object : UsersSearchComponent {
    override val state: Value<UsersSearchComponent.State> = MutableValue(
        UsersSearchComponent.State(
            searchedName = "",
            list = emptyList(),
            loading = false,
            error = false,
            errorMessage = null
        )
    )
    override fun sendIntent(intent: UsersSearchComponent.Intent) {}
    override fun onOutput(output: UsersRootComponent.Output) {}
}

private val fakePages = ChildPages(
    items = listOf(
        Child.Created(
            UsersRootComponent.TabConfig.FavouriteAuthors,
            UsersRootComponent.Tabs.FavouriteAuthors(fakeFavouriteComponent())
        ),
        Child.Created(
            UsersRootComponent.TabConfig.PopularAuthors,
            UsersRootComponent.Tabs.PopularAuthors(fakePopularComponent())
        ),
        Child.Created(
            UsersRootComponent.TabConfig.SearchAuthors,
            UsersRootComponent.Tabs.SearchAuthors(fakeSearchComponent())
        )
    ),
    selectedIndex = 0
)

@Preview(showBackground = true)
@Composable
private fun UsersRootContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            val pagesState = remember { mutableStateOf(fakePages) }
            UsersRootContent(
                pagesState = pagesState,
                onSelectTab = {},
                onOutput = {}
            )
        }
    }
}
