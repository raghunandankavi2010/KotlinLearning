package coroutines.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.Executors

/**
 *  Each emission will happen on a thread pool
 *  Collection of items will happen on main thread.
 */

// Good read about flow concurrency
fun main() {
    runBlocking<Unit> {
        (1..3).asFlow()
            .flatMapMerge {
                requestFlow(it)
            }.flowOn(Dispatchers.IO)
            .handleErrors()// does not matter where you put flow on emission happens on thread pool
            .collect { value ->
                // collect and print
                println("Collection Thread : ${Thread.currentThread().name}" + " collecting " + value)
            }
        //computationDispatcher.close()
    }
}


fun requestFlow(i: Int): Flow<String> = flow {
    delay(500) // wait 500 ms
    emit("$i")
    if (i == 2) error("Failed")
    println("Emission Thread : ${Thread.currentThread().name}" + " emitting " + i)
}



// to map extension function
suspend fun <K, V> Flow<Pair<K, V>>.toMap(): Map<K, V> {
    val result = mutableMapOf<K, V>()
    collect { (k, v) -> result[k] = v }
    return result
}

/**
 * https://medium.com/@elizarov/exceptions-in-kotlin-flows-b59643c940fb
 */
fun <T> Flow<T>.handleErrors(): Flow<T> =
    catch { e -> showErrorMessage(e) }

fun showErrorMessage(e: Throwable) {
    e.printStackTrace()
}

val computationDispatcher = Executors.newFixedThreadPool(3).asCoroutineDispatcher()


