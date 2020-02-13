package coroutines.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 *  Each emission will happen on a thread pool
 *  Collection of items will happen on main thread.
 */
@ExperimentalCoroutinesApi
fun requestFlow(i: Int): Flow<String> = flow {
    delay(500) // wait 500 ms
    emit("$i")
    println("Emission Thread :{${Thread.currentThread().name}")
}.flowOn(Dispatchers.IO)

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow()
        .flatMapMerge {
            requestFlow(it)
        }.collect { value ->
            // collect and print
            println("Collection Thread :{${Thread.currentThread().name}")
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}
// to map extension function
suspend fun <K, V> Flow<Pair<K, V>>.toMap(): Map<K, V> {
    val result = mutableMapOf<K, V>()
    collect { (k, v) -> result[k] = v }
    return result
}