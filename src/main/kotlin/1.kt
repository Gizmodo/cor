import kotlinx.coroutines.*
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() = runBlocking {
    val a = async {
        println("I'm computing part of the answer")
        delay(4_000)
        6
    }
    val b = async {
        delay(5_000)
        println("I'm computing another part of the answer")
        delay(4_000)
        7
    }
    println("The answer is ${a.await() * b.await()}")

    logger.trace { "This is trace log" }
    logger.debug { "This is debug log" }
    logger.info { "This is info log" }
    logger.warn { "This is warn log" }
    logger.error { "This is error log" }
    runBlocking {
        val job: Job = launch(context = Dispatchers.Default) {
            println("[${Thread.currentThread().name}] Launched coroutine")
            delay(5_000)
            logger.debug { "[${Thread.currentThread().name}] Finished coroutine" }
        }
        println("[${Thread.currentThread().name}] Created coroutine")
        job.join()
        println("[${Thread.currentThread().name}] Finished coroutine")
    }
}