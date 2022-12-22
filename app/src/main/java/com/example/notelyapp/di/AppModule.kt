package com.example.notelyapp.di

import android.app.Application
import androidx.room.Room
import com.example.notelyapp.feature_note.data_source.NoteDatabase
import com.example.notelyapp.feature_note.data_source.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatebase(app:Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideNoteDao(db : NoteDatabase): NoteDao {
        return db.noteDao

    }
}