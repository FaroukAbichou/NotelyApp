package com.example.notelyapp.feature_note.presentation.HomeScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notelyapp.Screen
import com.example.notelyapp.feature_note.presentation.HomeScreen.components.Intro
import com.example.notelyapp.feature_note.presentation.HomeScreen.components.IntroImage
import com.example.notelyapp.feature_note.presentation.util.ui.theme.ButtonPurple
import com.example.notelyapp.feature_note.presentation.util.ui.theme.White


@Composable
fun HomeScreen(
    navController:NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))

    ) {
        Intro()
        Column(Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom) {
            IntroImage()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp, end = 30.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {

        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.NotesScreen.route)
            },
            backgroundColor = ButtonPurple
        ) {
            Row(Modifier
                .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(text = "Let's Start",color = White)
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    tint= White,
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Add note"
                )
            }
        }

    }



}




