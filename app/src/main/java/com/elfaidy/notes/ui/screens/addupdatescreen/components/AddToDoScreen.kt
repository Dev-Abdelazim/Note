package com.elfaidy.notes.ui.screens.addupdatescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elfaidy.notes.models.ToDo
import com.elfaidy.notes.viewmodel.MainViewModel

@Composable
fun AddToDoScreen(
    vm: MainViewModel,
    onCheckedChange: (Boolean) -> Unit
) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = vm.isChecked.value,
            onCheckedChange = { onCheckedChange.invoke(it) }
        )

        AddUpdateTextField(
            value = vm.toDoTitle.value,
            placeholder = "Add ToDo",
            placeholderStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier,
            containerColor = MaterialTheme.colorScheme.background,
            textStyle = TextStyle(fontSize = 18.sp),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (vm.toDoTitle.value.isNotEmpty()){
                        val toDo = ToDo(
                            title = vm.toDoTitle.value,
                            isChecked = vm.isChecked.value
                        )
                        vm.addToDo(toDo)
                        vm.toDoTitle.value = ""
                        vm.isChecked.value = false
                    }
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
        ){ vm.toDoTitle.value = it}
    }
}