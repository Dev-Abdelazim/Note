package com.elfaidy.notes.viewmodel


import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elfaidy.notes.data.entity.NoteEntity
import com.elfaidy.notes.models.DescriptionStyle
import com.elfaidy.notes.models.ToDo
import com.elfaidy.notes.repository.Repository
import com.elfaidy.notes.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: Repository
): ViewModel() {

    val search: MutableState<String> = mutableStateOf("")
    private var _notes = mutableStateOf<List<NoteEntity>>(emptyList())
    val notes = _notes
    var aNote = mutableStateOf<NoteEntity?>(null)

    val isInEditMode: MutableState<Boolean> = mutableStateOf(false)
    val isBottomSheetOpen: MutableState<Boolean> = mutableStateOf(false)

    val background: MutableState<Color?> = mutableStateOf(null)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")

    val images: MutableState<List<String>> = mutableStateOf(emptyList())
    val imageComment: MutableState<String> = mutableStateOf("")

    val toDos: MutableState<List<ToDo>> = mutableStateOf(emptyList())
    val isEditTextIconClicked: MutableState<Boolean> = mutableStateOf(false)
    val isSelected: MutableState<Boolean> = mutableStateOf(false)
    var date: String = Constants.getDateAndTime()
    val charCounter: MutableState<Int> = mutableStateOf(0)


    val isAddToDoIconClicked: MutableState<Boolean> = mutableStateOf(false)
    val descStyle: MutableState<DescriptionStyle> = mutableStateOf(DescriptionStyle())
    val toDoTitle: MutableState<String> = mutableStateOf("")
    val isChecked = mutableStateOf(false)
    val isEditNote: MutableState<Boolean> = mutableStateOf(false)


    val visiblePermissionQueue = mutableStateListOf<String>()

    fun dismissDialog(){
        visiblePermissionQueue.removeFirst()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ){
        if (!isGranted){
            visiblePermissionQueue.add(permission)
        }
    }

    fun setAppData(noteEntity: NoteEntity){
        title.value = noteEntity.title
        date = noteEntity.date
        background.value = noteEntity.color.color
        description.value = noteEntity.description
        images.value = noteEntity.images
        imageComment.value = noteEntity.imageComment
        descStyle.value = noteEntity.descStyle
        toDos.value = noteEntity.todos
        isSelected.value = noteEntity.isSelected
    }



    init {
       getAllNotes()
    }

    private fun getAllNotes(){
        viewModelScope.launch {
            repo.getAllNotes().collectLatest {notes ->
                _notes.value = notes
            }
        }
    }

    fun getANoteById(id: Long){
        viewModelScope.launch {
            repo.getANoteById(id).collectLatest {
                aNote.value = it
            }
        }
    }

    fun deleteANote(noteEntity: NoteEntity){
        viewModelScope.launch{
            repo.delete(noteEntity)
        }
    }

    fun addANote(noteEntity: NoteEntity){
        viewModelScope.launch {
            repo.upsertNote(noteEntity)
        }
    }


    fun textFormat(
        descStyle: DescriptionStyle
    ): TextStyle {
        val newFontSize = when (descStyle.fontSize) {
            18 -> 18.sp
            20 -> 20.sp
            22 -> 22.sp
            24 -> 24.sp
            26 -> 26.sp
            else -> 16.sp
        }

        val bold = if (descStyle.isBold) FontWeight.Bold else FontWeight.Normal
        val italic = if (descStyle.isItalic) FontStyle.Italic else FontStyle.Normal

        return TextStyle(
            fontSize = newFontSize,
            fontWeight = bold,
            fontStyle = italic
        )
    }

    fun addToDo(toDo: ToDo){
        toDos.value += toDo
    }

    fun editToDo(index: Int, newToDo: ToDo){
        val nToDos = toDos.value.toMutableList()
        nToDos[index] = newToDo
        toDos.value = nToDos
    }


    fun reset() {
        background.value = null
        title.value = ""
        description.value = ""
        images.value = emptyList()
        imageComment.value = ""
        toDos.value = emptyList()
        charCounter.value = 0
        descStyle.value = DescriptionStyle()
        toDoTitle.value = ""
        isSelected.value = false
        isChecked.value = false
        isAddToDoIconClicked.value = false
    }


}