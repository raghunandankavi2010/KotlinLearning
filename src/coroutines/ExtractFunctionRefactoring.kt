package coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * runBlocking keeps main thread alive until all child threads
 * complete. we have extracted the code inside launch to a
 * separate function called printWorld which is suspending function.
 */

fun main() {
    runBlocking {
        launch {
            printWorld()
        }
        print("Hello ")
    }
}
suspend fun printWorld() {
    delay(1000L)
    print("World!")
}