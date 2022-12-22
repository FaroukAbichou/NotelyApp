package com.example.notelyapp.feature_note.presentation.HomeScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Intro() {
    Column(modifier = Modifier.padding(top = 80.dp, start = 20.dp, end = 40.dp)) {
        Text(text = "Notely",fontSize=40.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Capture what’s on your mind & get a reminder later at the right place or time you can also voice memo & other features",fontSize=20.sp, fontStyle = FontStyle.Normal, lineHeight = 25.sp)

    }

}