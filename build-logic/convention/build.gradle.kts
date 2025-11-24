plugins {
    `kotlin-dsl`
}

group = "com.phillqins.runney.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "runney.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
}