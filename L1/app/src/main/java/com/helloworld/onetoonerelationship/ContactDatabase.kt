package com.helloworld.onetoonerelationship

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(
    entities = [Contact::class],            //pass your entity which is Contact
    version = 1
)
abstract class ContactDatabase: RoomDatabase() {

    abstract val DAO : ContactDAO


}