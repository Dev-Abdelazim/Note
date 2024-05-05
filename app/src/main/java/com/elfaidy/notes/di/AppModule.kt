package com.elfaidy.notes.di

import android.content.Context
import androidx.room.Room
import com.elfaidy.notes.data.dao.NoteDao
import com.elfaidy.notes.data.database.NoteDatabase
import com.elfaidy.notes.repository.Repository
import com.elfaidy.notes.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase{
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note-db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(repo: RepositoryImpl): Repository{
        return repo
    }

    @Provides
    @Singleton
    fun provideDao(db: NoteDatabase): NoteDao{
        return db.noteDao
    }


}