@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)

package com.elfaidy.notes.ui.screens.addupdatescreen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.elfaidy.notes.R
import com.elfaidy.notes.viewmodel.MainViewModel

@ExperimentalComposeUiApi
@Composable
fun AddUpdateContent(
    viewModel: MainViewModel,
    newBackground: Color,
    modifier: Modifier
) {

    Column(
        modifier = modifier
    ){

        if (viewModel.images.value.isNotEmpty()){
            LazyRow {
                items(
                    viewModel.images.value,
                    key = {it}
                ){ imageUri ->
                    ImageItem(
                        imageUri = imageUri,
                        modifier = Modifier
                            .size(240.dp)
                            .padding(end = 5.dp)
                            .background(MaterialTheme.colorScheme.background)
                            .animateItemPlacement()
                    ) {
                        viewModel.images.value -= imageUri
                        viewModel.images.value.shuffled()
                    }
                }
            }
        }

        if (viewModel.isAddToDoIconClicked.value){
            AddToDoScreen(
                vm = viewModel
            ){ viewModel.isChecked.value = it }
        }

        LazyColumn{
            items(viewModel.toDos.value.size){index ->
                ToDoItem(
                    toDo = viewModel.toDos.value[index]
                ){ isChecked ->
                    viewModel.editToDo(
                        index,
                        viewModel.toDos.value[index].copy(
                            isChecked = isChecked
                        )
                    )
                }
            }
        }


        AddUpdateTextField(
            value = viewModel.description.value,
            placeholder = stringResource(id = R.string.desc_text),
            placeholderStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            textStyle = viewModel.textFormat(viewModel.descStyle.value),
            containerColor = newBackground
        ) { newDescription -> viewModel.description.value = newDescription }
    }
}


