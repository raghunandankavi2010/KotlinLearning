/**
 * Demo from the talk at google io
 *
 */

fun main(){

    val name = parseName_demo("Raghunandan Kavi")
    val firstName = name[0]
    val lastName = name[1]

    println("First Name: $firstName")
    println("Last Name: $lastName")
}

fun parseName_demo(name:String):List<String>{
    val space = name.indexOf(' ')

    return listOf(name.substring(0,space), name.substring(space+1))
}