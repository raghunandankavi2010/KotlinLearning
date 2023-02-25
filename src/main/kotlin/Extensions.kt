/**
Extension Function
 When there is  method with the same name as extension function in a class it takes precedence over extension function
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

    val myClasExtension = MyClasExtension()
    myClasExtension.doSomething()
}

fun Int.fact() : Int {
    val num = this
    return if(num == 0) 1
    else num*(num-1).fact()
}


class MyClasExtension {


    fun doSomething() {
        println("Do Something")
    }
}

fun MyClasExtension.doSomething() {
    println("Do Something else")
}