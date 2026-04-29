package ru.blays.ficbook.components.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent
import ru.blays.ficbook.theme.AppTheme
import ru.blays.ficbook.theme.defaultAccentColors


class ThemePreviewParamsProvider : PreviewParameterProvider<ThemeComponent.State> {
    private val valuesList: List<ThemeComponent.State> = buildList {
        // все accentColor × light/dark
        defaultAccentColors.indices.forEach { accentIndex ->
            add(ThemeComponent.State(
                themeIndex = 0,
                amoledTheme = false,
                dynamicColors = false,
                defaultAccentIndex = accentIndex
            ))
            add(ThemeComponent.State(
                themeIndex = 1,
                amoledTheme = false,
                dynamicColors = false,
                defaultAccentIndex = accentIndex
            ))
        }
        // amoled
        add(ThemeComponent.State(
            themeIndex = 1,
            amoledTheme = true,
            dynamicColors = false,
            defaultAccentIndex = 0
        ))
        // monet
        add(ThemeComponent.State(
            themeIndex = 0,
            amoledTheme = false,
            dynamicColors = true,
            defaultAccentIndex = 0
        ))
        add(ThemeComponent.State(
            themeIndex = 1,
            amoledTheme = false,
            dynamicColors = true,
            defaultAccentIndex = 0
        ))
    }

    override val values: Sequence<ThemeComponent.State> = valuesList.asSequence()

    /*override fun getDisplayName(index: Int): String? {
        val themeState = valuesList.getOrNull(index) ?: return null
        toStringTheme(themeState)
    }

    fun toStringTheme(themeState: ThemeComponent.State): String {
        val theme = when(themeState.themeIndex) {
            0 -> "System"
            1 -> "Dark"
            2 -> "Light"
            else -> "System"
        }
        return buildString {
            append(theme)
            if (themeState.amoledTheme) append(" / Amoled")
            if (themeState.dynamicColors) append(" / Monet")
            append(" / Accent $themeState.defaultAccentIndex")
        }
    }*/
}


@Composable
fun AppThemePreview(
    state: ThemeComponent.State,
    content: @Composable () -> Unit
) {
    AppTheme(
        themeIndex = state.themeIndex,
        isAmoledTheme = state.amoledTheme,
        colorAccentIndex = state.defaultAccentIndex,
        monetTheme = state.dynamicColors,
        content = content
    )
}
