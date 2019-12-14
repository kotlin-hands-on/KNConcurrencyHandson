package sample

import kotlin.native.concurrent.FutureState
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker

fun cantChangeThis(){
    println("i ${DefaultGlobalState.i}")
    DefaultGlobalState.i++
    println("i ${DefaultGlobalState.i}") //We won't get here
}

object DefaultGlobalState{
    var i = 5
}

fun canChangeThreadLocal(){
    println("i ${ThreadLocalGlobalState.i}")
    ThreadLocalGlobalState.i++
    println("i ${ThreadLocalGlobalState.i}")
}

@ThreadLocal
object ThreadLocalGlobalState{
    var i = 5
}

fun globalCounting(){
    globalCounterData.i++
    println("count ${globalCounterData.i}")
    globalCounterData.i++
    println("count ${globalCounterData.i}")
}

var globalCounterData = SomeMutableData(33)

fun globalCountingFail(){
    val worker = Worker.start()
    worker.execute(TransferMode.SAFE,{}){
        try {
            globalCounterData.i++
            println("count $globalCounterData")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.result

}

fun globalCountingTil(){
    globalCounter++
    println("count $globalCounter")
    globalCounter++
    background {
        println("count $globalCounter")
        globalCounter++
        println("count $globalCounter")
    }

}
var globalCounter = 12