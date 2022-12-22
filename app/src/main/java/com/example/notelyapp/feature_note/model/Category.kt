


package com.example.notelyapp.feature_note.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    val category:String= "",
    @PrimaryKey val id: Int?=null
)
