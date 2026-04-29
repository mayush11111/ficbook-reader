package ru.blays.ficbook.components.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.arkivanov.decompose.Child
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.blays.ficbook.components.superfilterContent.SuperfilterRootContent
import ru.blays.ficbook.reader.shared.components.superfilterComponents.SuperfilterComponent
import ru.blays.ficbook.reader.shared.components.superfilterComponents.SuperfilterTabComponent
import ru.blays.ficbook.reader.shared.components.themeComponents.ThemeComponent

private val fakeTabState = SuperfilterTabComponent.State(
    values = listOf(
        SuperfilterTabComponent.BlacklistItem(name = "Пункт 1", value = "val1"),
        SuperfilterTabComponent.BlacklistItem(name = "Пункт 2", value = "val2"),
        SuperfilterTabComponent.BlacklistItem(name = "Пункт 3", value = "val3")
    )
)

private fun fakeSuperfilterTab(): SuperfilterTabComponent = object : SuperfilterTabComponent {
    override val state: StateFlow<SuperfilterTabComponent.State> = MutableStateFlow(fakeTabState)
    override val addValueDialog: Value<ChildSlot<Unit, SuperfilterTabComponent.AddValueDialogComponent>> =
        MutableValue(ChildSlot())
    override fun onIntent(intent: SuperfilterTabComponent.Intent) {}
}

private val fakePages = ChildPages(
    items = listOf(
        Child.Created(0, fakeSuperfilterTab()),
        Child.Created(1, fakeSuperfilterTab()),
        Child.Created(2, fakeSuperfilterTab()),
        Child.Created(3, fakeSuperfilterTab()),
        Child.Created(4, fakeSuperfilterTab())
    ),
    selectedIndex = 0
)

@Preview(showBackground = true)
@Composable
private fun SuperfilterRootContentPreview(
    @PreviewParameter(ThemePreviewParamsProvider::class) theme: ThemeComponent.State
) {
    AppThemePreview(state = theme) {
        Surface {
            SuperfilterRootContent(
                pages = fakePages,
                onChangeTab = {},
                onOutput = {}
            )
        }
    }
}
