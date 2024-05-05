package com.elfaidy.notes.ui.screens.addupdatescreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.elfaidy.notes.utils.Constants
import com.elfaidy.notes.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteColorChooser(
    vm: MainViewModel,
) {

    ModalBottomSheet(
        onDismissRequest = {
            vm.isBottomSheetOpen.value = false
        }
    ) {
        NoteColorPicker(vm = vm)
    }
}



@ExperimentalMaterial3Api
@Composable
fun NoteColorPicker(vm: MainViewModel) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 22.dp,
            )
    ){
        items(Constants.backgroundColors){ color ->
            NoteColorPickerItem(color = color){
                vm.background.value = it
                vm.isBottomSheetOpen.value = false
            }
        }
    }
}

@Composable
fun NoteColorPickerItem(
    color: Color,
    onColorItemClick: (Color) -> Unit
) {
    Surface(
        modifier = Modifier
            .size(80.dp)
            .padding(2.dp)
            .clickable { onColorItemClick.invoke(color) },
        color = color,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 10.dp
    ){}
}