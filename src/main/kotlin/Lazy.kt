class Example {
    val name: String = "StackOverflow"

    val lazyVar: String by lazy {
        "$name something else"
    }
}
// delegate value computed one time
// value is Hello World
// the lazy block is executed one time when accessed first time
// next time you get cached value hello world
val lazyVar2: String by lazy {
    var i = 0
    println("Value of i ${++i}")
    "Hello World"
}
// Lazy to be used for val
// can be used where it requires another property ( internal to class)
// is thread safe
fun main(){
    println(lazyVar2)
    println(lazyVar2)
    val example  = Example()
    println("lazy = ${example.lazyVar}")
    val example2  = Example2()
    example2.printName("Raghu")
}
// lateinit used for var
// sometimes we would need to initialize the variable later not in constructor
// also dependency injection and in setup method of tests which cannot be of nullable type
class Example2 {
    lateinit var name : String

    fun printName(myname: String){
        this.name = myname
        println(name)
    }
}