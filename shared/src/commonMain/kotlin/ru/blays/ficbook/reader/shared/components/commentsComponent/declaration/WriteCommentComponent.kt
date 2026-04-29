package ru.blays.ficbook.reader.shared.components.commentsComponent.declaration

import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.blays.ficbook.reader.shared.data.dto.CommentBlockModelStable

interface WriteCommentComponent {
    val state: Value<State>

    fun sendIntent(intent: Intent)

    sealed class Intent {
        data class EditText(val newText: String) : Intent()
        data class AddReply(val blocks: List<CommentBlockModelStable>) : Intent()
        data object Post : Intent()
    }

    @Serializable
    data class State(
        val text: String,
        val renderedBlocks: List<CommentBlockModelStable>,
        val followType: Int,
        val error: Boolean,
        val errorMessage: String?
    )
}