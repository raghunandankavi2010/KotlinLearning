import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
  Function ║ Receiver (this) ║ Argument (it) ║    Result     ║
╠══════════╬═════════════════╬═══════════════╬═══════════════╣
║ let      ║ this@MyClass    ║ String("...") ║ Int(42)       ║
║ run      ║ String("...")   ║ N\A           ║ Int(42)       ║
║ run*     ║ this@MyClass    ║ N\A           ║ Int(42)       ║ (modifies outer object)
║ with*    ║ String("...")   ║ N\A           ║ Int(42)       ║ (modifies outer object)
║ apply    ║ String("...")   ║ N\A           ║ String("...") ║
║ also     ║ this@MyClass    ║ String("...") ║ String("...")
 *
 */


/**
 * Scoping functions kotlin
 */
fun main(){

    val string = "Hello World"

    //also(string)
    //with(string) // object and takes a param
    //let(string)
    runext(string) //
    //apply(string) // apply some properties to object return type object
}

fun also(string :String){
    val result1 = string.also {
        //print(this) // Receiver refers to the class
        println(it) // Argument refers to the string
        // Block return value block return value
    }

    println("Result $result1")

}


fun with(string :String){
    println("With......")
    val result = with(string) {
        println(this) // this refers to the string
        44  //return value .No argument received
    }

    println("Result $result")

    // Another Example
    data class Person(var name: String, var tutorial : String)
    val person = Person("Raghunandan", "Kotlin")

    with(person) {
        name = "Raghunandan Kavi" // name is modified
        tutorial = "Kotlin tutorials" // tutorial also modified

    }

    println("Name: " + person.name+" tutorial :"+person.tutorial)

}

fun let(string:String) {
    val result = string.let {
        //print(this) // Receiver refers to the class
        println(it) // Argument refers to the string
        42 // Block return value block return value
    }
    println("Result $result")
}

fun runext(string:String){

    var myString: String? = null

    runBlocking {
        launch {
            delay(1000L)
            myString = "Hello World!"
        }

        launch {
            delay(2000L)
            myString?.run {
                println("$this is not empty")
            }
        }

    }

    val result2 = string.run {
        println(this) // this refers to the string
        43  //return value .No argument received
    }

    println("Result $result2")


    data class Person(var name: String, var tutorial : String)
    val person = Person("Raghunandan", "Kotlin")
    println("Before....")
    println("Name : ${person.name} Tutorials ${person.tutorial}")
    println("After....")
    person.run {
        name = "Raghunandan Kavi" // name is modified
        tutorial = "Kotlin tutorials" // tutorial also modified

    }

    println("Name : ${person.name} Tutorials ${person.tutorial}")
}

fun apply(string:String){

    val result = string.apply {
        println(this) // this refers to the string

    }
    println("Result $result")
}

