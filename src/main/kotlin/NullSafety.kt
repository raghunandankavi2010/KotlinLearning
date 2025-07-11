fun main() {
    var name: String? = "Raghunandan"
    name = null
    println(name?.uppercase())

    name = "Raghunandan"
    name?.let { it ->
        println(it?.reversed())
    }

}