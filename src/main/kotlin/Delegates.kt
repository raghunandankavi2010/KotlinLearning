import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun main() {

    println("Observable Property")
    println(observeMe)
    observeMe = "aaaa"
    println(observeMe)
    observeMe = "bbbb"
    println(observeMe)

    var p: String by Delegate()
    println(p)
    println(p)

}

val name: String by lazy(LazyThreadSafetyMode.NONE) {
    "Raghunandan"
}

var observeMe by Delegates.observable("a") { p, old, new ->
    println("${p.name} property $old became  $new")
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "hello world"//"$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}