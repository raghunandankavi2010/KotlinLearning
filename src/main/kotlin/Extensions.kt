/**
Extension Function
 */
// the param is a default named param.
fun String.getFirstName(separator: String = " "): String {

    val separatorIndex: Int = indexOf(separator)
    return substring(0, separatorIndex)

}