package coroutines.flows

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

// flow { is a flow builder
val intFlow = flow {
    for (i in 0 until 10) {
        delay(100)
        println("Emitting $i")
        emit(i) //calls emit directly from the body of a FlowCollector
    }
}


// A whole bunch of flow operators : https://kotlinlang.org/docs/reference/coroutines/flow.html#asynchronous-flow

 fun main(){
    runBlocking<Unit> {
        intFlow.collect { value -> println(value) }
        println("Emit Second Time : Same steam")
        // Flows are  cold ie emit items when you want to collect them
        intFlow.collect { value -> println(value) }

        // Cancelling a flow
        withTimeoutOrNull(250) { // Timeout after 250ms
            intFlow.collect { value -> println(value) }
        }
        println("Done")

        (0..100).asFlow()
            .map { it*it } // executed in IO
            .filter { it % 4 == 0 } //executed in IO
            .flowOn(Dispatchers.IO) //changes upstream context, asFlow, map and filter
            .map { it * 2 } // not affected, continues in parent context

        // Transformation operator
        (1..3).asFlow() // a flow of requests
            .transform { value ->
                emit("${value.toString()} Value String") // transform to string
            }
            .collect { result -> println(result) }
    }

 }