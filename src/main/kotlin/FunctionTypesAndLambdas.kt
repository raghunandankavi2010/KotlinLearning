import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {

    test {
        println("Do Something")
    }

    printMe("Raghunandan\n", ::print)

    // lambda with type specified
    calculateSquare(10) { it: Int ->
         it*it
    }

    // using method reference
    calculateSquare(10,::square)
    calculateMultiply(10,20,::multiply)

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

fun multiply(x: Int, y: Int) : Int {
    return x*y
}
fun square(x: Int) : Int {
    return x*x
}
inline fun calculateSquare(x: Int, cal :(Int) -> Int) {
    val squaredNum = cal(x)
    println("Squared Num :$squaredNum")
}

inline fun calculateMultiply(x: Int, y: Int, cal :(Int, Int) -> Int) {
    val multipliedNum = cal(x,y)
    println("Multiplied Num :$multipliedNum")
}

suspend fun doSomething() {
    delay(1000L)
    println("Printing after 1 second")
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


