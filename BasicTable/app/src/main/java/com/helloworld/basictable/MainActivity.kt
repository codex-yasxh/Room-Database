package com.helloworld.basictable

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.helloworld.basictable.ui.theme.BasicTableTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var roomDB: RoomDB  // ✅ Initialize RoomDB instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ✅ Initialize Room Database
        roomDB = Room.databaseBuilder(
            applicationContext,
            RoomDB::class.java,
            "RoomDB"
        ).build()

        setContent {
            BasicTableTheme {
                TaskScreen(roomDB)
            }
        }
    }
}

@Composable
fun TaskScreen(roomDB: RoomDB) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    val task = Tasks(0, 1, "First Task", "15 Feb", true)
                    roomDB.dao.upsertData(task)
                    Log.d("MainActivity", "Inserted Task: $task")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Insert Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    val tasks = roomDB.dao.getData() // Fetch tasks
                    Log.d("MainActivity", "Fetched Tasks: $tasks")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Fetch & Log Tasks")
        }
    }
}
