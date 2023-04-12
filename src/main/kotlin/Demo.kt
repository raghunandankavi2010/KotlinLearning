
fun main(){

    val name = parseNameDemo("Raghunandan Kavi")
    val firstName = name[0]
    val lastName = name[1]

    val numbers = listOf(1,2,3,4,5,6,7,8)
    val evenNumbers = numbers.filter {
        it%2==0
    }
    evenNumbers.forEach {
        println(it)
    }
    println("First Name: $firstName")
    println("Last Name: $lastName")

    // demonstrating const val vs val
    val someClass = SomeClass()
    someClass.print()
}

fun parseNameDemo(name:String):List<String>{
    val space = name.indexOf(' ')

    return listOf(name.substring(0,space), name.substring(space+1))
}

class SomeClass {

    companion object {
        // Const is inlined at the call site.
        // Say you are calling this from a java file
        // You would have to recompile so that you get updated value
        // as the value is inlined. So java file has to be recompiled
        // or you will see the old value
        // refer @ https://medium.com/@patxi/to-val-or-to-const-val-that-is-the-question-43ba428332cb
        const val BASE_URL_CONST ="https://www.google.com/"
    }

    val BASE_URL = "https://www.google.com/"

    fun print() {
        println(BASE_URL_CONST)
        println(BASE_URL)
    }
}