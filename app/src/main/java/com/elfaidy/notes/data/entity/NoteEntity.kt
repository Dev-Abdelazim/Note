package com.elfaidy.notes.data.entity

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elfaidy.notes.models.BackgroundColor
import com.elfaidy.notes.models.DescriptionStyle
import com.elfaidy.notes.models.ToDo

@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val date: String,
    val description: String,
    @ColumnInfo("desc_style")
    val descStyle: DescriptionStyle = DescriptionStyle(),
    val color: BackgroundColor,
    val images: List<String>,
    @ColumnInfo("image_comment")
    val imageComment: String,
    var todos: List<ToDo>,
    @ColumnInfo("is_selected")
    val isSelected: Boolean
)

val color = Color(0xFF000000)

