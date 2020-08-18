/**
 * Structured equals vs Referential equality
 */
fun main(){

    val student1 = student {
        name = "First Student"
        id = 11
    }
    val student2 = student {
        name = "First Student"
        id = 11
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

}
// data class has implementation of equals hashcode and toString
data class Student(var name:String? = null,var id:Int? = null)

// Use of koltin dsl
// Student.() is lambda receiver apply student properties.
fun student(student: Student.()->Unit): Student = Student().apply(student)


