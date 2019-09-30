package coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    // runBlocking waits for child coroutines to complete before it finishes
    runBlocking { // launch new coroutine in background and continue
       val stringValue = withContext(Dispatchers.IO){// with context - change coroutine context

            delay(2000L)
            val task =10
           task
        }

        withContext(Dispatchers.Default) {// uses shared background pool of threads

            val sum  = stringValue+10
            print("Value is :$sum")
        }
    }
}