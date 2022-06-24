package tailrec

fun main() {

    println(fib(6))
    println(fact(4))
//    val num = 4
//    val fact = num.fact()
//    println(fact)
}

fun Int.fact() : Int {
    val num = this
    if(num == 0) return 1
    else return num*(num-1).fact()
}
tailrec fun fib(n: Int, a: Int = 1, result: Int = 1): Int {
   val fibNum = a + result
    return if(n == 0 || n == 1)  result
    else (fib(n - 1, fibNum, a))
}

tailrec fun fact(n: Int, result: Int = 1): Int {
    val factNum = n * result
    return if(n == 0)  result
    else fact(n-1,factNum)
}