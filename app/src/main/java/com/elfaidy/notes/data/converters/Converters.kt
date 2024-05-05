package com.elfaidy.notes.data.converters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.toArgb
import androidx.room.TypeConverter
import com.elfaidy.notes.models.DescriptionStyle
import androidx.compose.ui.graphics.Color
import com.elfaidy.notes.models.BackgroundColor
import com.elfaidy.notes.models.ToDo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.io.OutputStream

class Converters {


    //from Bitmap to bytearray
    @TypeConverter
    fun fromUri(uris: List<String>): String{
        return Gson().toJson(uris)
    }

    //from bytearray to Bitmap
    @TypeConverter
    fun toBitmap(value: String): List<String>{
        val type = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, type)
    }



    @TypeConverter
    fun fromToDo(toDo: List<ToDo>): String{
        return Gson().toJson(toDo)
    }

    @TypeConverter
    fun toToDo(value: String): List<ToDo>{
        val typeToken = object: TypeToken<List<ToDo>>(){}.type
        return Gson().fromJson(value, typeToken)
    }

    @TypeConverter
    fun fromDescriptionStyle(style: DescriptionStyle): String{
        val isBold = if (style.isBold) "1" else "0"
        val isItalic = if (style.isItalic) "1" else "0"
        return "${style.fontSize},$isBold,$isItalic"
    }

    @TypeConverter
    fun toDescriptionStyle(style: String): DescriptionStyle{
        val result = style.split(",")
        return DescriptionStyle(
            fontSize = result[0].toInt(),
            isBold = result[1] == "1",
            isItalic = result[2] == "1",
        )
    }


    @TypeConverter
    fun fromColor(color: BackgroundColor) : Long {
        return color.color.toArgb().toLong()
    }

    @TypeConverter
    fun toColor(color: Long): BackgroundColor {
        return BackgroundColor(color = Color(color))
    }


}