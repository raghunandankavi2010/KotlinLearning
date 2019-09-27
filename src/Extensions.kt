

/**
     Extension Function
 */
// the param is a default named param.
fun String.getFirstName(separator:String = " "):String {

    val separator = indexOf(separator)
    return substring(0,separator)

}