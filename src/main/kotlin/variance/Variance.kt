package variance

/**
 * Covariance (`out`) and Contravariance (`in`) — by example.
 *
 * Visual: see `docs/variance.svg` (open in a browser/IDE). It is far easier to
 * grasp the picture than the words:
 *
 *   Base hierarchy:        Puppy  <:  Dog  <:  Animal       (<: means "is a subtype of")
 *
 *   Covariance  (out):     Producer<Puppy> <: Producer<Dog> <: Producer<Animal>   (SAME direction)
 *   Contravariance (in):   Consumer<Animal> <: Consumer<Dog> <: Consumer<Puppy>   (REVERSED direction)
 *
 * Rule of thumb — PECS: "Producer Extends, Consumer Super"
 *   - `out T` : the type only ever comes OUT (return values). Safe to widen.  -> covariant
 *   - `in  T` : the type only ever goes IN  (parameters).    Safe to narrow.  -> contravariant
 */

// ---------- The base type hierarchy ----------

open class Animal(val name: String) {
    fun feed() = println("  feeding animal '$name'")
}

open class Dog(name: String) : Animal(name) {
    fun bark() = println("  $name says woof")
}

class Puppy(name: String) : Dog(name)

// ---------- Covariance: `out` (a Producer only hands T OUT) ----------
// Because T never appears as an input, it is safe to treat a Producer<Puppy>
// as a Producer<Animal>: whatever it produces is at least an Animal.
class Producer<out T : Animal>(private val item: T) {
    fun produce(): T = item
}

// ---------- Contravariance: `in` (a Consumer only takes T IN) ----------
// Because T never appears as an output, it is safe to treat a Consumer<Animal>
// as a Consumer<Puppy>: something that can handle any Animal can surely handle a Puppy.
class Consumer<in T : Animal> {
    fun consume(item: T) = println("  consuming '${item.name}'")
}

fun main() {
    println("=== Covariance (out) — direction PRESERVED ===")
    val puppyProducer: Producer<Puppy> = Producer(Puppy("Rex"))

    // ✅ A Producer<Puppy> can stand in for a Producer<Animal>.
    val animalProducer: Producer<Animal> = puppyProducer
    animalProducer.produce().feed()

    // ❌ The reverse does NOT compile — an Animal producer is not a Puppy producer:
    // val bad: Producer<Puppy> = Producer<Animal>(Animal("Generic"))

    println()
    println("=== Contravariance (in) — direction REVERSED ===")
    val animalConsumer: Consumer<Animal> = Consumer()

    // ✅ A Consumer<Animal> can stand in for a Consumer<Puppy>.
    val puppyConsumer: Consumer<Puppy> = animalConsumer
    puppyConsumer.consume(Puppy("Bella"))

    // ❌ The reverse does NOT compile — a Puppy consumer can't accept any Animal:
    // val bad: Consumer<Animal> = Consumer<Puppy>()

    println()
    println("=== Same idea, from the standard library ===")
    // List<out E> is covariant:
    val puppies: List<Puppy> = listOf(Puppy("A"), Puppy("B"))
    val animals: List<Animal> = puppies        // ✅ List<Puppy> is a List<Animal>
    println("  ${animals.size} animals via a covariant List<out E>")

    // Comparable<in T> is contravariant:
    val animalComparator: Comparable<Animal> = object : Comparable<Animal> {
        override fun compareTo(other: Animal) = 0
    }
    val puppyComparator: Comparable<Puppy> = animalComparator   // ✅ reversed
    println("  Comparable<Animal> used as Comparable<Puppy>: ${puppyComparator.compareTo(Puppy("Z"))}")
}
