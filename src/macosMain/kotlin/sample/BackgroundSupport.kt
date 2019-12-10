package sample

import platform.Foundation.NSThread
import kotlin.native.concurrent.Future
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker
import kotlin.native.concurrent.freeze

private val worker = Worker.start()

fun background(block: () -> Unit) {
    worker.execute(TransferMode.SAFE, { block.freeze() }) {
        it()
    }
}

fun waitForWorker(){
    //Cheating
    NSThread.sleepForTimeInterval(2.toDouble())
    worker.requestTermination()
}

@SharedImmutable
val isMainThread: Boolean
    get() = NSThread.isMainThread