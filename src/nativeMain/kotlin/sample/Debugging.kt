package sample

import kotlin.native.concurrent.ensureNeverFrozen
import kotlin.native.concurrent.freeze

fun ensureNeverFrozenFailNow() {
    val sd = SomeData("a", 1)
    sd.freeze()
    sd.ensureNeverFrozen() //This will fail
}

fun ensureNeverFrozenFailLater() {
    val sd = SomeData("a", 1)
    sd.ensureNeverFrozen()
    sd.freeze() //This will fail
}

fun ensureNeverFrozenBackground() {
    val sd = SomeData("a", 1)
    sd.ensureNeverFrozen()
    background {
        println("Won't get here $sd")
    }
}

fun captureTooMuchInit() {
    val model = CountingModelEnsure()
    model.increment()
    println("I have ${model.count}") //We won't even get here
}

class CountingModelEnsure{

    init {
        ensureNeverFrozen()
    }

    var count = 0

    fun increment(){
        count++
        background {
            saveToDb(count)
        }
    }

    private fun saveToDb(arg:Int){
        //Do some db stuff
        println("Saving $arg to db")
    }
}