/**
 * CSC 402 — Mobile Application Programming
 * Lab 2: Part A — Kotlin Fundamentals
 */
fun main() {
    println("=== Task 1 ===")
    task1()

    println("\n=== Task 2 ===")
    task2()

    println("\n=== Task 3 ===")
    task3()

    println("\n=== Task 4 ===")
    task4()

    println("\n=== Task 5 ===")
    task5()
    
    // Future tasks (task6(), task7(), etc.) can be called here later!
}

// -----------------------------------------------------------------------------
// PART A — Task 1: Variables, types and string templates
// -----------------------------------------------------------------------------
fun task1() {
    val university = "Imam Abdulrahman Bin Faisal University"
    val courseCode = "CSC 402"
    val creditHours = 3
    var studentsEnrolled = 28
    val isElective = false

    println("$courseCode has $studentsEnrolled students enrolled ($creditHours credit hours).")

    studentsEnrolled += 2
    println("$courseCode has $studentsEnrolled students enrolled ($creditHours credit hours).")

    println("University name length: ${university.length}")

    // courseCode = "CSC 403" // Val cannot be reassigned
}

// -----------------------------------------------------------------------------
// PART A — Task 2: Functions, default values and named arguments
// -----------------------------------------------------------------------------
fun greetStudent(name: String, course: String = "CSC 402"): String {
    return "Welcome to $course, $name!"
}

fun finalMark(quizzes: Double, project: Double, finalExam: Double): Double =
    (0.10 * quizzes) + (0.70 * project) + (0.20 * finalExam)

fun printBanner(title: String, width: Int = 40) {
    val line = "-".repeat(width)
    println(line)
    println(title)
    println(line)
}

fun task2() {
    printBanner("CSC 402 Lab 2")
    println(greetStudent("Sara"))
    println(greetStudent("Sara", "CSC 301"))

    val resultPositional = finalMark(10.0, 70.0, 20.0)
    val resultNamed = finalMark(project = 70.0, finalExam = 20.0, quizzes = 10.0)

    println("Positional argument result: $resultPositional")
    println("Named argument result:      $resultNamed")
}

// -----------------------------------------------------------------------------
// PART A — Task 3: Decisions with if and when
// -----------------------------------------------------------------------------
fun letterGrade(mark: Int): String = when (mark) {
    in 95..100 -> "A+"
    in 90..94  -> "A"
    in 85..89  -> "B+"
    in 80..84  -> "B"
    in 75..79  -> "C+"
    in 70..74  -> "C"
    in 65..69  -> "D+"
    in 60..64  -> "D"
    in 0..59   -> "F"
    else       -> "Invalid"
}

fun status(mark: Int): String = if (mark >= 60) "Pass" else "Fail"

fun task3() {
    val marks = listOf(97, 88, 74, 61, 45, 130)

    for (mark in marks) {
        val letter = letterGrade(mark)
        val passOrFail = status(mark)
        println("Mark $mark -> $letter ($passOrFail)")
    }
}

// -----------------------------------------------------------------------------
// PART A — Task 4: Null safety
// -----------------------------------------------------------------------------
fun describeTeam(teamName: String?): String {
    return teamName?.let { "Team: $it (${it.length} characters)" } ?: "Team not registered yet"
}

fun task4() {
    val nickname: String? = null
    val fullName: String = "Abdullah Al-Qahtani"

    println(fullName.length)
    println(nickname?.length) // Prints null because nickname is null

    println(nickname ?: "no nickname set")

    println(describeTeam("Team Falcon"))
    println(describeTeam(null))

    // println(nickname!!) // Throws NullPointerException (java.lang.NullPointerException)
}

// -----------------------------------------------------------------------------
// PART A — Task 5: Data classes and collections
// -----------------------------------------------------------------------------
data class Course(
    val code: String,
    val title: String,
    val credits: Int,
    val days: String
)

fun findCourse(list: List<Course>, code: String): String {
    val found = list.firstOrNull { it.code == code }
    return found?.title ?: "Course not found"
}

fun task5() {
    val courses = listOf(
        Course("CSC 402", "Mobile Application Programming", 3, "Mon / Wed"),
        Course("CSC 311", "Database Systems", 4, "Sun / Tue"),
        Course("CSC 340", "Operating Systems", 3, "Mon / Wed"),
        Course("MATH 202", "Discrete Mathematics", 3, "Sun / Thu"),
        Course("ENG 214", "Technical Writing", 2, "Tue")
    )

    println("--- All Courses ---")
    courses.forEach { println(it) }

    println("\n--- Courses with 3+ Credits ---")
    courses.filter { it.credits >= 3 }.forEach { println(it) }

    println("\n--- Course Codes ---")
    val courseCodes = courses.map { it.code }
    println(courseCodes)

    println("\n--- Total Credit Hours ---")
    val totalCredits = courses.sumOf { it.credits }
    println(totalCredits)

    println("\n--- Course Search ---")
    println(findCourse(courses, "CSC 402"))
    println(findCourse(courses, "CSC 999"))

    println("\n--- Copy & Immutability Check ---")
    val csc402 = courses.first { it.code == "CSC 402" }
    val updatedCsc402 = csc402.copy(credits = 4)
    println("Updated copy:  $updatedCsc402")
    println("Original list: ${courses.first { it.code == "CSC 402" }}")
}
