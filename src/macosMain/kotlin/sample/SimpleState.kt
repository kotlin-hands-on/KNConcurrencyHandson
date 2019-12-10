package sample

fun runSimpleState(){
    val s = SimpleState()
    s.increment()
    s.increment()
    s.report()
    s.decrement()
    s.report()
}

class SimpleState{
    var count = 0

    fun increment(){
        count++
    }

    fun decrement(){
        count--
    }

    fun report(){
        println("My count $count")
    }
}