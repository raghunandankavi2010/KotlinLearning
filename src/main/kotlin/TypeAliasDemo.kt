data class User1(val name: String, val surname: String)
typealias Users = List<User1>

typealias Predicate = (String) -> Boolean

fun foo(p: Predicate) = p("Hello World")

fun main(args:Array<String>){

    val f: (String) -> Boolean = { it.length >3 }
    println(foo(f))
    val users:Users = listOf(User1("Raghunandan","Kavi"),User1("aaa","bbb"))

    println("${users.get(0)}")
}