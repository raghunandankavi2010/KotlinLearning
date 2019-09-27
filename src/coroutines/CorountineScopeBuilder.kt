package coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Custom coroutine scope can be build with
 * @coroutineScope.
 * runBlocking keeps alive the main thread until all
 * coroutines are finished.
 * @result
 *
 * Task from coroutine scope ( delay is 100)
 * Task from runBlocking  ( delay is 200)
 * Task from nested launch  ( delay is 200)
 * Coroutine scope is over
 */

fun main() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking") // prints 2nd
    }

    coroutineScope { // Creates a new coroutine scope
        launch {
            delay(500L)
            println("Task from nested launch") // prints 3rd
        }

        delay(100L)
        println("Task from coroutine scope") // This line will be printed before nested launch // prints 1st
    }

    println("Coroutine scope is over") // This line is not printed until nested launch completes // prints 4th
}