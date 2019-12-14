package sample

import kotlin.native.concurrent.AtomicInt
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze
import kotlin.native.concurrent.isFrozen

fun atomicIntDemo() {
    val dc = AtomicDataCounter()
    dc.freeze()
    dc.ai.increment()
    println("dc $dc")
}

class AtomicDataCounter {
    val ai = AtomicInt(3)
}

fun atomicThreadsDemo() {
    val dc = AtomicDataCounter()
    background {
        dc.ai.increment()
        println("dc ${dc.ai.value}, is frozen ${dc.isFrozen}")
    }
}

fun atomicRefDemo() {
    val dr = AtomicDataReference().freeze()
    dr.ar.value = SomeData("Hello 🎸", 43).freeze()
    println("dr ${dr.ar.value}")
}

class AtomicDataReference {
    val ar = AtomicReference(SomeData("Hello 🚀", 32).freeze())
}