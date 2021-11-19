package json


import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Project(val name: String, val language: String)

val format = Json { prettyPrint = true }

fun main() {
    val data = Json.decodeFromString<Project>("""
        {"name":"Scan18Nov21·04·53·26.pdf","language":"Kotlin"}
    """)

    println(data.name)
}