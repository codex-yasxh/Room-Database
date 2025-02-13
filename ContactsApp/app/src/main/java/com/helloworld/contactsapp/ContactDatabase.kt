package com.helloworld.contactsapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Contact::class],  // Your entities go here
    version = 1,
    exportSchema = false  // Disable schema export if you don't need it
)
abstract class ContactDatabase : RoomDatabase() {

    abstract val DAO: ContactDAO

    // Singleton pattern to ensure only one instance of the database is created
    companion object {
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contacts_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
