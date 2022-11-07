

fun main(){

    val car = Car.Suzuki
    Car.Suzuki.carName = "Suzuki Car"
    printCar(car)
    //Car.Honda.carName = "Honda Car"

    //println(Car.Honda.carName)


}

fun printCar(car: Car) {
    when(car) {
        Car.Honda -> {
            println(Car.Honda.carName)
        }
        Car.Suzuki -> {
            println(Car.Suzuki.carName)
        }
        Car.Maruti -> {
            println(Car.Maruti.carName)
        }

    }
}
enum class Car(var carName: String){
    Honda("Honda"),
    Suzuki("Suzuki"),
    Maruti("Maruti")
}