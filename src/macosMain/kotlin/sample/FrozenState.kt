package sample

import kotlin.native.concurrent.freeze
import kotlin.native.concurrent.isFrozen

fun freezeSomeState(){
    val sd = SomeData("Hello 🐶", 22)
    sd.freeze()

    println("Am I frozen? ${sd.isFrozen}")
}

data class SomeData(val s:String, val i:Int)

fun failChanges(){
    val smd = SomeMutableData(3)
    smd.i++
    println("smd: $smd")

    smd.freeze()
    smd.i++
    println("smd: $smd") //We won't actually get here
}

data class SomeMutableData(var i:Int)