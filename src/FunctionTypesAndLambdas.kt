/**
 * The variable is declared as a function type that takes a String
   argument and returns a Boolean . The method itself is defined
   as a lambda enclosed in curly braces. In the shown lambda, the
   The shown main function demonstrates the usage of a higher-
   String parameter is declared and named before the -> symbol,
   order function by calling it with an anonymous function. The
   whereas the body follows after it.
 */

// credits dZone koltin cheat sheet

/*val myFunction: (String) -> Boolean = { s ->

    s.length > 3
}*/

fun main(){
    //println(myFunction("HelloWorld",1))
    higherOrderFunction()
}

// it refers to implicit argument string
val myFunction: (String) -> Boolean = {

    it.length > 3
}


// Int arg unused so _
val myFunction_noarg: (String,Int) -> Boolean = {it,_ ->

    it.length > 3
}

/**
 * A higher order function that takes function as a param
 * Here test is function that take string as input and
 * returns a Boolean as output
 * Notice the lambda expression (String) -> Boolean
 */
fun myHigherOrderFun(iterations: Int, test: (String) ->

Boolean){

    (0 until iterations).forEach {

        println("$it: ${test("my")}")

    }

}

fun higherOrderFunction(){

   // myHigherOrderFun(2,{ it.length > 2 })
    // can also be invoked as
    myHigherOrderFun(2){
        it.length>2
    }
}

