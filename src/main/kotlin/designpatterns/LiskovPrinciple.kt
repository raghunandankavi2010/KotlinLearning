package designpatterns

/**
 * Credit : https://github.com/JamieCruwys/SOLID-principles-kotlin/blob/master/src/3-liskov-substitution-principle.kt
 */
fun main() {
    val vehicles: List<Vehicle> = VehicleRepository().getAll()
    for (vehicle in vehicles) {
        println(vehicle.registration)
    }
}

open class Vehicle(val registration: String)

data class Car(val reg: String) : Vehicle(reg)

data class Campervan(val reg: String, val numberOfBeds: Int) : Vehicle(reg)

// List of Vehicle both car and campervan has registration
// both are types of a Vehicle. So can be
// Instances of Car and Campervan can be used interchangeably wherever a Vehicle is expected
class VehicleRepository {
    fun getAll(): List<Vehicle> {
        val vehicles: MutableList<Vehicle> = ArrayList()
        vehicles.add(Car(reg = "AB66 GHJ"))
        vehicles.add(Campervan(reg = "CA66 MPR", numberOfBeds = 2))
        return vehicles
    }
}