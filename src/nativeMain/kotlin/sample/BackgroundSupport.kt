package sample

import co.touchlab.stately.concurrency.ThreadRef
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker
import kotlin.native.concurrent.freeze
import kotlin.system.getTimeMillis

private val worker = Worker.start()

@SharedImmutable
private val mainThreadRef = ThreadRef()

fun background(block: () -> Unit) {
    worker.execute(TransferMode.SAFE, { block.freeze() }) {
        it()
    }
}

fun waitForWorker(){
    //Cheating
    val start = getTimeMillis()
    val end = start + 2000
    while (getTimeMillis() < end){
        //Spinning!
    }
    worker.requestTermination()
}

@SharedImmutable
val isMainThread: Boolean
    get() = mainThreadRef.same()