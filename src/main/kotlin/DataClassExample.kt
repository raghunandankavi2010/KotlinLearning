class DataClassExample {

    companion object {
        @JvmStatic
        fun main(vararg: Array<String>) {

            val user = User("Raghunandan", 30)
            // use copy to change a property and keeping other properties the same
            val userCopy = user.copy(age = 35)
            println(userCopy)

        }
    }

    data class User(var name: String, var age: Int)

}