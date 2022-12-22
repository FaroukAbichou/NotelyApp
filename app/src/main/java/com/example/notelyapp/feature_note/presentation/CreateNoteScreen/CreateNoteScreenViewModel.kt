package com.example.notelyapp.feature_note.presentation.CreateNoteScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notelyapp.feature_note.model.Category
import com.example.notelyapp.feature_note.model.InvalidNoteException
import com.example.notelyapp.feature_note.model.Note
import com.example.notelyapp.feature_note.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNoteScreenViewModel @Inject constructor(
        private val noteRepository: NoteRepository,
        savedStateHandle: SavedStateHandle
    ):ViewModel(){

    private val _noteTitle = mutableStateOf(NoteTextfieldState(hint = "Enter Title..."))
    val noteTitle: State<NoteTextfieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextfieldState(hint = "Enter Your Note"))
    val noteContent: State<NoteTextfieldState> = _noteContent

    private val _noteCategory = mutableStateOf(NoteTextfieldState(hint = "Enter a new Category"))
    val noteCategory: State<NoteTextfieldState> = _noteCategory

    private val _noteAddedCategory = mutableStateOf(NoteTextfieldState(hint = "Enter a new Category"))
    val noteAddedCategory: State<NoteTextfieldState> = _noteAddedCategory


    private val _categorys = mutableStateOf(emptyList<Category>())
    val categorys: State<List<Category>> = _categorys

    private val _eventflow = MutableSharedFlow<UiEvent>()
    val eventFlow= _eventflow.asSharedFlow()

    private var currentNoteId : Int? =  null

    init {
        getCategories()

        savedStateHandle.get<String>("Id")?.let { noteId ->
            if (noteId.toInt() != -1) {
                viewModelScope.launch{
                    noteRepository.getNoteById(noteId.toInt())?.also { note ->
                        currentNoteId= note.id
                        _noteTitle.value = _noteTitle.value.copy(
                            text = note.title,
                        )
                        _noteCategory.value = _noteCategory.value.copy(
                            text = note.category,
                        )
                        _noteContent.value = _noteContent.value.copy(
                            text = note.content,
                        )
                    }
                }
            }
        }
    }

    fun enterTitle(value:String){
        viewModelScope.launch {
            _noteTitle.value = _noteTitle.value.copy(value)
        }
    }

    fun changeTitleFocus(focusState: FocusState){
        viewModelScope.launch {
            _noteTitle.value = _noteTitle.value.copy(
                isHintvisible = focusState.isFocused &&
                        noteTitle.value.text.isBlank()
            )
        }
    }

    fun enteredContent(value:String){
        viewModelScope.launch {
            _noteContent.value = _noteContent.value.copy(value)
        }
    }
    fun changeContentFocus(focusState: FocusState){
        viewModelScope.launch {
            _noteContent.value = _noteContent.value.copy(
                isHintvisible = focusState.isFocused &&
                        noteTitle.value.text.isBlank()
            )
        }
    }

    fun enteredCategory(value:String){
        viewModelScope.launch {
            _noteAddedCategory.value = _noteAddedCategory.value.copy(value)
        }
    }

    fun chosenCategory(value:String){
        viewModelScope.launch {
            _noteCategory.value = _noteCategory.value.copy(value)
        }
    }
    fun changeCategoryFocus(focusState: FocusState){
        viewModelScope.launch {
            _noteCategory.value = _noteCategory.value.copy(
                isHintvisible = focusState.isFocused &&
                        noteCategory.value.text.isBlank()
            )
        }
    }

    fun saveCategory(category: String){
        viewModelScope.launch {
            try {
                noteRepository.insertCategory(
                    Category(
                        category = category,
                        isSelected = true
                    )
                )
                getCategories()
                _eventflow.emit(UiEvent.SaveCategory)
            } catch (e: InvalidNoteException){
                _eventflow.emit(
                    UiEvent.ShowSnackbar(
                        message = e.message?:"Couldnt save Note"
                    )
                )
            }
        }
    }

    fun saveNote(){
        viewModelScope.launch {
            try {

                noteRepository.insertNote(
                    Note(
                        title = noteTitle.value.text,
                        content = noteContent.value.text,
                        category = noteCategory.value.text,
                        timestamp = System.currentTimeMillis(),
                        color = Color(0xFFFEFFFF).hashCode(),
                        id =currentNoteId
                    )
                )
                _eventflow.emit(UiEvent.SaveNote)
            } catch (e: InvalidNoteException){
                _eventflow.emit(

                    UiEvent.ShowSnackbar(
                        message = e.message?:"Couldnt save Note"
                    ))
            }
        }

    }

    fun getCategories() {
        viewModelScope.launch {
            _categorys.value = noteRepository.getCategory()
        }
    }

    sealed class UiEvent{
        data class ShowSnackbar(val message:String):UiEvent()
        object SaveNote: UiEvent()
        object SaveCategory: UiEvent()
    }

}