plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.6.0-M1-139"
}
repositories {
    mavenCentral()
}

val hostOs = System.getProperty("os.name")
val isLinux = hostOs == "Linux"
val isWindows = hostOs.startsWith("Windows")

kotlin {

    if (isLinux){
        linuxX64("native")
    } else if(isWindows){
        mingwX64("native")
    }else {
        macosX64("native")
    }

    sourceSets {
        val nativeMain by getting {
            dependencies {
                implementation("co.touchlab:stately-concurrency:1.1.7")
            }
        }
    }

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries {
            executable {
                entryPoint = "sample.main"
            }
        }
    }
}

// Use the following Gradle tasks to run your application:
// :runReleaseExecutableMacos - without debug symbols
// :runDebugExecutableMacos - with debug symbols