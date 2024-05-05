package com.elfaidy.notes.ui.screens.addupdatescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.elfaidy.notes.models.ToDo

@Composable
fun ToDoItem(
    toDo: ToDo,
    onCheckedChange: (Boolean) -> Unit
) {

    val style = if (toDo.isChecked){
        TextStyle(
            color = MaterialTheme.colorScheme.onBackground.copy(
                alpha = 0.5f
            ),
            textDecoration = TextDecoration.LineThrough
        )
    }else{TextStyle()}

    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = toDo.isChecked,
            onCheckedChange = { onCheckedChange.invoke(it) }
        )

        Text(
            text = toDo.title,
            style = style
        )
    }
}
