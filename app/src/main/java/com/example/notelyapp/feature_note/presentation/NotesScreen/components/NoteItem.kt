package com.example.notelyapp.feature_note.presentation.NotesScreen.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notelyapp.feature_note.model.Note
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier,
    cornerRadius: Dp =10.dp,
    onDeleteClick: () -> Unit,
){

    Box(modifier = modifier) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 20.dp),
            elevation = 7.dp,
            shape = RoundedCornerShape(cornerRadius)
        )
        {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFEFFFF))
                .padding(20.dp))
            {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 10.dp))
                {
                    IconButton(
                        onClick = onDeleteClick,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete note",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                    Spacer(Modifier.padding(10.dp))
                    Column() {
                        Text(text = note.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        val simpleDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                        val currentDate = simpleDate.format(Date(note.timestamp))
                        Text(text = currentDate)
                        Text(text = note.category, fontSize = 20.sp, fontWeight = FontWeight.Light)
                    }
                    Spacer(Modifier.padding(10.dp))
                }
                Divider(Modifier.padding(vertical = 20.dp))

                Text(text = note.content,fontSize=20.sp, fontStyle = FontStyle.Normal, lineHeight = 25.sp, fontWeight = FontWeight.Light)
            }
        }

    }
}



