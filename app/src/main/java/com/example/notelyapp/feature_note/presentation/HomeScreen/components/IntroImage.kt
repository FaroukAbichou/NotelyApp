package com.example.notelyapp.feature_note.presentation.HomeScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.notelyapp.R


@Composable
fun IntroImage() {
    val image= painterResource(id = R.drawable.notevector)
    Image(painter = image,contentDescription = "",
        modifier = Modifier.width(300.dp).height(400.dp),
        contentScale = ContentScale.Fit,
    )

}

@Composable
fun ExclamationImage(modifier: Modifier) {
    val image= painterResource(id = R.drawable.noteexcla)
    Image(painter = image,contentDescription = "",
        modifier = Modifier.width(150.dp).height(100.dp),
        contentScale = ContentScale.Fit,
    )

}