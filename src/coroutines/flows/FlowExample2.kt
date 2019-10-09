package coroutines.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

// Throws IllegalStateException. should use same context.
fun foo(): Flow<Int> = flow {
    kotlinx.coroutines.withContext(Dispatchers.IO) {
        log("Started foo flow")
        for (i in 1..3) {
            emit(i)
        }
    }
}

// The fix
@ExperimentalCoroutinesApi
fun fooFixed(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        log("Emitting $i")
        emit(i) // emit next value
    }
}.flowOn(Dispatchers.IO)

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    //foo().collect { value -> log("Collected $value") } // This will throw IllegalStateException as wrong context is used
    fooFixed().collect { value -> log("Collected $value") }
    // Use of Buffer
    fooFixed()
        .buffer() // buffer emissions, don't wait
        .collect { value ->
            delay(300) // pretend we are processing it for 300 ms
            println(value)
        }
}
