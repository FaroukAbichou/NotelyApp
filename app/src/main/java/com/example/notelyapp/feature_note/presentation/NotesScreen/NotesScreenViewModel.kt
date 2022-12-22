package com.example.notelyapp.feature_note.presentation.NotesScreen

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notelyapp.feature_note.model.Category
import com.example.notelyapp.feature_note.model.Note
import com.example.notelyapp.feature_note.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesScreenViewModel @Inject constructor(
    private val noteRepository: NoteRepository
):ViewModel(){

    private val _notes = mutableStateOf(emptyList<Note>())
    val notes: State<List<Note>> = _notes

    private val _categorys = mutableStateOf(emptyList<Category>())
    val categorys: State<List<Category>> = _categorys

    private var recentlyDeletedNote: Note? = null

    init {
        getNotes()
        getCategorys()

    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
            recentlyDeletedNote = note
        }
    }

    fun restoreNote() {
        viewModelScope.launch {
            noteRepository.insertNote(recentlyDeletedNote?: return@launch)
            recentlyDeletedNote=null
        }
    }

     fun getNotes(){
        viewModelScope.launch {
            _notes.value = noteRepository.getNotes()

        }
    }

     fun getCategorys(){
        viewModelScope.launch {
            _categorys.value = noteRepository.getCategory()
        }
    }
    var ListIsEmpty= false

    fun search(value: String) {
        viewModelScope.launch {
            _notes.value = noteRepository.searchNote(value)
            if (_notes.value.isEmpty()){
                ListIsEmpty = !ListIsEmpty
                Log.d("tag1",ListIsEmpty.toString())
            }
        }
    }
    var CategoryListIsEmpty= false
    fun searchCategory(value: String) {
        viewModelScope.launch {
            _notes.value = noteRepository.searchCategory(value)
            if (_notes.value.isEmpty()){
                CategoryListIsEmpty = !CategoryListIsEmpty
                Log.d("tag1",CategoryListIsEmpty.toString())
            }
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            noteRepository.deleteCategory(category)

        }
    }
}
