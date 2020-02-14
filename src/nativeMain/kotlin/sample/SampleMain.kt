package sample

fun main() {
    println("\n\n---------------")

    // 1) Simple State
    // Just your usual mutable state, in the main thread.
//    runSimpleState()

    // 2) Frozen State
//    freezeSomeState()
//    failChanges()

    // 3) Global State
//    cantChangeThis()
//    canChangeThreadLocal()
//    globalCounting()
//    globalCountingFail()

    // 4) Background
//    basicBackground()
//    captureState()
//    captureTooMuch()
//    captureTooMuchAgain()
//    captureArgs()
//    captureTooMuchSource()

    // 5) Atomics
//    atomicIntDemo()
//    atomicThreadsDemo()
//    atomicRefDemo()

    // 6) Coroutines
    // See mt_coroutines branch...


    //Leave this please...
    waitForWorker()

    println("---------------\n\n")
}