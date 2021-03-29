package coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.URL

fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        withContext(Dispatchers.IO)
        {
            doSomething(channel = channel)
       /*     println("Background thread that does some computation :${Thread.currentThread().name}")
            for (x in 1..5) channel.send((x * x).toString())
            channel.close() // we're done sending*/
        }
    }
    // here we print received values using `for` loop (until the channel is closed)

    for (y in channel) println(y)
    println("Main thread that prints value :${Thread.currentThread().name} and finishes")
}

suspend fun doSomething(channel: Channel<Int>) =  withContext(Dispatchers.IO) {

    println("Background thread that does some computation :${Thread.currentThread().name}")
    for (x in 1..5) channel.send((x * x))
    channel.close()
}