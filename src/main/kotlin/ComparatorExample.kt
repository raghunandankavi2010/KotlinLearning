fun main() {

    val list = ArrayList<Medicine>()
    list.add(Medicine("BloodPressure", 10))
    list.add(Medicine("BloodPressure", 1200))
    list.add(Medicine("Diabetes", 20))
    list.add(Medicine("Diabetes", 100))
    list.add(Medicine("Sugar", 105))
    list.add(Medicine("Sugar", 120))
    list.add(Medicine("Sugar", 100))

    val sortedList = list.sortedWith(compareBy<Medicine> { it.category }).filter { it.price == 100}

    for (obj in sortedList) {
        println(obj)
    }
}

data class Medicine(val category: String, val price: Int) {
    override fun toString(): String {
        return "$category $price"
    }
}

