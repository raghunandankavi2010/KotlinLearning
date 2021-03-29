fun main() {

    test {
        println("Do Something")
    }
    test(::calculate)

}

fun calculate() {
    println("Dosomething")
}

fun test(abs: () -> Unit) {
    println("Started")
    abs()
    println("Ended")
}

