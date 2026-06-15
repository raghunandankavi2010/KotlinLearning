package whatsnew240.coroutines_1_11

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeTo
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

/*
 * ============================================================================
 *  kotlinx.coroutines 1.11.0  —  ReceiveChannel.consumeTo(destination)
 * ============================================================================
 *
 *  Draining a channel into a list used to be a manual `for (x in channel)`
 *  loop. `consumeTo` drains every element into a destination MutableCollection
 *  and then closes/consumes the channel for you (so no leaks).
 *
 *  VISUAL — channel emptied into your own collection:
 *
 *      produce { send 1; send 2; send 3 }
 *              │ │ │
 *              ▼ ▼ ▼     .consumeTo(sink)
 *      ┌───────────────┐
 *      │ sink: [1,2,3] │   channel fully drained, then consumed (closed)
 *      └───────────────┘
 */

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val channel = produce {
        send("alpha")
        send("beta")
        send("gamma")
    }

    val sink = mutableListOf<String>()
    channel.consumeTo(sink)        // drains + consumes the channel

    println("drained into sink: $sink")

    /* Expected output:
     * drained into sink: [alpha, beta, gamma]
     */
}
