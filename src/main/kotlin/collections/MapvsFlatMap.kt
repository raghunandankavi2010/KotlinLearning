package collections

fun main() {

    // Map used to transform collection to another type
    val list = listOf(1, 2, 3, 4, 5)
    val squaredList = list.map {
        it * it
    }
    println(squaredList)

    val stringList = list.map {
        it.toString()
    }
    println(stringList)

    //flatmap merges two list into one list
    val maleList = listOf(
        User("Raghunandan", 34),
        User("Puttanna", 70)
    )

    val femaleList = listOf(
        User("Kavya", 34),
        User("Lakshmi", 70)
    )
    val usersList = listOf(maleList, femaleList)
    val mergedList = usersList.flatMap { it }
    println(mergedList)

    // flatten returns a single list form collection of lists
    val flattenedList = usersList.flatten()
    println(flattenedList)
}

data class User(
    val name: String,
    val age: Int
)