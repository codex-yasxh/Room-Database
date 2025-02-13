package com.helloworld.onetoonerelationship

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity() //table name change karne ke liye use kr skte hai
data class Contact(
    @PrimaryKey(autoGenerate = true)  // ✅ Primary key for Room Database and ID is going to be used as a primary key
    val id : Int? = null, // generating default values for ID
    val firstName : String,
    val lastName : String,
    val phoneNumber : String
)


