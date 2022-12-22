package com.example.notelyapp.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notelyapp.SetUpNavGraph
import com.example.notelyapp.feature_note.presentation.util.ui.theme.NotelyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
                NotelyAppTheme {

                    navController=rememberNavController()
                    
                    SetUpNavGraph(navController = navController)

                }
            }

    }
}

