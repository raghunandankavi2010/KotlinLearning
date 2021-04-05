
class DataClassExample {

    companion object {
        @JvmStatic
        fun main(vararg: Array<String>) {

            val user = User("Raghunandan", 30)

            val user2 = User("Raghunandan","Male",34)
            // use copy to change a property and keeping other properties the same
            val userCopy = user.copy(age = 35)
            println(userCopy)
            println(user2)

        }
    }

    // data classes must have at least one param for the constructor
    // data classes generate hashcode toString equals implementation
    data class User(var name: String, var age: Int){

        private var gender: String = "Female "
        // secondary constructor
        constructor(name:String, gender: String, age: Int): this(name, age) {
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

}