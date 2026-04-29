package ru.blays.ficbook.components.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import ru.blays.ficbook.components.userProfile.AccountsManagingContent
import ru.blays.ficbook.components.userProfile.LogInContent
import ru.blays.ficbook.components.userProfile.UserProfileContent
import ru.blays.ficbook.reader.shared.components.profileComponents.declaration.UserLogInComponent
import ru.blays.ficbook.reader.shared.components.profileComponents.declaration.UserProfileManagingComponent
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent
import ru.blays.ficbook.reader.shared.data.dto.SavedUserModel

private val fakeUserModel = SavedUserModel(
    name = "Пользователь",
    id = "12345",
    avatarPath = "",
    cookies = emptyList()
)

private val fakeLogInState = UserLogInComponent.State(
    login = "user@example.com",
    password = "",
    loading = false,
    success = true,
    reason = null
)

private val fakeAccountsState = UserProfileManagingComponent.State(
    savedUsers = listOf(
        SavedUserModel(name = "Пользователь1", id = "1", avatarPath = "", cookies = emptyList()),
        SavedUserModel(name = "Пользователь2", id = "2", avatarPath = "", cookies = emptyList())
    ),
    selectedUserID = "1"
)

@Preview(showBackground = true)
@Composable
private fun UserProfileContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            UserProfileContent(
                state = fakeUserModel,
                onIntent = {},
                onOutput = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LogInContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            LogInContent(
                state = fakeLogInState,
                onIntent = {},
                onOutput = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AccountsManagingContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            AccountsManagingContent(
                state = fakeAccountsState,
                onIntent = {},
                onOutput = {}
            )
        }
    }
}
