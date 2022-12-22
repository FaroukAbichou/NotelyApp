package com.example.notelyapp.feature_note.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notelyapp.feature_note.model.Category
import com.example.notelyapp.feature_note.model.Note


@Dao
interface NoteDao {
    //For Notes

    @Query("SELECT * FROM note")
    suspend fun getNotes(): List<Note>

    @Query("SELECT * FROM note where title Like :title")
    suspend fun searchNote(title: String): List<Note>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id:Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    //For Categories

    @Query("SELECT * FROM note where category Like :category")
    suspend fun searchCategory(category: String): List<Note>

    @Query("SELECT * FROM category")
    suspend fun getCategory(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)



}