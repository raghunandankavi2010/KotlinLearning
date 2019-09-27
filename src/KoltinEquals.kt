
fun main(){

    val student1 = Student("Raghunandan",23)
    val student2 = Student("Raghunandan",23)
    // structural equality == in kotlin same as .equals in java
    if(student1==student2){
        println("Both student contents are same")
    }else {
        println("Both student contents are not same")
    }

    // referetial equality ===
    if(student1 === student2){
        println("Both point to same object")
    }else {
        println("Both do not point to same object")
    }

    val a = Integer(10)
    val b = Integer(10)


    if(a == b){
        println("Both a and b have same contents")
    }else {
        println("Both a and b do not have same contents")
    }


    if(a === b){
        println("Both a and b point to same object")
    }else {
        println("Both a and b point to different object")
    }

}
// data class has implementation of equals hashcode and tostring
data class Student(var name:String,var id:Int)
