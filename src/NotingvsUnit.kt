import java.lang.RuntimeException

/**
 * Nothing represents a value that does not exist
 * Unit is similar to void in java.
 *
 */

fun main(){

    doSomething(6)
    unreachableCode()
}
// Nothing represents a value that does not exist
fun reportError():Nothing = throw RuntimeException()

fun doSomething(n: Int): String{
    if(n>5){
        return "OK"
    }
    reportError()

}

fun unreachableCode(){

    reportError()
    val i = 1 // unreachable code warning

}