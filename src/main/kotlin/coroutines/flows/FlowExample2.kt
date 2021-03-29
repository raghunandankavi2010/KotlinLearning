package coroutines.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

// Throws IllegalStateException. should use same context.
fun foo(): Flow<Int> = flow {
    kotlinx.coroutines.withContext(Dispatchers.IO) {
        log("Started foo flow")
        for (i in 1..3) {
            emit(i)
        }
    }
}

// The fix
@ExperimentalCoroutinesApi
fun fooFixed(): Flow<String> = flow {
    /* for (i in 1..3) {
         delay(100)
         log("Emitting $i")
         emit(i) // emit next value
     }*/
    emit(fetchAll())

}.flowOn(Dispatchers.IO)

@ExperimentalCoroutinesApi
fun main() {
    runBlocking<Unit> {
        //foo().collect { value -> log("Collected $value") } // This will throw IllegalStateException as wrong context is used
        fooFixed().collect { value -> log("Collected $value") }
        // Use of Buffer
        fooFixed()
            .buffer() // buffer emissions, don't wait
            .collect { value ->
                delay(300) // pretend we are processing it for 300 ms
                println(value)
            }
    }
}

private fun fetchAll(): String {
    return try {
        var inputstream: InputStream? = null
        try {
            val url = URL("https://truecaller.blog/2018/01/22/life-as-an-android-engineer/")
            val conn =
                url.openConnection() as HttpURLConnection
            conn.readTimeout = 10000
            conn.connectTimeout = 15000
            conn.requestMethod = "GET"
            conn.doInput = true
            conn.connect()
            val response = conn.responseCode
            if (response != 200) return "error:\nHTTP Status Code is $response"
            inputstream = conn.inputStream
            val reader: Reader = InputStreamReader(inputstream, "UTF-8")
            val br = BufferedReader(reader)
            val wc: MutableMap<String, Int?> = TreeMap()
            var line: String?
            while (br.readLine().also { line = it } != null) {
                val tokens =
                    line!!.split("[\\s\\d~`!@#\\$%\\^&\\*\\(\\)\\-\\+\\[\\]\\{\\}\'\"\\\\|/\\?,\\.;:]+")
                        .toTypedArray()

                for (element in tokens) {
                    var token = element
                    if (token == "") continue
                    token = token.toLowerCase()
                    var n = if (wc.containsKey(token)) wc[token]!! else 0
                    n++
                    wc[token] = n
                }
            }
            val sb = java.lang.StringBuilder()
            sb.append("result:\n")
            for ((key, value) in wc) {
                sb.append(key)
                    .append(" : ")
                    .append(value)
                    .append("\n")
            }
            sb.toString()
        } finally {
            inputstream?.close()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        "error:\nUnable to retrieve web page. URL may be invalid."
    }
}
