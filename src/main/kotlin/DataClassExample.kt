class DataClassExample {

    companion object {
        @JvmStatic
        fun main(vararg: Array<String>) {

            val child = Child("Raghunandan")
            println("${child.responseCode} ${child.data}")

            val user = User("Raghunandan", 30)

            // structural equality if both user 1 and user 2 have same content then they are equal
            // This is due to the fact that hashcode, equals and toString generated for data class is same
            val userU1 = User("Raghunandan", 30)
            val userU2 = User("Raghunandan", 30)
            if(userU1 == userU2) {
                println("hash code for user 1: ${userU1.hashCode()}\nhash code for user 2: ${userU1.hashCode()} ")
            } else {
                println("hash codes are different")
            }

            // For normal class the hashcode emp1 and emp2 would be different ie referential equality
            val emp1 = Employee("Raghu")
            val emp2 = Employee("Raghu")
            println("Default Implementation of hash code for emp1 \n${emp1.hashCode()}")
            println("Default Implementation of hash code for emp2 \n${emp2.hashCode()}")



            val user2 = User("Raghunandan", "Male", 34)
            // use copy to change a property and keeping other properties the same
            val userCopy = user.copy(age = 35)
            println(userCopy)
            println(user2)

            val tutorial = Tutorial("Java")

            println(tutorial.mName)


        }
    }

    // data classes must have at least one param for the constructor
    // data classes generate hashcode toString equals implementation
    data class User(var name: String, var age: Int) {

        private var gender: String = "Female "

        // secondary constructor
        constructor(name: String, gender: String, age: Int) : this(name, age) {
            this.gender = gender
        }

        // init block called after primary constructor
        init {
            // do some one time initializations
        }

        // In case you need to override hashCode, equals, toString
        /* override fun hashCode(): Int {
             return Objects.hash(name, age)
         }

         override fun equals(other: Any?): Boolean {
             if (this === other) return true
             if (other == null || javaClass != other::class.java) return false
             val user: User = other as User
             return name == user.name &&
                     age == user.age
         }

         override fun toString(): String {
             return "$name $age"
         }*/

    }

    // name is private to tutorial class
    // mName has a getter with not setter
    data class Tutorial(private var name: String) {
        val mName: String get() = name


    }

    open class Parent {
        var responseCode = 200
    }

    // kind of works but useless
    data class Child(var data: String) : Parent()

    // Sealed class with instance specific data
    sealed class Result {
        data class Success(var data: Any): Result()
        data class Failure(var throwable: Throwable): Result()
    }


}

class Employee(val name: String)