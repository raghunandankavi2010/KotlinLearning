
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
}

fun parseNameDemo(name:String):List<String>{
    val space = name.indexOf(' ')

    return listOf(name.substring(0,space), name.substring(space+1))
}