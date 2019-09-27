


fun main(){

    var a = A()
    var strs = a
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
}

interface Source<out T> {
    fun nextT(): T
}


class A :Source<Int> {
    override fun nextT(): Int {
        return 1
    }

}
/*interface Base
class Base1: Base

fun ArrayList<out Base>.myFun() = println(toString())

fun main(args: Array<String>) {
    val a= SimpleClass();
    var list = java.util.ArrayList<String>()
    list.add("A")
    a.addList(list);
    //val a =InsectInteractorMapperImp()

   /// arrayListOf(Base1(), Base1()).myFun()

}



interface InsectInteractorMapper<in T1,out T2> {
    fun map(A: T1 ): T2
    fun mapa(B: String): List<T2>
}

interface  SuperClass{
    abstract fun doSOmething()

}
class A:SuperClass{
    override fun doSOmething() {
       println("A")
    }

}

class B:SuperClass{
    override fun doSOmething() {
        println("B")
    }

}
class InsectInteractorMapperImp: InsectInteractorMapper<SuperClass,A> {
    override fun map(A: SuperClass): A {

        return A()
    }

    override fun mapa(B: String): List<A> {
        val insectDataModelList: MutableList<A> = mutableListOf()

        return insectDataModelList.toList()
    }
}*/
