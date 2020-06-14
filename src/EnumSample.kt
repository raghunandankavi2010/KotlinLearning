

fun main(){

    val honda = Car.Honda
    println(honda)
    Car.Honda.carname = "Honda Car"
    println(Car.Honda.carname)

}
enum class Car(var carname: String){
    Honda("Honda"),
    Suzuki("Suzuki"),
    Maruti("Maruti")
}