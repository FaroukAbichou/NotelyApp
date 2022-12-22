package com.example.notelyapp.feature_note.presentation.NotesScreen

import androidx.compose.foundation.background


import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notelyapp.Screen
import com.example.notelyapp.feature_note.presentation.HomeScreen.components.ExclamationImage
import com.example.notelyapp.feature_note.presentation.NotesScreen.components.CategoryAllItems
import com.example.notelyapp.feature_note.presentation.NotesScreen.components.CategoryItemNoteScreen
import com.example.notelyapp.feature_note.presentation.NotesScreen.components.NoteItem
import com.example.notelyapp.feature_note.presentation.NotesScreen.components.SearchBar
import com.example.notelyapp.feature_note.presentation.util.ui.theme.ButtonPurple
import com.example.notelyapp.feature_note.presentation.util.ui.theme.White
import kotlinx.coroutines.launch


@Composable
fun NotesScreen(
    navController:NavController,
    viewModel: NotesScreenViewModel = hiltViewModel()
){
    val notes = viewModel.notes.value
    val categorys =viewModel.categorys.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        viewModel.getNotes()
        viewModel.getCategorys()
    }



    Scaffold(
        Modifier.background(Color(0xFFEFF3F6)),

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.CreateNoteWA.route)
                },
                backgroundColor = ButtonPurple
            ) {
                Row(Modifier.padding(horizontal = 30.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        tint= White,
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add note"
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Create", color = White)
                }
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "My Notes",
                    fontSize = 25.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            SearchBar(
                onSearch = {
                        viewModel.search(it)
                },
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row() {
                CategoryAllItems(){
                    viewModel.getNotes()
                }

                Spacer(modifier = Modifier.width(16.dp))


                    val mRememberObserver = remember { mutableStateOf("") }
                    val scrollState = rememberScrollState()
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .horizontalScroll(state = scrollState)
                            .padding(end = 10.dp)) {
                                categorys.forEach { category ->
                                    Row (verticalAlignment = Alignment.CenterVertically){
                                        CategoryItemNoteScreen(
                                            category = category,

                                        ) {
                                            viewModel.searchCategory(category.category)
                                            mRememberObserver.value = category.category
                                            }
                                    Spacer(modifier = Modifier.width(16.dp))}

                        }
                    }
            }

            Spacer(modifier = Modifier.height(16.dp))
            if (!viewModel.ListIsEmpty ){
            LazyColumn(modifier = Modifier.fillMaxWidth()) {

                    items(notes) { note ->
                        NoteItem(
                            note = note,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Screen.CreateNote.withArgs(note.id)
                                    )
                                },
                            onDeleteClick = {
                                scope.launch { val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Note deleted",
                                    actionLabel = "Undo"
                                )
                                    viewModel.deleteNote(note)
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.restoreNote()
                                    }
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                    }
                }

            }else{
                Warning()
                viewModel.ListIsEmpty = !viewModel.ListIsEmpty

        }
    }
}}

@Composable
fun Warning(){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp),
    horizontalAlignment = CenterHorizontally){
            ExclamationImage(Modifier)
            Text(text = "No Note Found",
                Modifier,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
    }
}
