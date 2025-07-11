package json

import com.google.gson.Gson
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable


@Serializable
object MySingleton {
    val name: String = "MySingleton"
}

fun main() {
    println("--- Testing Gson (Expected to break the singleton) ---")
    gsonBreakingSingleton()
    println("\n__________________________________________________________________\n")
    println("--- Testing kotlinx.serialization (Expected to preserve the singleton) ---")
    kotlinxNotBreakingSingleton()
}

private fun kotlinxNotBreakingSingleton() {
    val json = Json { encodeDefaults = true }

    // serialize
    val serialized = json.encodeToString(MySingleton)
    println("kotlinx serialized JSON: $serialized")

    // deserialize
    val deserialized = json.decodeFromString<MySingleton>(serialized)

    println("MySingleton before serialization hashCode: ${System.identityHashCode(MySingleton)}")
    println("MySingleton after serialization hashCode: ${System.identityHashCode(deserialized)}")

    // This will be TRUE because kotlinx.serialization is smart about Kotlin 'object's
    println("Same instance: ${deserialized === MySingleton}")
}

private fun gsonBreakingSingleton() {
    val gson = Gson()

    // serialize
    val json = gson.toJson(MySingleton)
    println("Gson serialized JSON: $json")

    // deserialize
    val deserialized = gson.fromJson(json, MySingleton::class.java)

    println("MySingleton before serialization hashCode: ${System.identityHashCode(MySingleton)}")
    println("MySingleton after serialization hashCode: ${System.identityHashCode(deserialized)}")

    // This will be FALSE because Gson uses reflection and creates a NEW instance
    println("Same instance: ${deserialized === MySingleton}")
}