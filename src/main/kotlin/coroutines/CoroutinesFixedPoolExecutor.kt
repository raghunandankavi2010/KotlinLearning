package coroutines

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory

fun log(msg: String) = println("[${Thread.currentThread().name}] [${Thread.currentThread().priority}]  $msg")

fun main() = runBlocking {

/*    val executorService = Executors.newSingleThreadExecutor { r ->
        Thread(r, "TestExecutor")
    }
    val dispatcher = executorService.asCoroutineDispatcher()*/

    val computationExecutor = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors()){
            r -> val t = Thread(r, "Computation")
                 t.priority = Thread.MAX_PRIORITY
                 t

    }

    val dispatcher1 = computationExecutor.asCoroutineDispatcher()


    repeat(4) {
        launch {
            withContext(dispatcher1) {
                checkThreadName("Computation")
                log("Start sleep $it")
                delay(2000)
                log("Finished sleep $it")
            }
            dispatcher1.close()
            check(computationExecutor.isShutdown)
        }
    }

}

fun checkThreadName(prefix: String) {
    val name = Thread.currentThread().name
    check(name.startsWith(prefix)) { "Expected thread name to start with '$prefix', found: '$name'" }
}


