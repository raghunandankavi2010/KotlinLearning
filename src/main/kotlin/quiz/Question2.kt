package quiz

/***
 * Source https://medium.com/@maydin/kotlin-quiz-refresh-your-kotlin-knowledge-1-2269065fd457
 */
fun main() {
    class Example {
        fun printFunctionType() { println("Class method") }
    }

    fun Example.printFunctionType() { println("Extension function") }

    Example().printFunctionType()// prints class method
}

// valid
fun Any?.toString(): String {
    if (this == null) return "null"
    return toString()
}