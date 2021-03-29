package coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Corountines are light weight just like threads
 * You cannot launch 100's of threads in java but you can launch
 * 100's of corountines in koltin
 */
fun main() {
    runBlocking {
        repeat(100_000) { // launch a lot of coroutines
            launch {
                delay(1000L)
                println(".")
            }
        }
    }
}