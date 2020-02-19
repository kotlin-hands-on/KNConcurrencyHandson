package sample

fun main() {
    setupThreading()

    println("\n\n---------------")

    // 1) Simple State
    // Just your usual mutable state, in the main thread.
//    runSimpleState()

    // 2) Frozen State
//    freezeSomeState()
//    failChanges()
//    freezeChildren()

    // 3) Global State
//    cantChangeThis()
//    canChangeThreadLocal()
//    threadLocalDifferentThreads()
//    companionAlsoFrozen()
//    globalCounting()
//    globalCountingFail()
//    globalCountingSharedFail()

    // 4) Background
//    basicBackground()
//    captureState()
//    captureTooMuch()
//    captureTooMuchAgain()
//    captureArgs()

    // 5) Debugging
//    ensureNeverFrozenFailNow()
//    ensureNeverFrozenFailLater()
//    ensureNeverFrozenBackground()
//    captureTooMuchInit()

    //Leave this please...
    teardownThreading()

    println("---------------\n\n")
}