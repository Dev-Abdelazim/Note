package com.elfaidy.notes.ui.screens.addupdatescreen.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.elfaidy.notes.R
import com.elfaidy.notes.viewmodel.MainViewModel

@Composable
fun AddUpdateFooter(
    vm: MainViewModel,
    modifier: Modifier,
    onIconMenuClick: (Int) -> Unit
) {

    val modeEditTextIcons = listOf(
        IconModel(
            R.drawable.baseline_text_increase_24,
            R.string.increase_text_size
        ),
        IconModel(
            R.drawable.baseline_text_decrease_24,
            R.string.decrease_text_size
        ),
        IconModel(
            R.drawable.baseline_format_bold_24,
            R.string.desc_bold_icon
        ),
        IconModel(
            R.drawable.baseline_format_italic_24,
            R.string.desc_italic_icon
        ),
        IconModel(
            R.drawable.baseline_close_24,
            R.string.desc_close_edit_icon
        )
    )

    val mainBottomIcons = listOf(
        IconModel(
            R.drawable.baseline_image_24,
            R.string.add_image
        ),
        IconModel(
            R.drawable.baseline_check_box_24,
            R.string.add_todo
        ),
        IconModel(
            R.drawable.baseline_title_24,
            R.string.edit_text
        ),
    )


    Column(
        modifier = modifier,
    ){

        if (vm.isEditTextIconClicked.value) {
            BottomBar(
                icons = modeEditTextIcons,
                modifier = Modifier.fillMaxWidth()
            ) {
                onIconMenuClick.invoke(it)
            }
        }

        BottomBar(
            icons = mainBottomIcons,
            modifier = Modifier.fillMaxWidth()
        ) {
            onIconMenuClick.invoke(it)
        }
    }
}

data class IconModel(
    @DrawableRes val icon: Int,
    @StringRes val desc: Int
)