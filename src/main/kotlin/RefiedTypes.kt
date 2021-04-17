

/**
 * https://stackoverflow.com/questions/45949584/how-does-the-reified-keyword-in-kotlin-work
 */

// Overloading return type using reified
inline fun <reified T> calculate(value: Float): T {
    return when (T::class) {
        Float::class -> value as T
        Int::class -> value.toInt() as T
        else -> throw IllegalStateException("Only works with Float and Int")
    }
}



fun main() {


    val intCall: Int = calculate(12.3f)
    val floatCall: Float = calculate(12.3f)

    println(intCall)
    println(floatCall)

}



/**
 * In android
 * inline fun <reified T:Any> Context.openActivity(noinline init: Intent.()-> Unit {}) {
 * var intent = newIntent<T>(this)
 * intent.init()
 * startActivity(intent)
 * }
 *
 * inline fun <reified T: Any> newIntent(context: Context): Intent =
 *              Intent(context,T::class.java)
 *
 * Usage : T is resolved to MainActivity
 * openActivity(MyActivity::class.java) {
 * putString("string.key", "string.value")
 * putInt("string.key", 43)
 * }
 */


