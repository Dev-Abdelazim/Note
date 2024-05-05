package com.elfaidy.notes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.elfaidy.notes.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM `note_table`")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM `note_table` WHERE id = :id")
    fun getANoteById(id: Long): Flow<NoteEntity>

    @Upsert
    suspend fun upsert(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)
}