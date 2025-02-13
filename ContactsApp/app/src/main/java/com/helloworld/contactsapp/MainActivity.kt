package com.helloworld.contactsapp

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
import com.helloworld.contactsapp.ui.theme.OneToOneRelationshipTheme

class MainActivity : ComponentActivity() {

    // Initialize Room Database and DAO
    private lateinit var contactDAO: ContactDAO
    private lateinit var contactDatabase: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize Room Database and DAO manually
        contactDatabase = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contact_db"
        ).build()

        contactDAO = contactDatabase.DAO

        // Pass DAO to ViewModel through ViewModelFactory
        val viewModelFactory = ContactViewModelFactory(contactDAO)
        val viewModel: ContactViewModel by viewModels { viewModelFactory }

        setContent {
                ContactScreen(
                    state = viewModel.state.collectAsState().value,
                    onEvent = { event -> viewModel.onEvent(event) }
                )
        }
    }
}
