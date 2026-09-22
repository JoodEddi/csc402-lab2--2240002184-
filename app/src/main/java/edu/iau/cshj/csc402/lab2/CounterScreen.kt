package edu.iau.cshj.csc402.lab2


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AttendanceCounter() {
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("STUDENTS PRESENT", fontWeight = FontWeight.Bold, color = Color.Gray)
        Spacer(modifier = Modifier.height(12.dp))
        Card(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "$count",
                fontSize = 52.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { if (count > 0) count-- },
                enabled = count > 0,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) { Text("−") }

            Button(
                onClick = { count++ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) { Text("+") }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = { count = 0 },
            enabled = count > 0
        ) { Text("Reset") }

        Spacer(modifier = Modifier.height(16.dp))
        val message = if (count == 0) "Tap + to check a student in." else "$count of 30 students checked in."
        Text(message, color = Color.DarkGray)
    }
}