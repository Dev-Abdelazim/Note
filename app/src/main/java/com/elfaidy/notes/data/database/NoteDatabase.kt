package com.elfaidy.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.elfaidy.notes.data.converters.Converters
import com.elfaidy.notes.data.dao.NoteDao
import com.elfaidy.notes.data.entity.NoteEntity


@Database(
    entities = [NoteEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NoteDatabase: RoomDatabase(){
    abstract val noteDao: NoteDao
}
