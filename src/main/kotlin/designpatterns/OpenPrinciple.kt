package designpatterns

fun main() {
    val dog = Dog()
    dog.move()
    dog.woof()
}

open class Animal {
    fun move() {
        println("I am moving!")
    }
}

class Dog: Animal() {
    fun woof() {
        println("woof!")
    }
}