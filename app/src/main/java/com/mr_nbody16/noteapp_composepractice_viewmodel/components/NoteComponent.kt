package com.mr_nbody16.noteapp_composepractice_viewmodel.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction



@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: MutableState<String>,
    label: String,
    maxLines: Int = 1,
    onTextChange: (String) -> Unit = {},
    onImeAction: () -> Unit = {}
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(value = text.value, onValueChange = onTextChange, maxLines = maxLines,
        label = { Text(text = label) },
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ), modifier = modifier
    )

}


@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text)
    }
}