import kotlinx.coroutines.*

data class Settings(
    var alwaysDarkTheme: Boolean,
    var transferData: Boolean,
    var receiveNotifications: Notifications
)

sealed class Notifications {
    object Off : Notifications()
    data class On(
        val muted: Boolean,
        val dubEmail: Boolean
    ) : Notifications()
}

suspend fun main() = coroutineScope {
    val settings: Settings? = null

    settings?.apply {
        alwaysDarkTheme = true
        transferData = false
        receiveNotifications = Notifications.On(
            muted = true,
            dubEmail = true
        )
    }
    // Job created with a builder is active
    val job = Job()
    println(job) // JobImpl{Active}@ADD
    // until we complete it with a method
    job.complete()
    println(job) // JobImpl{Completed}@ADD

    // launch is initially active by default
    val activeJob = launch {
        delay(1000)
    }
    println(activeJob) // StandaloneCoroutine{Active}@ADD
    // here we wait until this job is done
    activeJob.join() // (1 sec)
    println(activeJob) // StandaloneCoroutine{Completed}@ADD

    // launch started lazily is in New state
    val lazyJob = launch(start = CoroutineStart.LAZY) {
        delay(1000)
    }
    println(lazyJob) // LazyStandaloneCoroutine{New}@ADD
    // we need to start it, to make it active
    lazyJob.start()
    println(lazyJob) // LazyStandaloneCoroutine{Active}@ADD
    lazyJob.join() // (1 sec)
    println(lazyJob) //LazyStandaloneCoroutine{Completed}@ADD
}