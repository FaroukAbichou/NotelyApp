package com.example.notelyapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notelyapp.feature_note.presentation.CreateNoteScreen.CreateNoteScreenViewModel
import com.example.notelyapp.feature_note.presentation.CreateNoteScreen.CreateNoteScreen
import com.example.notelyapp.feature_note.presentation.HomeScreen.HomeScreen
import com.example.notelyapp.feature_note.presentation.NotesScreen.NotesScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController){

    val createNoteScreenViewModel: CreateNoteScreenViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.NotesScreen.route )
    {
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController)
        }
        composable(
            route = Screen.NotesScreen.route
        ){

            NotesScreen(navController)
        }
        composable(
            route = Screen.CreateNoteWA.route
        ){

            CreateNoteScreen(navController)
        }


        composable(
            route = Screen.CreateNote.route + "/{Id}",
            arguments = listOf(
                navArgument("Id"){
                    type= NavType.StringType
                    nullable=true
                },
            )
        ){
            CreateNoteScreen(navController)
//                entry ->
//            entry.arguments?.getString("Id")?.let {
//                CreateNoteScreen(navController,
//                    Id = it.toInt(),
//                    )
//            }
        }
    }
}

