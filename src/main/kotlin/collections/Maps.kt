package collections

import java.util.*
import kotlin.collections.HashMap

class Maps {

}

data class Person(var name: String, var age: Int = 0, var phoneNumber: String?) {

    override fun hashCode(): Int {
         return Objects.hash(name, age,phoneNumber)
     }

     override fun equals(other: Any?): Boolean {
         if (this === other) return true
         if (other == null || javaClass != other::class.java) return false
         val person: Person = other as Person
         return name == person.name &&
                 age == person.age && phoneNumber == person.phoneNumber
     }

     override fun toString(): String {
         return "$name $age"
     }
}
fun main() {

    val calendar = Calendar.getInstance()
    val startTS = calendar.timeInMillis
    val prevYear = calendar.add(Calendar.YEAR, -1)
    val endTs = calendar.timeInMillis
    println("Start = $startTS end = $endTs")

    val person1 = Person("Raghunandan",34,"9986929644")
    val person2 = Person("Kiran",35,"897127464658")
    // map can also be initialized like below
    /* val map1 = mapOf<Person,String>( person1 to "9986929644",
        person2 to "8971246458"
    )*/

    val map = HashMap<Person,String?>()
    map[person1] = person1.phoneNumber
    map[person2] = person2.phoneNumber

    val person = Person("Raghunandan",34,"9986929644")
    println(map[person])
    map.forEach { (per, phone) ->
        if(per.name == "Raghunandan" && per.age == 34)
        println("$per = $phone")
    }

    val filteredMap = map.filter { (k,v) -> k.name == "Raghunandan" && k.age == 34 }
    filteredMap.forEach { (k,v) ->
        println("$v")
    }
}