package com.helloworld.basictable

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tasks")
data class Tasks(
    @PrimaryKey
    val id : Int,
    val userID : Int,
    val title : String,
    val dueDate : String,
    val status : Boolean

)
