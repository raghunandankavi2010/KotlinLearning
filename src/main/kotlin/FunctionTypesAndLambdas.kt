import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {

    test {
        println("Do Something")
    }
    test(::calculate)

    printMe("Raghunandan", ::print)

    inlined {
        println("Inlined function")
    }

    runBlocking {
        calculateExecutionTime {
            doSomething()
        }
    }
    println(1 add 2)
}

suspend fun doSomething() {
    delay(1000L)
    println("Printing after 1 second")
}

fun calculate() {
    println("Dosomething")
}

inline fun printMe(str: String, myPrint: (String) -> Unit) {
    myPrint(str)
}

inline fun inlined(block: () -> Unit) {
    println("before")
    block()
    println("after")
}

// calculate function execution time using inline function
inline fun calculateExecutionTime(myFun: () -> Unit) {
    val before = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
    println(before)
    myFun.invoke()
    val after = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
    println(after)
    println("Difference is ${after - before}")
}

fun test(abs: () -> Unit) {
    println("Started")
    abs()
    println("Ended")
}

// infix function
infix fun Int.add(number: Int): Int {
    return this + number
}


