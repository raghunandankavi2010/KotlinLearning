interface Produce<out T> {
    fun produce(): T
}

interface Consume<in T> {
    fun consume()
}
open class Fruits {
    open fun printFruits() {
        println("Frutis")
    }
}

open class Apple: Fruits() {
    override fun printFruits() {
        println("Apple Fruits")
    }
}


class KashmirApple : Apple() {
    override fun printFruits() {
        println("Kashmir Apple")
    }
}

class FruitsProductions : Produce<Fruits> {
    override fun produce(): Fruits {
        println("Fruits Productions")
        return Fruits()
    }
}

class AppleProductions : Produce<Apple> {
    override fun produce(): Apple {
        println("Apple Productions")
        return Apple()
    }
}

class KashmirAppleProductions : Produce<KashmirApple> {
    override fun produce(): KashmirApple {
        println("KashmirApple Productions")
        return KashmirApple()
    }
}

class FruitsConsumption : Consume<Fruits> {
    override fun consume() {
        println("Fruits consumed")
    }
}

class AppleConsumption : Consume<Apple> {
    override fun consume() {
        println("Apple consumed")
    }
}

class KashmirAppleConsumption : Consume<KashmirApple> {
    override fun consume() {
        println("KashmirApple consumed")
    }
}


fun main() {

    val a = A()
    val strs = a
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
    val fruitsProduce : Produce<Fruits> = FruitsProductions()
    val appleProduce : Produce<Fruits> = AppleProductions()
    val kashmirApple : Produce<Fruits> = KashmirAppleProductions()

//    val fruitsProduce2 : Produce<KashmirApple> = FruitsProductions()
//    val appleProduce2 : Produce<KashmirApple> = AppleProductions()
    val kashmirApple2 : Produce<Apple> = KashmirAppleProductions()

    val consumer1 : Consume<KashmirApple> = FruitsConsumption()
    val consumer2 : Consume<KashmirApple> = AppleConsumption()
    val consumer3 : Consume<KashmirApple> = KashmirAppleConsumption()

}

interface Source<out T> {


    fun nextT(): T
}



class A : Source<Int> {
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
/**
 * in is used when you want to assign to a subtype
 * class Consumer<in :T> {
 *   fun toString(value: T): String {
 *     return value.toString()
 *   }
 * }
 * val consumer = Consumer<Number>
 * val c1:Consumer<Double> = consumer
 * Double is subtype of Number
 *
 * out is used when you when to assign it to supertype
 *
 * class Producer<out T>(private val value: T) {
 *    fun get() :T {
 *    return value
 * }
 * val producer = Producer("Raghu")
 * val p1: Producer<Any> = producer
 * Any is a super type of String
 *
 */

