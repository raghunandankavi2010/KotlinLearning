package whatsnew240

/*
 * ============================================================================
 *  KOTLIN 2.4.0  —  UInt.toBigInteger() / ULong.toBigInteger()  (JVM)
 * ============================================================================
 *
 *  Unsigned types store the full positive range in the same bits a signed type
 *  uses. Converting a large unsigned value to a *signed* type would overflow
 *  into negatives. BigInteger has no upper bound, so it represents the true
 *  unsigned magnitude.
 *
 *  THE TRAP (bit pattern is identical, interpretation differs):
 *
 *      bits: 1111...1111 (64 ones)
 *            │
 *            ├─ as ULong  ──▶ 18446744073709551615   (max unsigned)
 *            └─ as Long   ──▶ -1                      (signed two's complement!)
 *
 *      ULong.MAX_VALUE.toLong()        => -1            (WRONG magnitude)
 *      ULong.MAX_VALUE.toBigInteger()  => 18446744073709551615   (CORRECT)
 */

fun main() {
    val maxULong = ULong.MAX_VALUE
    val maxUInt = UInt.MAX_VALUE

    println("ULong.MAX_VALUE.toLong()       = ${maxULong.toLong()}   (overflowed!)")
    println("ULong.MAX_VALUE.toBigInteger() = ${maxULong.toBigInteger()}")
    println("UInt.MAX_VALUE.toBigInteger()  = ${maxUInt.toBigInteger()}")

    // Arithmetic stays exact in BigInteger land.
    val sum = maxULong.toBigInteger() + maxUInt.toBigInteger()
    println("sum (exact, no overflow)       = $sum")

    /* Expected output:
     * ULong.MAX_VALUE.toLong()       = -1   (overflowed!)
     * ULong.MAX_VALUE.toBigInteger() = 18446744073709551615
     * UInt.MAX_VALUE.toBigInteger()  = 4294967295
     * sum (exact, no overflow)       = 18446744078004518910
     */
}
