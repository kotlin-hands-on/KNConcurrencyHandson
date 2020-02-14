package sample

import kotlin.native.concurrent.ensureNeverFrozen
import kotlin.native.concurrent.isFrozen

fun basicBackground(){
    println("Is main thread $isMainThread")
    background {
        println("Is main thread $isMainThread")
    }
}

fun captureState(){
    val sd = SomeData("Hello 🥶", 67)
    println("Am I frozen? ${sd.isFrozen}")
    background {
        println("Am I frozen now? ${sd.isFrozen}")
    }
}

fun captureTooMuch(){
    val model = CountingModel()
    model.increment()
    println("I have ${model.count}")

    model.increment()
    println("I have ${model.count}") //We won't get here
}

class CountingModel{
    var count = 0

    fun increment(){
        count++
        background {
            saveToDb(count)
        }
    }

    private fun saveToDb(arg:Int){
        //Do some db stuff
    }
}

fun captureTooMuchAgain(){
    val model = CountingModel()
    println("model is frozen ${model.isFrozen}")
    model.increment()
    println("model is frozen ${model.isFrozen}")
}

fun captureArgs(){
    val model = CountingModelSafer()
    model.increment()
    println("I have ${model.count}")

    model.increment()
    println("I have ${model.count}")
}

class CountingModelSafer{
    var count = 0

    fun increment(){
        count++
        saveToDb(count)
    }

    private fun saveToDb(arg:Int) = background {
        println("Doing db stuff with $arg, in main $isMainThread")
    }
}

fun captureTooMuchSource(){
    val model = CountingModel()
    model.ensureNeverFrozen()
    model.increment()
    println("I have ${model.count}") //We won't even get here
}