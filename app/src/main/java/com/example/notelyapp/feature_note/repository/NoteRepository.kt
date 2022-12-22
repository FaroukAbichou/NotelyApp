package com.example.notelyapp.feature_note.repository

import com.example.notelyapp.feature_note.data_source.NoteDao
import com.example.notelyapp.feature_note.model.Category
import com.example.notelyapp.feature_note.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val dao: NoteDao
) {
    suspend fun getNotes(): List<Note> {
        return dao.getNotes()
    }

    suspend fun searchNote(title:String): List<Note> {
        return dao.searchNote("%$title%")
    }

    suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    suspend fun insertNote(note: Note) {
        return dao.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }


    suspend fun getCategory(): List<Category> {
        return dao.getCategory()
    }

    suspend fun searchCategory(category:String): List<Note> {
        return dao.searchCategory(category)
    }

    suspend fun insertCategory(category: Category) {
        return dao.insertCategory(category)
    }

    suspend fun deleteCategory(category: Category) {
        return dao.deleteCategory(category)
    }
}