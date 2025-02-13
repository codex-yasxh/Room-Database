package com.helloworld.contactsapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDAO {

    // Insert or update a contact
    @Upsert
    suspend fun upsertContact(contact: Contact)

    // Delete a contact
    @Delete
    suspend fun deleteContact(contact: Contact)

    // Get contacts ordered by first name
    @Query("SELECT * FROM contacts ORDER BY firstName ASC")
    fun getContactOrderedByfirstName(): Flow<List<Contact>>

    // Get contacts ordered by last name
    @Query("SELECT * FROM contacts ORDER BY lastName ASC")
    fun getContactOrderedByLastName(): Flow<List<Contact>>

    // Get contacts ordered by phone number
    @Query("SELECT * FROM contacts ORDER BY phoneNumber ASC")
    fun getContactOrderedByPhoneNumber(): Flow<List<Contact>>
}
