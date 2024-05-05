package com.elfaidy.notes.ui.screens.addupdatescreen.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AddUpdateTextField(
    value: String,
    placeholder: String,
    placeholderStyle: TextStyle,
    modifier: Modifier,
    maxLines: Int? = null,
    containerColor: Color,
    textStyle: TextStyle,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
    ),
    onValueChange: (String) -> Unit
) {

    TextField(
        value = value,
        onValueChange = { onValueChange.invoke(it)},
        modifier = modifier,
        placeholder = {
            Text(
                text = placeholder,
                style = placeholderStyle
            )
        },
        maxLines = maxLines ?: Int.MAX_VALUE,
        textStyle = textStyle,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor =  containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    )
}