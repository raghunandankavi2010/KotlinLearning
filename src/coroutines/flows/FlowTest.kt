package coroutines.flows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@InternalCoroutinesApi
fun main() {
    zipWhenCompletesBeforeAnother()
    combineWithDelay()
}

/**
 * zip waits for both streams and then combines
 */
@InternalCoroutinesApi
fun zipWhenCompletesBeforeAnother() {
    val nums = (1..3).asFlow()
    val strs = flowOf("one","two","three","four")
    runBlocking {
        nums.zip(strs) { a,b -> "${Thread.currentThread().name} emitting $a -> $b" }
            .flowOn(Dispatchers.IO)
            .collect { value ->
                println("${Thread.currentThread().name} Receiving $value")
            }
    }
}

/**
 * combine combines both streams irrespective of the time
 */
fun combineWithDelay() {
    val nums = (1..3).asFlow().onEach { delay(300) }
    val strs = flowOf("one","two","three").onEach { delay(400) }
    runBlocking {
        nums.combine(strs) { a,b -> "${Thread.currentThread().name} emitting $a -> $b" }
            .flowOn(Dispatchers.IO)
            .collect { value ->
                println("${Thread.currentThread().name} Receiving $value")
            }
    }
}