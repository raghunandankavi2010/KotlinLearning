

fun main(){
    val xx = ArrayList<Int>()
    xx.addAll(listOf(8, 3, 1, 4))
    xx.sortedWith(compareBy { it })

    // prints 8, 3, 1, 4
    xx.forEach { println(it) }

    println("Sorted List")

    val sortedXx = xx.sortedWith(compareBy { it })
    // prints sorted collection
    sortedXx.forEach { println(it) }
}
