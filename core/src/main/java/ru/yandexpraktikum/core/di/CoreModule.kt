package ru.yandexpraktikum.core.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.yandexpraktikum.core.data.db.NoteDao
import ru.yandexpraktikum.core.data.db.NoteDatabase
import ru.yandexpraktikum.core.data.repository.NotesRepositoryImpl
import ru.yandexpraktikum.core.domain.repository.NotesRepository
import javax.inject.Singleton

private const val DATABASE_NAME = "note_database"

@Module
interface CoreModule {
    companion object {
        @Provides
        @Singleton
        fun provideNoteDatabase(
            context: Context,
        ): NoteDatabase {
            return Room.databaseBuilder(
                context,
                NoteDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

        @Provides
        fun provideNoteDao(database: NoteDatabase): NoteDao {
            return database.noteDao()
        }
    }

    @Binds
    @Singleton
    fun bindNotesRepository(repositoryImpl: NotesRepositoryImpl): NotesRepository


    // Объявите методы для создания необходимых зависимостей.
    //
    // Обратите внимание, что аннотацию @Provides можно использовать только в неабстрактных         // методах, поэтому можно объявить методы с @Provides внутри объекта-компаньона
}

// Добавьте аннотацию @Inject к конструкторам NotesRepositoryImpl, DataNoteMapper и             // PresentationNoteMapper