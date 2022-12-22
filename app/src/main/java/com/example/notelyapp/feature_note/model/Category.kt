


package com.example.notelyapp.feature_note.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey val id: Int?=null,
    val category:String= "",
    var isSelected:Boolean,
)
