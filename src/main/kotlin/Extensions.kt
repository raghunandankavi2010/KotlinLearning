/**
Extension Function
 */
// the param is a default named param.
fun String.getFirstName(separator: String = " "): String {

    val separatorIndex: Int = indexOf(separator)
    return substring(0, separatorIndex)

}

fun main() {
    val num = 4
    val fact = num.fact()
    println(fact)
}

fun Int.fact() : Int {
    val num = this
    return if(num == 0) 1
    else num*(num-1).fact()
}