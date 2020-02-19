package sample

import co.touchlab.stately.concurrency.ThreadRef
import kotlin.native.concurrent.Future
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker
import kotlin.native.concurrent.freeze

private val worker = Worker.start()
private val collectFutures = mutableListOf<Future<*>>()

@SharedImmutable
private val mainThreadRef: ThreadRef by lazy {
    ThreadRef()
}

fun background(block: () -> Unit) {
    val future = worker.execute(TransferMode.SAFE, { block.freeze() }) {
        it()
    }
    collectFutures.add(future)
}

fun setupThreading() {
    //Need to record the first thread as the main thread
    isMainThread
}

fun teardownThreading() {
    //Wrap it up!
    collectFutures.forEach { it.result }
    worker.requestTermination()
}

@SharedImmutable
val isMainThread: Boolean
    get() = mainThreadRef.same()