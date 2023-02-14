package test

data class Student(val id: Int, val subscribedCourses: List<Course>) {}

data class Course(val id: Int, val name: String, val isPaid: Boolean)

fun getStudents(): List<Student> {
    val coursesS1 = listOf<Course>(Course(1, "Maths", false), Course(2, "Arts", true))
    val coursesS2 = listOf<Course>(Course(3, "History", true), Course(4, "Biology", true))
    val coursesS3 = listOf<Course>(Course(3, "History", true), Course(5, "Physics", false))

    val studentList = mutableListOf<Student>()
    val s1 = Student(1, coursesS1)
    val s2 = Student(2, coursesS2)
    val s3 = Student(2, coursesS3)

    studentList.add(s1)
    studentList.add(s2)
    studentList.add(s3)

    return studentList

}

data class PaidCourses(val course: String)

fun main() {
    val studentsList = getStudents()

    val list = mutableListOf<PaidCourses>()
    studentsList.forEach { student ->
        val temp: List<PaidCourses> =
            student.subscribedCourses
                .filter { it.isPaid }
                .map { course -> PaidCourses(course.name) }
        list.addAll(temp)
    }

    val map = list.groupingBy { it }
        .eachCount()
        .toList()
        .sortedByDescending { it.second }.toMap()
    map.forEach {
        println(it)
    }
}