package whatsnew240.coroutines_1_11

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asDeferred
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
 * ============================================================================
 *  kotlinx.coroutines 1.11.0  —  CompletableDeferred.asDeferred()
 * ============================================================================
 *
 *  A CompletableDeferred is a "promise you fill in yourself" via complete().
 *  If you hand it to other code, that code could ALSO complete it — leaking
 *  write access. `asDeferred()` returns a READ-ONLY Deferred view: callers can
 *  await the result but cannot complete/cancel it.
 *
 *  VISUAL — capability narrowing:
 *
 *      producer ──owns──▶ CompletableDeferred  { complete(), cancel(), await() }
 *                                  │
 *                                  │ .asDeferred()
 *                                  ▼
 *      consumer ──gets──▶ Deferred (read-only)  { await() only }
 *
 *      So the consumer can READ the future value but cannot FORGE it.
 */

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val promise = CompletableDeferred<String>()

    // Expose only the read-only view to the consumer.
    val readOnly = promise.asDeferred()

    // Consumer: just awaits — it has no way to complete `readOnly`.
    val consumer = launch {
        println("consumer: waiting for result...")
        println("consumer: got '${readOnly.await()}'")
    }

    // Producer: the only one able to fulfil the promise.
    launch {
        println("producer: computing...")
        promise.complete("42")
    }

    consumer.join()

    /* Expected output (ordering of first two lines may interleave):
     * consumer: waiting for result...
     * producer: computing...
     * consumer: got '42'
     */
}
