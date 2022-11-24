package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

private val mutex = Mutex()
private var counterWithMutex = 0

/**
 *  run blocking keeps the main thread alive
 *  until launch ( child corountine) completes
 *  @result Hello World
 *  This is called structured concurrency
 */
suspend fun main() {
//    runBlocking {
//        launch {
//            // launch new coroutine in background and continue
//            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
//            print("World!") // print after delay
//        }
//        print("Hello ") // main thread continues while coroutine is delayed
//    }
    val job1WithMutex = CoroutineScope(Default).launch {
        for (i in 1..500) {
            incrementCounterByTenWithNoMutex()
        }
    }

    val job2WithMutex = CoroutineScope(Default).launch {
        for (i in 1..500) {
            incrementCounterByTenWithNoMutex()
        }
    }
    joinAll(job1WithMutex, job2WithMutex)
    println("With No Mutex Tally: $counterWithMutex")

}

private suspend fun incrementCounterByTenWithMutex() {
    mutex.withLock {
        for (i in 0 until 10) {
            counterWithMutex++
        }
    }
}

private suspend fun incrementCounterByTenWithNoMutex() {
    for (i in 0 until 10) {
        counterWithMutex++
    }
}
