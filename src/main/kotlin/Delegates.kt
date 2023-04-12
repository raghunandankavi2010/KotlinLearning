import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun main() {

    println("Observable Property")
    println(observeMe)
    observeMe = "aaaa"
    println(observeMe)
    observeMe = "bbbb"
    println(observeMe)

    var p: String by Delegate()
    println(p)
    println(p)

    val file = "myfile.mpeg"
    val mediaFile = MediaFile(FileDownloader(file), FilePlayer(file))
    mediaFile.downLoad()
    mediaFile.play()

    val mediaFileDelegation = MediaFileDelegation(FileDownloader(file), FilePlayer(file))
    mediaFileDelegation.downLoad()
    mediaFileDelegation.play()

}

/**
 * Use kotlin by for delegation
 */

class MediaFileDelegation(fileDownloader: FileDownloader, filePlayer: FilePlayer) : Downloader by fileDownloader,
    Player by filePlayer

class FileDownloader(private val file: String) : Downloader {
    override fun downLoad() {
        print("$file downlaoded")
    }

}

class FilePlayer(private val file: String) : Player {
    override fun play() {
        print("$file playing")
    }

}


class MediaFile(private val downloader: Downloader, private val player: Player) : Downloader, Player {
    override fun downLoad() {
        downloader.downLoad()
    }

    override fun play() {
        player.play()
    }
}

interface Downloader {
    fun downLoad()
}

interface Player {

    fun play()
}

val name: String by lazy(LazyThreadSafetyMode.NONE) {
    "Raghunandan"
}

var observeMe by Delegates.observable("a") { p, old, new ->
    println("${p.name} property $old became  $new")
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "hello world"//"$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}