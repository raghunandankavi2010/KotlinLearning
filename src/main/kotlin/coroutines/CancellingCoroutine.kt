package coroutines

import kotlinx.coroutines.*


fun main() {
    runBlocking {
        val supervisor = SupervisorJob()
        with(CoroutineScope(coroutineContext + supervisor)) {
            val firstChild = launch(CoroutineExceptionHandler { _, _ -> }) {
                println("The first child is failing")
                throw AssertionError("The first child is cancelled")
            }
            val secondChild = launch {
                // Cancellation of the first child is not propagated to the second child
                println("The first child is cancelled: ${firstChild.isCancelled}, but the second one is still active")
                println("The second child is running: $isActive")
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    // But cancellation of the supervisor is propagated
                    println("The second child is cancelled because the supervisor was cancelled")
                }
            }

            //wait until first child fails and completes
            firstChild.join()
            println("Cancelling the supervisor")
            supervisor.cancel()
            secondChild.join()
        }
    }
}