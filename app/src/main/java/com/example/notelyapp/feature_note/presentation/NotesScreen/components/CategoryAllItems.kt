package com.example.notelyapp.feature_note.presentation.NotesScreen.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notelyapp.feature_note.model.Category
import com.example.notelyapp.feature_note.presentation.util.ui.theme.ButtonPurple


@Composable
fun CategoryAllItems(
    onGetAllNotes: (Category) -> Unit = {}
) {
    var selected by remember { mutableStateOf(false) }
    FloatingActionButton(
        onClick = {
            selected = !selected
            if (selected){
                onGetAllNotes(Category(isSelected = true))
                Log.d("tag","hello")
            }
        },
        backgroundColor = ButtonPurple,
    ) {
        Row(
            Modifier
            .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "All Notes", color = Color.White, )
        }
    }
}

