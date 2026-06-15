package whatsnew240.coroutines_1_11

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
 * ============================================================================
 *  kotlinx.coroutines 1.11.0  —  SharedFlow.asFlow()
 * ============================================================================
 *
 *  A SharedFlow is HOT: it emits whether or not anyone listens, never
 *  completes, and exposes replay/subscriptionCount. Sometimes you want to hand
 *  out a plain COLD-looking Flow so callers can't depend on those hot details.
 *  `asFlow()` wraps it as a regular Flow facade.
 *
 *  HOT vs the COLD-looking facade:
 *
 *      MutableSharedFlow ──emits──▶ • • • • • •  (runs regardless of collectors)
 *            │  exposes: replayCache, subscriptionCount, tryEmit, never-ends
 *            │
 *            │ .asFlow()
 *            ▼
 *      Flow facade ──▶ just collect() ; none of the hot machinery is visible
 */

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    val hot = MutableSharedFlow<Int>(replay = 0)

    // Downstream sees only a Flow — no replayCache / subscriptionCount surface.
    val facade = hot.asFlow()

    val collected = launch {
        // SharedFlow never completes, so bound it with take(3).
        val first3 = facade.take(3).toList()
        println("collected: $first3")
    }

    // Give the collector a tick to subscribe, then emit.
    delay(50)
    repeat(5) { hot.emit(it) }

    collected.join()

    /* Expected output:
     * collected: [0, 1, 2]
     */
}
