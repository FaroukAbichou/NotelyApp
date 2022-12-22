package com.example.notelyapp.feature_note.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notelyapp.feature_note.model.Category
import com.example.notelyapp.feature_note.model.Note


@Database(
    entities = [
        Note::class ,
        Category::class],
    version=2,
    exportSchema = true
)
abstract class NoteDatabase:RoomDatabase(){
    abstract val noteDao: NoteDao
    companion object{
        const val  DATABASE_NAME= "notes_db"
    }

}
