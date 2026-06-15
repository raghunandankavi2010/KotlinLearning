package whatsnew240

/*
 * ============================================================================
 *  KOTLIN 2.4.0  —  @all: META-TARGET FOR ANNOTATIONS  (now STABLE)
 * ============================================================================
 *
 *  A property in Kotlin secretly expands into SEVERAL elements: the field,
 *  the getter, the setter, the constructor parameter. An annotation has to
 *  pick ONE target with a use-site prefix (@get:, @field:, ...). Forgetting
 *  one was a classic source of bugs (e.g. validation/serialization missing).
 *
 *  `@all:` applies the annotation to EVERY applicable target at once.
 *
 *  ONE property `name` fans out to many places:
 *
 *                         @all:Ann
 *                            │
 *            ┌───────────────┼───────────────┬───────────────┐
 *            ▼               ▼               ▼               ▼
 *        [field]         [getter]        [setter]      [constructor param]
 *         @field:Ann      @get:Ann        @set:Ann        @param:Ann
 *
 *  Before:  you had to write @get:Ann @field:Ann @param:Ann name: String
 *  Now:     @all:Ann name: String
 */

@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.VALUE_PARAMETER,
)
@Retention(AnnotationRetention.RUNTIME)
annotation class Tagged

class Account(
    // `@all:` puts @Tagged on the field, the getter, AND the constructor param.
    @all:Tagged val owner: String,
)

fun main() {
    // Reflectively prove the annotation landed on multiple targets.
    val field = Account::class.java.getDeclaredField("owner")
    val getter = Account::class.java.getDeclaredMethod("getOwner")

    println("@Tagged on field 'owner'?  ${field.isAnnotationPresent(Tagged::class.java)}")
    println("@Tagged on getter 'getOwner'? ${getter.isAnnotationPresent(Tagged::class.java)}")

    /* Expected output:
     * @Tagged on field 'owner'?  true
     * @Tagged on getter 'getOwner'? true
     */
}
