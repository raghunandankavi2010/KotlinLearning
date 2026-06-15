package whatsnew240

/*
 * ============================================================================
 *  KOTLIN 2.4.0  —  EXPLICIT BACKING FIELDS  (now STABLE)
 * ============================================================================
 *
 *  Problem it solves: you want a WIDE type internally (mutable) but a NARROW
 *  type publicly (read-only) — without writing a private `_backing` field and
 *  a manual getter.
 *
 *  OLD PATTERN (boilerplate)              NEW: `field:` explicit backing field
 *  --------------------------             ------------------------------------
 *  private val _items =                   val items: List<String>
 *      mutableListOf<String>()                field = mutableListOf()
 *  val items: List<String> get() = _items
 *
 *  VISUAL — one property, two faces:
 *
 *                    ┌─────────────────────────────────────────┐
 *   outside world ──▶│  items : List<String>      (read-only)   │
 *                    │         ▲                                 │
 *                    │         │ same storage                    │
 *                    │  field : MutableList<String> (read/write)│◀── inside class
 *                    └─────────────────────────────────────────┘
 */

class Playlist {
    // Public face is the immutable List; the backing `field` is mutable.
    val songs: List<String>
        field = mutableListOf()

    fun add(song: String) {
        // Inside the class we see the wide type and can mutate it.
        songs.add(song)            // `songs` here refers to the MutableList field
    }
}

fun main() {
    val playlist = Playlist()
    playlist.add("Stairway to Heaven")
    playlist.add("Bohemian Rhapsody")

    // From outside, `songs` is just a List — no `.add()` available.
    println("Songs (${playlist.songs.size}): ${playlist.songs}")
    // playlist.songs.add("x")   // <-- would NOT compile: List has no add()

    /* Expected output:
     * Songs (2): [Stairway to Heaven, Bohemian Rhapsody]
     */
}
