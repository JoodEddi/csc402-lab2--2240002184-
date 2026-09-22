package edu.iau.cshj.csc402.lab2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CourseModel(val code: String, val title: String, val credits: Int, val days: String)

@Composable
fun CourseRow(course: CourseModel) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .height(40.dp)
                    .background(Color(0xFF4CAF50))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(course.code, fontWeight = FontWeight.Bold)
                Text(
                    course.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Text(course.days, fontSize = 11.sp, color = Color.Gray)
            }
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFE3F2FD)
            ) {
                Text("${course.credits} cr", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun CourseListScreen() {
    val courses = remember {
        listOf(
            CourseModel("CSC 402", "Mobile Application Programming", 3, "Mon / Wed"),
            CourseModel("CSC 311", "Database Systems", 4, "Sun / Tue"),
            CourseModel("CSC 340", "Operating Systems", 3, "Mon / Wed"),
            CourseModel("MATH 202", "Discrete Mathematics", 3, "Sun / Thu"),
            CourseModel("ENG 214", "Technical Writing", 2, "Tue")
        )
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(courses) { course ->
            CourseRow(course)
        }
    }
}

