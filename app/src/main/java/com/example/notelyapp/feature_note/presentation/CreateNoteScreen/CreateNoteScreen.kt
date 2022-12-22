package com.example.notelyapp.feature_note.presentation.CreateNoteScreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notelyapp.R
import com.example.notelyapp.feature_note.model.Category
import com.example.notelyapp.feature_note.presentation.CreateNoteScreen.components.TransparentHintTextField
import com.example.notelyapp.feature_note.presentation.util.ui.theme.ButtonPurple
import kotlinx.coroutines.flow.collectLatest


@Composable
fun CreateNoteScreen(
    navController: NavController,
    viewModel: CreateNoteScreenViewModel = hiltViewModel(),
    ){
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val categoryState = viewModel.noteCategory.value
    val categoryAddedState = viewModel.noteAddedCategory.value
    val categorys =viewModel.categorys.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is CreateNoteScreenViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is CreateNoteScreenViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
                is CreateNoteScreenViewModel.UiEvent.SaveCategory -> {
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
           Column(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(16.dp)
           ) {
               Box(modifier = Modifier.padding(top = 20.dp, bottom = 0.dp, start = 4.dp, end = 4.dp)){
                   Row(modifier = Modifier
                       .fillMaxWidth(),
                       horizontalArrangement = Arrangement.Start) {
                       Icon(painter = painterResource(R.drawable.ic_baseline_arrow_back_24),
                           contentDescription = "Back",
                           modifier = Modifier
                               .clickable {
                                   navController.popBackStack()
                               }
                       )
                   }

                   Row(modifier = Modifier
                       .fillMaxWidth(),
                       horizontalArrangement = Arrangement.End) {
                       Icon(painter = painterResource(R.drawable.ic_baseline_done_24),
                           contentDescription = "Done",
                           modifier = Modifier
                               .clickable {
                                   viewModel.saveNote()
                               }
                       )
                   }
               }
                   Spacer(modifier = Modifier.height(20.dp))

                   TransparentHintTextField(
                       text = titleState.text,
                       onValueChange = {
                           viewModel.enterTitle(it)
                       },
                       onFocusChange = {
                           viewModel.changeTitleFocus(it)
                       },
                       isHintVisible = titleState.isHintvisible,
                       singleLine = true,
                       textStyle = MaterialTheme.typography.h5
                   )

                   Spacer(modifier = Modifier.height(10.dp))
                   GroupedRadioButton(categories = categorys,viewModel)
//                   LazyRow(modifier = Modifier.padding(vertical = 20.dp)) {
                   //                       items(categorys) { category ->
//                           Row (verticalAlignment = CenterVertically){
////                               var selected =remember { mutableStateOf(false) }
////                               RadioButton(
////                                   onClick = {
////                                        selected.value = !selected.value
////                                       viewModel.enteredCategory(category.category)
////                                   },
////                                   selected = selected.value,
////                               )
////                               Text(text =category.category)
//
//                           }
//                           Spacer(modifier = Modifier.width(16.dp))
//                       }
//                   }

                   Column(Modifier
                       .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                       TransparentHintTextField(
                           text = categoryAddedState.text,
                           onValueChange = {
                               viewModel.enteredCategory(it)
                           },
                           onFocusChange = {
                               viewModel.changeCategoryFocus(it)
                           },
                           isHintVisible = categoryAddedState.isHintvisible,
                           singleLine = true,
                           textStyle = MaterialTheme.typography.h5
                       )
                       Button(
                           onClick = {
                               viewModel.saveCategory(categoryAddedState.text)
                           }
                       ) {
                           Text(text = "Add Category")
                       }
                   }
                   Spacer(modifier = Modifier.height(40.dp))
                   TransparentHintTextField(
                       text =contentState.text,
                       onValueChange = {
                           viewModel.enteredContent(it)
                       },
                       onFocusChange = {
                           viewModel.changeContentFocus(it)
                       },
                       isHintVisible = contentState.isHintvisible,
                       textStyle = MaterialTheme.typography.body1,
                       modifier = Modifier.fillMaxHeight()
                   )
               }
        }

}

@Composable
fun GroupedRadioButton(categories: List<Category>,viewModel: CreateNoteScreenViewModel) {
    val mRememberObserver = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    Row(verticalAlignment = CenterVertically, modifier = Modifier.horizontalScroll(state = scrollState).padding(end = 10.dp)) {
        categories.forEach { category ->
            Row (verticalAlignment = CenterVertically){
                RadioButton(
                    selected = mRememberObserver.value == category.category,
                    onClick = {
                                mRememberObserver.value = category.category
                                viewModel.chosenCategory(category.category)
                              },
                    enabled = true
                )
            }
            Text(text = category.category)
        }
    }
}




