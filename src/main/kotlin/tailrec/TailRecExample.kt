package tailrec

fun main() {

    println(fib(6))
    println(fact(4))
}

tailrec fun fib(n: Int, a: Int = 1, b: Int = 1): Int {
   val fibNum = a + b
    return if(n == 0 || n == 1) b
    else (fib(n - 1, fibNum, a))
}

tailrec fun fact(n: Int, b: Int = 1): Int {
    val factNum = n * b
    return if(n == 0)  b
    else fact(n-1,factNum)
}