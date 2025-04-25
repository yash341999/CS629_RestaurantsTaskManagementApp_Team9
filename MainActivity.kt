package com.example.taskmanagerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerApp()
        }
    }
}

@Composable
fun TaskManagerApp() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabTitles = listOf("Shifts", "Tasks", "Chat")

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TabRow(selectedTabIndex = selectedTab) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = selectedTab == index, onClick = { selectedTab = index }) {
                    Text(text = title, modifier = Modifier.padding(12.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTab) {
            0 -> ShiftScheduleScreen()
            1 -> TaskAssignmentScreen()
            2 -> ChatScreen()
        }
    }
}

@Composable
fun ShiftScheduleScreen() {
    val schedule = listOf(
        "Monday: Alice (9am-5pm), Bob (12pm-8pm)",
        "Tuesday: Alice, Charlie",
        "Wednesday: Bob, Charlie",
        "Thursday: Alice, Eve",
        "Friday: Bob, Eve",
        "Saturday: Charlie, Eve",
        "Sunday: Alice, Charlie"
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Text("Weekly Shift Schedule", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
        LazyColumn {
            items(schedule) { item ->
                Text(text = item, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun TaskAssignmentScreen() {
    val tasks = listOf(
        "Alice - Kitchen Prep",
        "Bob - Table Service",
        "Charlie - Cashier",
        "Eve - Inventory",
        "Alice - Clean Up",
        "Bob - Restock"
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Text("Task Assignments", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
        LazyColumn {
            items(tasks) { task ->
                Text(text = task, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun ChatScreen() {
    var messages by remember { mutableStateOf(listOf<String>()) }
    var input by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("In-app Chat", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(messages) { msg ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(Color(0xFFE0E0E0))
                        .padding(8.dp)
                ) {
                    Text(msg)
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = input,
                onValueChange = { input = it },
                placeholder = { Text("Type a message...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
            Button(
                onClick = {
                    if (input.isNotBlank()) {
                        messages = messages + input
                        input = ""
                    }
                },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("Send")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    TaskManagerApp()
}
