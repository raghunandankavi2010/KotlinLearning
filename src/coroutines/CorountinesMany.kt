package coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * launch many coroutines
 */
fun main() = runBlocking {
    val start = System.currentTimeMillis()
    val jobs = List(10_000){
        launch {
            delay(1000) // delays for 1000 millis
            print(".")
        }
    }
    jobs.forEach { it.join() }
    val end = System.currentTimeMillis()
    println()
    println(end-start)
}