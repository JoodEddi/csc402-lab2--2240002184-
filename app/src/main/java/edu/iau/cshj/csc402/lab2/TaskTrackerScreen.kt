package edu.iau.cshj.csc402.lab2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TeamTask(val id: Int, val title: String, val owner: String, val isDone: Boolean = false)

@Composable
fun TaskRow(task: TeamTask, onToggle: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = task.isDone, onCheckedChange = { onToggle() })
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None,
                    color = if (task.isDone) Color.Gray else Color.Unspecified
                )
                Text(task.owner, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun TaskTrackerScreen() {
    val tasks = remember {
        mutableStateListOf(
            TeamTask(1, "Setup Repository", "Sara", isDone = true),
            TeamTask(2, "Complete Part A", "Sara", isDone = true),
            TeamTask(3, "Design Student ID Card", "Sara", isDone = false),
            TeamTask(4, "Build Task Tracker", "Sara", isDone = false)
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(color = Color(0xFF1A237E), modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem("${tasks.count { it.isDone }}", "Done")
                StatItem("${tasks.count { !it.isDone }}", "Open")
                StatItem("${tasks.size}", "Total")
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks, key = { it.id }) { task ->
                TaskRow(task = task, onToggle = {
                    val index = tasks.indexOf(task)
                    if (index != -1) {
                        tasks[index] = task.copy(isDone = !task.isDone)
                    }
                })
            }
        }

        Button(
            onClick = {
                val nextId = (tasks.maxOfOrNull { it.id } ?: 0) + 1
                tasks.add(TeamTask(nextId, "New task", "Sara", isDone = false))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Add task")
        }
    }
}

