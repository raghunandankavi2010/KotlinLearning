package coroutines.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class Product(val name: String, val company: String)

fun main() = runBlocking {
    val products = flowOf(
        Product("Product A", "Company A"),
        Product("Product B", "Company B"),
        Product("Product C", "Company A"),
        Product("Product D", "Company B")
    )

    products
        .groupToList { it.company }
        .collect { println(it) }

}

fun <T, K> Flow<T>.groupToList(getKey: (T) -> K): Flow<Pair<K, List<T>>> = flow {
    val storage = mutableMapOf<K, MutableList<T>>()
    collect { t -> storage.getOrPut(getKey(t)) { mutableListOf() } += t }
    storage.forEach { (k, ts) -> emit(k to ts) }
}
