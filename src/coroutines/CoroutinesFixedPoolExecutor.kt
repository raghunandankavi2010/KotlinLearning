package coroutines

import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main() = runBlocking {

    val context1 = Executors.newFixedThreadPool(2).asCoroutineDispatcher()

    repeat(4) {
        launch {
            withContext(context1) {
                log("Start sleep $it")
                delay(2000)
                log("Finished sleep $it")
            }
        }
    }
}