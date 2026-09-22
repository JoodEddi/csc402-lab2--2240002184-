package edu.iau.cshj.csc402.lab2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreetingCard() {
    var nameInput by remember { mutableStateOf("") }
    var submittedName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Your name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { submittedName = nameInput.trim() },
            enabled = nameInput.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show greeting")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                if (submittedName.isBlank()) {
                    Text("Enter your name above.", color = Color.Gray)
                } else {
                    Text("Marhaba, $submittedName!", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("Your name has ${submittedName.length} letters.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = Color(0xFFE8F5E9)
                    ) {
                        Text("CSC 402 - Lab 2", color = Color(0xFF2E7D32), modifier = Modifier.padding(6.dp), fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
