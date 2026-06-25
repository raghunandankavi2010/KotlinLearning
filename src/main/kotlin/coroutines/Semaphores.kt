package coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.delay

fun main() = runBlocking {
    // Limit maximum concurrent tasks to 3
    val semaphore = Semaphore(permits = 3)

    repeat(10) { id ->
        launch {
            // Only 3 coroutines will execute this block simultaneously
            semaphore.withPermit {
                println("Task $id acquired a permit.")
                delay(1000) // Simulating network request or disk I/O
                println("Task $id is releasing permit.")
            }
        }
    }
}
