import kotlin.properties.Delegates

fun main() {

    println("Observable Property")
    observeMe = "aaaa"
    observeMe = "bbbb"

}

var observeMe by Delegates.observable("a") { p, old, new ->
    println("${p.name} property $old became  $new")
}