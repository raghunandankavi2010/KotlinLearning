package whatsnew240

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/*
 * ============================================================================
 *  KOTLIN 2.4.0  —  UUID API IN THE COMMON STANDARD LIBRARY  (now STABLE)
 * ============================================================================
 *
 *  `kotlin.uuid.Uuid` is a MULTIPLATFORM UUID — no more java.util.UUID on JVM
 *  and a different type on Native/JS. One API everywhere.
 *
 *  A UUID is 128 bits = 16 bytes, rendered as 8-4-4-4-12 hex digits:
 *
 *       b3f1c0de - 1234 - 4abc - 89ef - 0123456789ab
 *      └──────┘   └──┘   └──┘   └──┘   └──────────┘
 *        8         4      4      4         12        = 32 hex chars + 4 dashes
 *
 *      version ─┘            └─ variant bits
 *
 *  Why multiplatform matters:
 *      JVM      ─┐
 *      Native   ─┼──▶  kotlin.uuid.Uuid   (same code compiles for all)
 *      JS/Wasm  ─┘
 */

@OptIn(ExperimentalUuidApi::class)
fun main() {
    val random: Uuid = Uuid.random()
    println("Random UUID : $random")

    // Round-trip through text.
    val text = "550e8400-e29b-41d4-a716-446655440000"
    val parsed = Uuid.parse(text)
    println("Parsed      : $parsed")
    println("Round-trips : ${parsed.toString() == text}")

    // Round-trip through raw bytes (the underlying 16 bytes).
    val bytes = parsed.toByteArray()
    val fromBytes = Uuid.fromByteArray(bytes)
    println("16 bytes?   : ${bytes.size == 16}")
    println("From bytes  : ${fromBytes == parsed}")

    /* Expected output (random line varies):
     * Random UUID : <random>
     * Parsed      : 550e8400-e29b-41d4-a716-446655440000
     * Round-trips : true
     * 16 bytes?   : true
     * From bytes  : true
     */
}
