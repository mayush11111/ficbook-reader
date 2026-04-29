package ru.blays.ficbook.components.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.blays.ficbook.components.settingsContent.SettingsMainContent
import ru.blays.ficbook.components.settingsContent.SettingsProxyContent
import ru.blays.ficbook.reader.shared.components.settingsComponents.declaration.SettingsMainComponent
import ru.blays.ficbook.reader.shared.components.settingsComponents.declaration.SettingsProxyComponent
import ru.blays.ficbook.reader.shared.components.settingsComponents.declaration.SettingsUnitComponent
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent
import ru.blays.ficbook.reader.shared.preferences.json.ProxyConfig
import java.net.Proxy

private fun <T : Any> previewUnit(value: T): SettingsUnitComponent<T> = object : SettingsUnitComponent<T> {
    override val state: StateFlow<T> = MutableStateFlow(value)
    override fun onIntent(intent: SettingsUnitComponent.Intent<T>) = Unit
}

private val fakeProxyState = SettingsProxyComponent.State(
    enabled = true,
    usedCustom = true,
    customProxyConfig = ProxyConfig(
        hostname = "proxy.example.com",
        port = 8080,
        type = Proxy.Type.HTTP,
        username = null,
        password = null
    )
)

@Preview(showBackground = true)
@Composable
private fun SettingsMainContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            SettingsMainContent(
                themeSetting = previewUnit(1),
                amoledSetting = previewUnit(false),
                dynamicColorsSetting = previewUnit(false),
                accentIndexSetting = previewUnit(0),
                glassEffectEnabled = previewUnit(true),
                blurAlpha = previewUnit(0.6f),
                blurRadius = previewUnit(20f),
                blurNoiseFactor = previewUnit(0.1f),
                bypassBlock = previewUnit(false),
                autoVoteSetting = previewUnit(false),
                chromeCustomTabsSetting = previewUnit(true),
                typografSetting = previewUnit(true),
                onOutput = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsProxyContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            SettingsProxyContent(
                state = fakeProxyState,
                onIntent = {},
                onOutput = {}
            )
        }
    }
}
