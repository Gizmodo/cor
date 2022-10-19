import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
fun main() {
    networkRequest()
//        runPipeline()
}

/*

 fun runPipeline() {

     GlobalScope {
        val channel = Channel<String>()

        launch(CoroutineName("Producer")) {
            while (true) {
                // Boom!! Throws an exception!!!
                val data = fetchDataFromRemote()
                channel.send(data)
            }
        }

    }
     GlobalScope {
        val channel = Channel<String>()

        launch(CoroutineName("Consumer")) {
            for (data in channel) {
                println(data)
            }
        }
    }
}

*/
fun fetchDataFromRemote(): String {
    GlobalScope.launch {
        delay(1000)
        "test"
    }
    return "ew"
}

fun networkRequest() {

    GlobalScope.launch {
        log("Making first network request...")
        for (i in 1..3) {
            delay(1000)
            println("First: $i")
        }
        log("First network request made!")
    }
    GlobalScope.launch {
        delay(500)
        log("Making second network request...")
        for (i in 1..3) {
            delay(1000)
            println("Second: $i")
        }
        log("Second network request made!")
    }
    Thread.sleep(4000)
    log("Done!")
}
