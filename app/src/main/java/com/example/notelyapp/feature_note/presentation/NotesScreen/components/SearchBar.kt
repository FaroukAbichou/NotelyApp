package com.example.notelyapp.feature_note.presentation.NotesScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it != FocusStateImpl.Active && text.isNotEmpty()
                }
        )
        if(isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}


internal enum class FocusStateImpl : FocusState {
    // The focusable component is currently active (i.e. it receives key events).
    Active,

    // One of the descendants of the focusable component is Active.
    ActiveParent,

    // The focusable component is currently active (has focus), and is in a state where
    // it does not want to give up focus. (Eg. a text field with an invalid phone number).
    Captured,

    // The focusable component is not currently focusable. (eg. A disabled button).
    Deactivated,

    // One of the descendants of this deactivated component is Active.
    DeactivatedParent,

    // The focusable component does not receive any key events. (ie it is not active, nor are any
    // of its descendants active).
    Inactive;

    override val isFocused: Boolean
        get() = when (this) {
            Captured, Active -> true
            ActiveParent, Deactivated, DeactivatedParent, Inactive -> false
        }

    override val hasFocus: Boolean
        get() = when (this) {
            Active, ActiveParent, Captured, DeactivatedParent -> true
            Deactivated, Inactive -> false
        }

    override val isCaptured: Boolean
        get() = when (this) {
            Captured -> true
            Active, ActiveParent, Deactivated, DeactivatedParent, Inactive -> false
        }

    /**
     * Whether the focusable component is deactivated.
     *
     * TODO(ralu): Consider making this public when we can add methods to interfaces without
     * breaking compatibility.
     */
    val isDeactivated: Boolean
        get() = when (this) {
            Active, ActiveParent, Captured, Inactive -> false
            Deactivated, DeactivatedParent -> true
        }
}
