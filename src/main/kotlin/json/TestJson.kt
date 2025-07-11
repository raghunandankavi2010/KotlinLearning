package json


import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Project(
    @SerialName("name") val name: String,
    @SerialName("language") val language: String,
    @SerialName("age") val age: Int?,
    )


val json = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
    isLenient = true
    explicitNulls = false

}

fun main() {
    val data = json.decodeFromString<Project>("""
        {"name":"Raghunandan Kavi","language":"Kotlin", "age":  null}
    """)

    println("My age is ${data.age}")
}