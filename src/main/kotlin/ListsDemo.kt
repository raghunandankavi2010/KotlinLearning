

import java.util.*


fun main() {

    val allStudents: MutableList<Students> = ArrayList()

    val s1 = Students("Raghunandan","Kavi",34)
    val s2 = Students("Abhay","Gupta",29)
    val s3 = Students("Raghunandan","Kavi",25)

    allStudents.add(s1)
    allStudents.add(s2)
    allStudents.add(s3)

    val myList = allStudents.sortedWith( compareBy({it.firstName}, {it.lastName})).distinctBy { it.firstName to it.lastName }
    myList.forEach { println(it) }

    val xx = ArrayList<Int>()
    xx.addAll(listOf(8, 3, 1, 1, 4))
    xx.sortedWith(compareBy { it })

    // prints 8, 3, 1, 4
    xx.forEach { println(it) }

    println("Sorted List")

    val sortedXx = xx.sortedWith(compareBy { it })
    sortedXx.forEach { println(it) }
}

data class Students(var firstName: String, var lastName: String, var age: Int)