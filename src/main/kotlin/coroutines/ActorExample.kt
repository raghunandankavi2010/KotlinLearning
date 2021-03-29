package coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


object IncCounter


fun CoroutineScope.counterActor() = actor<IncCounter>(Dispatchers.Main) {
    var counter = 0
    for (msg in channel) {
        updateView(++counter)
    }
}

fun main() = runBlocking {
    val counter = counterActor()
    withContext(Dispatchers.IO) {
        counter.send(IncCounter)
    }
    counter.close()
    println("View state: $viewState")
}


fun updateView(newVal: Int) {
    viewState = newVal
}

var viewState = 0

