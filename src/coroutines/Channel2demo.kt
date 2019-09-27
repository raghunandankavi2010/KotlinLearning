package coroutines


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// produce is a builder

fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce(Dispatchers.IO) {
   // println("Background thread that does some computation :${Thread.currentThread().name}")
    for (x in 1..5) {
        println("Background thread that does some computation :${Thread.currentThread().name}")
        send(x * x)
    }
}

fun main() {
    doSomething()
}

fun doSomething() = runBlocking {

        launch {
            val squares = produceSquares()
            squares.consumeEach { println(it) }
            println("Main thread that prints value :${Thread.currentThread().name} and finishes")
        }
}

