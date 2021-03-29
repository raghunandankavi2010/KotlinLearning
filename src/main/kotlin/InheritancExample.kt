fun main() {

    val myClass = MyClass()
    myClass.printMe()
}

open class BaseClass {

    open val name: String = "BaseClass"
    open fun printMe() {
        println("Base Class")
    }

    fun notOpen() {
        println("Not Open")
    }
}

interface BaseClass2 {
    fun printMe() {
        println("Base Class2")
    }
}

class MyClass : BaseClass(), BaseClass2 {

    override var name = "Hello Base Class"
    override fun printMe() {
        super<BaseClass>.printMe()
        super<BaseClass2>.printMe()
        println(name)
    }
}