fun main() {
    var name: String? = "Raghunandan"
    name = null
    println(name?.uppercaseChar())

    name = "Raghunandan"
    name?.let { it ->
        println(it?.reversed())
    }

}