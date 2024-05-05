package com.elfaidy.notes.repository

import com.elfaidy.notes.data.dao.NoteDao
import com.elfaidy.notes.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val dao: NoteDao
): Repository {
    override suspend fun upsertNote(noteEntity: NoteEntity){
        dao.upsert(noteEntity)
    }

    override suspend fun delete(noteEntity: NoteEntity) {
        dao.delete(noteEntity)
    }

    override fun getAllNotes(): Flow<List<NoteEntity>> =
        dao.getAllNotes()

    override fun getANoteById(id: Long): Flow<NoteEntity> =
        dao.getANoteById(id)

}


