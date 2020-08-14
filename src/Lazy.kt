class Example {
    val name: String = "StackOverflow"

    val lazyVar: String by lazy {
        "$name something else"
    }
}
// Lazy to be used for val
// can be used where it requires another property ( internal to class)
// is thread safe
fun main(){
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