package com.example.notelyapp.feature_note.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Note(
    val title:String,
    val content:String,
    val category: String,
    val timestamp:Long,
    val color:Int,
    @PrimaryKey val id: Int?=null
)

class InvalidNoteException(message:String):Exception(message)
