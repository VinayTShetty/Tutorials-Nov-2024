fun main() {

    // =========================================
    // Example 1: groupBy() Using String List
    // =========================================

    // List of words
    val words = listOf("Apple", "Ant", "Ball", "Bat", "Cat")

    // -------------------------
    // Group by First Character
    // -------------------------
    // Key = first letter of each word
    //
    // A → Apple, Ant
    // B → Ball, Bat
    // C → Cat

    val groupedByFirstChar = words.groupBy { word ->
        word.first()
    }

    println("Grouped By First Character:")
    println(groupedByFirstChar)
    // Output:
    // {A=[Apple, Ant], B=[Ball, Bat], C=[Cat]}


    println("=========================================")


    // =========================================
    // Example 2: groupBy() Using Int List
    // =========================================

    // List of numbers
    val numbers = listOf(1, 2, 3, 4, 5, 6)

    // -------------------------
    // Group by Even / Odd
    // -------------------------
    // Key = (number % 2 == 0)
    //
    // false → Odd numbers
    // true  → Even numbers

    val groupedEvenOdd = numbers.groupBy { number ->
        number % 2 == 0
    }

    println("Grouped By Even/Odd:")
    println(groupedEvenOdd)
    // Output:
    // {false=[1, 3, 5], true=[2, 4, 6]}


    println("=========================================")


    // =========================================
    // Example 3: groupBy() Using Object List
    // =========================================

    data class Student(
        val name: String,
        val grade: String
    )

    val students = listOf(
        Student("Rahul", "A"),
        Student("Amit", "B"),
        Student("Priya", "A"),
        Student("Neha", "C")
    )

    // -------------------------
    // Group by Grade
    // -------------------------
    // Key = grade
    //
    // A → Rahul, Priya
    // B → Amit
    // C → Neha

    val groupedByGrade = students.groupBy { student ->
        student.grade
    }

    println("Grouped By Grade:")
    println(groupedByGrade)
    // Output:
    // {A=[Student(name=Rahul, grade=A), Student(name=Priya, grade=A)],
    //  B=[Student(name=Amit, grade=B)],
    //  C=[Student(name=Neha, grade=C)]}


    println("=========================================")


    // =========================================
    // Example 4: Counting using groupBy()
    // =========================================

    // Count students in each grade

    val countByGrade = students
        .groupBy { it.grade }
        .mapValues { entry ->
            entry.value.size
        }

    println("Count By Grade:")
    println(countByGrade)
    // Output:
    // {A=2, B=1, C=1}
}