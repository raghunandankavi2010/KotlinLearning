package coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch {
       //delayedOne()
        sleepOne()
    }

    launch {
        println("This is run in case of delay as it in a non blocking call")
        println("Coroutine 2 ${Thread.currentThread().name}")
    }
    // IO has max(64,NO_OF_CPUS)
    launch(Dispatchers.IO) {

    }

    //  Dispatcher IO has max(64,NO_OF_CPUS). here 64 represents threads in thread pool
    //  Dispatcer.Default has minimum 2 or n tasks if n is the cpu number
    launch(Dispatchers.IO) {

    }
}

suspend fun delayedOne() {
    delay(1000)
    println("Delayed in coroutine")
}

suspend fun sleepOne() {
    Thread.sleep(1000)
    println("Sleep in coroutine a blocking call even IDE suggests using withContext with dispatcher")
}

