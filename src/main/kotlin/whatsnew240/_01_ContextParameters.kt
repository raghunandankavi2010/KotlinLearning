package whatsnew240

/*
 * ============================================================================
 *  KOTLIN 2.4.0  —  CONTEXT PARAMETERS  (now STABLE)
 * ============================================================================
 *
 *  Context parameters let a function declare dependencies it needs *implicitly*,
 *  without threading them through every call as explicit arguments.
 *  They replace the old experimental "context receivers".
 *
 *  WITHOUT context parameters            WITH context parameters
 *  ---------------------------           --------------------------------------
 *  fun save(db: Db, u: User)             context(db: Db)
 *  fun audit(db: Db, msg: String)        fun save(u: User)
 *  fun flow(db: Db) {                    context(db: Db)
 *      save(db, u)        <-- db         fun audit(msg: String)
 *      audit(db, "saved") <-- db         fun flow() {            <-- db is in scope
 *  }                                         save(u)             (no db passed!)
 *                                            audit("saved")
 *                                        }
 *
 *  VISUAL:  the context flows DOWN the call stack automatically
 *
 *        with(Logger) {              ┌──────────────────────────┐
 *            doWork()        ───────▶│  context: Logger present │
 *        }                           │     doWork()             │
 *                                    │       └─ log() finds it ─┘│  no param needed
 *                                    └──────────────────────────┘
 */

class Logger(val prefix: String) {
    fun emit(line: String) = println("$prefix $line")
}

// `context(log: Logger)` = "this function may only be called where a Logger is in scope".
context(log: Logger)
fun log(message: String) = log.emit(message)

context(log: Logger)
fun greet(name: String) {
    log("Greeting $name")          // <-- Logger is implicitly available here
    println("Hello, $name!")
}

fun main() {
    val logger = Logger(prefix = "[APP]")

    // `with(logger)` introduces the Logger into the context scope.
    with(logger) {
        greet("Raghu")             // we never pass `logger` explicitly
        greet("Kotlin 2.4")
    }

    /* Expected output:
     * [APP] Greeting Raghu
     * Hello, Raghu!
     * [APP] Greeting Kotlin 2.4
     * Hello, Kotlin 2.4!
     */
}
