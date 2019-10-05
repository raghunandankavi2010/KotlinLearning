import java.text.SimpleDateFormat
import java.util.*

fun person(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

/**
 *  Type Safe Builders
 */
class PersonBuilder {

    var name: String = ""
    var age : Int = 0
    private var dob: Date = Date()
    var dateOfBirth: String = ""
        set(value) {
            dob = SimpleDateFormat("yyyy-MM-dd").parse(value)
        }

    private val addresses = mutableListOf<Address>()

    // ADDRESSES.() receiver with addresses
    fun addresses(block: ADDRESSES.() -> Unit) {
        addresses.addAll(ADDRESSES().apply(block))
    }

    private var loves: Loves? = null

    fun loves(block: LovesBuilder.() -> Unit) {
        loves = LovesBuilder().apply(block).build()
    }



    fun build(): Person = Person(name, dob,age, addresses,loves)

}

class ADDRESSES: ArrayList<Address>() {

    fun address(block: AddressBuilder.() -> Unit) {
        add(AddressBuilder().apply(block).build())
    }

}

class AddressBuilder {

    var street: String = ""
    var number: Int = 0
    var city: String = ""

    fun build() : Address = Address(street, number, city)

}

class LovesBuilder {

    var game: String = ""

    fun build() : Loves = Loves(game)

}
data class Person(val name: String,
                  val dateOfBirth: Date,
                  val age: Int,
                  val addresses: List<Address>,val loves:Loves?)

data class Address(val street: String,
                   val number: Int,
                   val city: String)

data class Loves(val game: String)


fun main(args: Array<String>) {
    val p = person {
        name = "Raghunandan"
        dateOfBirth = "23-12-1986"
        age = 31
        loves {
            game = "Football"
        }

        addresses {
            address {
                street = "108 ganapati temple road"
                number = 23
                city = "Bangaluru"

            }
            address {
                street = "K.R.Road"
                number = 240
                city = "Bangaluru"

            }
        }
    }
    println(p)
}