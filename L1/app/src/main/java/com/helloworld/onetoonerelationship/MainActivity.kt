package com.helloworld.onetoonerelationship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.helloworld.onetoonerelationship.ui.theme.OneToOneRelationshipTheme

class MainActivity : ComponentActivity() {

    // Manually initialize the Room Database and DAO
    private lateinit var contactDAO: ContactDAO
    private lateinit var contactDatabase: ContactDatabase

    // Initialize ViewModel manually without Hilt
    private val viewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge mode (optional)
        enableEdgeToEdge()

        // Initialize the Room database and DAO manually
        contactDatabase = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contact_db"
        ).build()

        contactDAO = contactDatabase.contactDAO()

        // Pass the DAO to ViewModel manually
        val viewModelFactory = ContactViewModelFactory(contactDAO)
        viewModel = viewModelFactory.create(ContactViewModel::class.java)

        setContent {
            OneToOneRelationshipTheme {
                // Setting the Content Composable
                ContactScreen(
                    state = viewModel.state.collectAsState().value,
                    onEvent = { event -> viewModel.onEvent(event) }
                )
            }
        }
    }
}
