package com.elfaidy.notes.repository

import com.elfaidy.notes.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun upsertNote(noteEntity: NoteEntity)
    suspend fun delete(noteEntity: NoteEntity)
    fun getAllNotes(): Flow<List<NoteEntity>>
    fun getANoteById(id: Long): Flow<NoteEntity>
}