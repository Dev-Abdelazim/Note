@file:OptIn(ExperimentalComposeUiApi::class)

package com.elfaidy.notes.ui.screens.addupdatescreen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.elfaidy.notes.NoteApplication
import com.elfaidy.notes.R
import com.elfaidy.notes.components.NoteTopAppBar
import com.elfaidy.notes.data.entity.NoteEntity
import com.elfaidy.notes.models.BackgroundColor
import com.elfaidy.notes.navigation.Screens
import com.elfaidy.notes.ui.screens.addupdatescreen.components.AddUpdateContent
import com.elfaidy.notes.ui.screens.addupdatescreen.components.AddUpdateFooter
import com.elfaidy.notes.ui.screens.addupdatescreen.components.AddUpdateHeader
import com.elfaidy.notes.ui.screens.addupdatescreen.components.NoteColorChooser
import com.elfaidy.notes.utils.Constants
import com.elfaidy.notes.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AddUpdateScreen(
    viewModel: MainViewModel,
    navController: NavHostController,
    multiplePermissionLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>,
    id: Long
) {

    if (id != 0L) viewModel.getANoteById(id)

    val permissionsInRequest = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_MEDIA_IMAGES
    )

    val context = LocalContext.current

    if (viewModel.isBottomSheetOpen.value){
        NoteColorChooser(viewModel)
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = {
            it.forEach {
                viewModel.images.value += it.toString()
            }
        }
    )




    AddUpdateScreenContent(
        viewModel = viewModel,
        navController = navController,
        id = id,
        onIconMenuClick = {iconId ->
            when(iconId){
                R.drawable.baseline_title_24 -> {
                    viewModel.isEditTextIconClicked.value = true
                }

                R.drawable.baseline_check_box_24 -> {
                    viewModel.isAddToDoIconClicked.value = !viewModel.isAddToDoIconClicked.value
                }

                R.drawable.baseline_image_24 -> {
                    if (
                        ActivityCompat.checkSelfPermission(NoteApplication.context, permissionsInRequest[0]) == PackageManager.PERMISSION_GRANTED
                        &&  ActivityCompat.checkSelfPermission(context,permissionsInRequest[1]) == PackageManager.PERMISSION_GRANTED
                    ){
                        galleryLauncher.launch("image/*")

                    }else{
                        Log.d("112211", "permission")
                       multiplePermissionLauncher.launch(
                           permissionsInRequest
                       )
                    }
                }

                R.drawable.baseline_text_increase_24 -> {
                    if (viewModel.descStyle.value.fontSize < Constants.MAX_TEXT_SIZE){
                        viewModel.descStyle.value = viewModel.descStyle.value.copy(
                            fontSize = Constants.increaseTextSize(viewModel.descStyle.value.fontSize)
                        )
                    }
                }

                R.drawable.baseline_text_decrease_24 -> {
                    if (viewModel.descStyle.value.fontSize > Constants.MIN_TEXT_SIZE){
                        viewModel.descStyle.value = viewModel.descStyle.value.copy(
                            fontSize = Constants.decreaseTextSize(viewModel.descStyle.value.fontSize)
                        )
                    }
                }

                R.drawable.baseline_format_bold_24 -> {
                    viewModel.descStyle.value = viewModel.descStyle.value.copy(
                        isBold = !viewModel.descStyle.value.isBold
                    )
                }

                R.drawable.baseline_format_italic_24 -> {
                    viewModel.descStyle.value = viewModel.descStyle.value.copy(
                        isItalic = !viewModel.descStyle.value.isItalic
                    )
                }

                R.drawable.baseline_close_24 -> {
                    viewModel.isEditTextIconClicked.value = false
                }
            }
        }
    )
}




@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddUpdateScreenContent(
    viewModel: MainViewModel,
    navController: NavController,
    onIconMenuClick: (Int) -> Unit,
    id: Long
) {

    val topBarTitle = if (viewModel.isInEditMode.value) {
        stringResource(id = R.string.update_node_topbar_title)
    } else {
        stringResource(id = R.string.add_node_topbar_title)
    }

    val newBackground = if (viewModel.background.value != null){
        viewModel.background.value
    }else{
        MaterialTheme.colorScheme.background
    }

    val context = LocalContext.current

    // TopBar actions icons
    val actionIconsList = mutableListOf(
        R.drawable.baseline_color_lens_24,
        R.drawable.baseline_check_24
    )

    val newIcons = if (viewModel.isEditNote.value) {
        actionIconsList.add(0, R.drawable.baseline_delete_24)
        actionIconsList
    } else {
        actionIconsList
    }


    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = topBarTitle,
                actionIcons = newIcons,
                navigationIcon = Icons.Filled.ArrowBack,
                navigationIconClick = {
                    navController.popBackStack()
                    viewModel.isEditNote.value = false
                    viewModel.reset()
                },
                actionIconsClick = { icon ->
                    when (icon) {
                        // color chooser dialog
                        R.drawable.baseline_color_lens_24 -> {
                            viewModel.isBottomSheetOpen.value = true
                        }

                        // save note to database icon
                        R.drawable.baseline_check_24 -> {
                            if (checkValidInput(viewModel)) {
                                //add note to database
                                var note = NoteEntity(
                                    title = viewModel.title.value,
                                    description = viewModel.description.value,
                                    descStyle = viewModel.descStyle.value,
                                    color = BackgroundColor(newBackground!!),
                                    images = viewModel.images.value,
                                    imageComment = "",
                                    todos = viewModel.toDos.value,
                                    date = Constants.getDateAndTime(),
                                    isSelected = false
                                )
                                if (viewModel.isEditNote.value){
                                    note = note.copy(id = id)
                                }
                                viewModel.isEditNote.value = false

                                viewModel.addANote(note)
                                navController.navigate(route = Screens.NotesScreen.route)
                                viewModel.reset()
                            } else {
                                Toast.makeText(
                                    context,
                                    "You can't save empty note",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        // delete note icon
                        R.drawable.baseline_delete_24 -> {
                            if (viewModel.aNote.value != null){
                                viewModel.deleteANote(viewModel.aNote.value!!)
                            }

                            navController.navigate(Screens.NotesScreen.route)
                            viewModel.isEditNote.value = false
                            viewModel.reset()
                        }
                    }
                }
            )
        }
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(newBackground!!)
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = it.calculateTopPadding()
                )
        ){
            val (constContentRef,midContentRef, bottomBarRef) = createRefs()


            AddUpdateHeader(
                viewModel = viewModel,
                newBackground = newBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(constContentRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )


            AddUpdateContent(
                viewModel = viewModel,
                newBackground = newBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(midContentRef) {
                        top.linkTo(constContentRef.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            AddUpdateFooter(
                vm = viewModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(bottomBarRef) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onIconMenuClick = onIconMenuClick
            )

        }

    }
}

fun checkValidInput(vm: MainViewModel) =
    vm.title.value.isNotEmpty()
            || vm.description.value.isNotEmpty()
            || vm.images.value.isNotEmpty()
            || vm.toDos.value.isNotEmpty()



