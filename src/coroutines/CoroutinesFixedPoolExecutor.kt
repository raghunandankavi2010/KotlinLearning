package coroutines

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main() = runBlocking {
    val computationExecutor = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors(),
        ThreadFactoryImpl("computation", Thread.MAX_PRIORITY)
    )

    val dispatcher = computationExecutor.asCoroutineDispatcher()
    //val context1 = Executors.newFixedThreadPool(2).asCoroutineDispatcher()

    repeat(4) {
        launch {
            withContext(dispatcher) {
                log("Start sleep $it")
                delay(2000)
                log("Finished sleep $it")
            }
        }
    }
    dispatcher.close()
    check(computationExecutor.isShutdown)

}

class ThreadFactoryImpl constructor(name: String,priority: Int): ThreadFactory {

    private var threadName: String = name
    private val threadPriority = priority

    override fun newThread(runnable: java.lang.Runnable): Thread {
        val thread = Thread(runnable, threadName)
        thread.priority = threadPriority
        return thread
    }

}