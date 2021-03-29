
/**
 * Demo the  koltin way
 * using class
 *
 */

data class FullName(val firstName:String, val lastName:String)


fun main(){

    //parseString()
    //extensionFunction()
    extensionProperties()

}

fun extensionFunction(){

    // pass separator
    val output = "Raghunandan,Kavi".getFirstName(",")
    // by default separator is space
    // val output = "Raghunandan Kavi".getFirstName()
    println(" First Name: $output" )
}

fun parseName(name:String):FullName{
    val space = name.indexOf(' ')

    return FullName(name.substring(0,space), name.substring(space+1))
}

fun parseString(){

    val name = parseName("Raghunandan Kavi")
    val firstName = name
    val lastName = name

    println("First Name: $firstName")
    println("Last Name: $lastName")

    if(name!=parseName("Raghunandan Kavi"))
    {
        println("Equals does not work")
    }else {
        println("Equals work's!")
    }
}

/**
 * Extension properties
 */
val String.firstName :String
get (){
    val separator = indexOf( " ")
    return substring(0,separator)
}

fun extensionProperties(){
    println("Raghunandan Kavi".firstName)
}

