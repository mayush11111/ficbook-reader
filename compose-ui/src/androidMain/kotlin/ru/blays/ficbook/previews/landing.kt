package ru.blays.ficbook.components.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import ru.blays.ficbook.components.landingScreenContent.LandingScreenContent
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent

@Preview(showBackground = true)
@Composable
private fun LandingScreenContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            LandingScreenContent(
                dialogChild = null,
                onIntent = {}
            )
        }
    }
}
