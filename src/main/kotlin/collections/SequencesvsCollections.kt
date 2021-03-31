package collections

/**
 * https://blog.kotlin-academy.com/effective-kotlin-use-sequence-for-bigger-collections-with-more-than-one-processing-step-649a15bb4bf
 * Prefer sequences for large collection and if you are using multiple operators to transform the sequence
 *
 */
fun main() {

    println("Sequences are lazy.....................")
    sequenceOf(1,2,3)
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it, "); it * 2 }
        .forEach { print("E$it, ") } // Prints: F1, M1, E2, F2, F3, M3, E6,

    println("\nList are eager.....................")
    listOf(1,2,3)
        .filter { print("F$it, "); it % 2 == 1 }
        .map { print("M$it, "); it * 2 }
        .forEach { print("E$it, ") } // Prints: F1, F2, F3, M1, M3, E2, E6,
}