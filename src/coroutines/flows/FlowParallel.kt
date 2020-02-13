package coroutines.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun requestFlow(i: Int): Flow<String> = flow {
    delay(500) // wait 500 ms
    emit("$i")
    println("Current Thread :{${Thread.currentThread().name}")
}.flowOn(Dispatchers.IO)

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow()
        .flatMapMerge {
            requestFlow(it)
        }.collect { value ->
            // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}