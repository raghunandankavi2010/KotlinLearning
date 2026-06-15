package whatsnew240

/*
 * ============================================================================
 *  KOTLIN 2.4.0  —  SORTED-ORDER CHECKING FUNCTIONS  (new stdlib)
 * ============================================================================
 *
 *  Before 2.4 you wrote `list == list.sorted()` (allocates a whole new list!)
 *  just to ask "is this already sorted?". Now there are direct, allocation-free
 *  predicates: isSorted(), isSortedDescending(), isSortedBy { }, isSortedWith().
 *
 *  VISUAL — a single left-to-right scan comparing neighbours:
 *
 *      [ 1 , 2 , 2 , 5 , 9 ]
 *        └─≤─┘            isSorted() walks each adjacent pair:
 *            └─≤─┘            1≤2 ✓  2≤2 ✓  2≤5 ✓  5≤9 ✓   => true
 *                └─≤─┘
 *                    └─≤─┘
 *
 *      [ 3 , 1 , 2 ]
 *        └─≤─┘  3≤1 ✗  => false  (stops at first violation, O(n))
 */

data class Person(val name: String, val age: Int)

fun main() {
    val ascending = listOf(1, 2, 2, 5, 9)
    val descending = listOf(9, 5, 2, 1)
    val jumbled = listOf(3, 1, 2)

    println("ascending.isSorted()            = ${ascending.isSorted()}")
    println("jumbled.isSorted()              = ${jumbled.isSorted()}")
    println("descending.isSortedDescending() = ${descending.isSortedDescending()}")

    val people = listOf(
        Person("Alice", 24),
        Person("Bob", 31),
        Person("Carol", 29),
    )
    // Check ordering by a selector without sorting.
    println("people sorted by age?           = ${people.isSortedBy { it.age }}")
    println("people sorted by name?          = ${people.isSortedBy { it.name }}")

    /* Expected output:
     * ascending.isSorted()            = true
     * jumbled.isSorted()              = false
     * descending.isSortedDescending() = true
     * people sorted by age?           = false
     * people sorted by name?          = true
     */
}
