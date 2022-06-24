
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