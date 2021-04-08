/**
 * Structured equals vs Referential equality
 */
fun main(){

    val student1 = student {
        name = "First Student"
        age = 11
    }
    val student2 = student {
        name = "First Student"
        age = 11
    }


    // structural equality == in kotlin same as .equals in java
    if(student1==student2){
        println("Both student contents are same")
    }else {
        println("Both student contents are not same")
    }

    // referential equality ===
    if(student1 === student2){
        println("Both point to same object")
    }else {
        println("Both do not point to same object")
    }
    
    val student3: Student = student1
    if(student3 == student1) {
        println("Student 3 and Student 1 contents are same")
    } else {
        println("Student 3 and Student 1 contents are same")
    }
    if(student3 === student1) {
        println("Student 3 and Student 1 point to same Student object")
    } else {
        println("Student 3 and Student 1 does not point to same Student Object")
    }

}
// data class has implementation of equals hashcode and toString
data class Student(val name:String,val age:Int )

class StudentBuilder {
    var name = ""
    var age = 0

    fun build() = Student(name,age)
}

// Use of kotlin dsl
// StudentBuilder.() is lambda receiver and you apply student properties.
fun student(student: StudentBuilder.()->Unit): Student = StudentBuilder().apply(student).build()



