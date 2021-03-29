package coroutines

import kotlinx.coroutines.*

/**
 *  run blocking keeps the main thread alive
 *  until launch ( child corountine) completes
 *  @result Hello World
 *  This is called structured concurrency
 */
fun main() {
    runBlocking {
        launch {
            // launch new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            print("World!") // print after delay
        }
        print("Hello ") // main thread continues while coroutine is delayed
    }
}