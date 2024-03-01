
fun main() {

    val testBackingField = TestBackingField()
    testBackingField.counter = -1
    println(testBackingField.counter)

    testBackingField.counter = 5
    println(testBackingField.counter)

    testBackingField.changeName("Raghunandan")
    println(testBackingField.name)


    val test = BackingProperty()
    test.age = 40
    test.print()

   // val animal = Animal("Dog")
    // this will give error and not compile
    // why class Animal generates setter and getter
    // getter is there but not with getHeight simply height
    //animal.height

    // Here height is encapsulated.
    val human = Human("Asian")
    human.setHeight(5)
    println("Height ${human.getHeight()}")
}

class Human(name: String) {

    // Here both set and get are private encapsulated
    // Access using setters and getters
    private var height = 0
        private set

    fun setHeight(height: Int) {
        this.height = height
    }

    fun getHeight() = height
}



// This is backing field
class TestBackingField {
    var counter = 0
        set(value) {
            if (value >= 0)
                field = value
        }

    // HERE SET IS PRIVATE SO CAN'T ACCESS OUTSIDE THE CLASS
    var name = ""
        private set(value) {
            if(value.isNotEmpty())
                field = value
        }

    fun changeName(nameChange: String) {
        name = nameChange
    }
}

class BackingProperty {

    private var _age = 20

    var age: Int
    get() {
       return _age
    }
    set(value){
        _age = value
    }

    // Here we use _age rather than age which is public
    fun print() {
        println(_age)
    }


}