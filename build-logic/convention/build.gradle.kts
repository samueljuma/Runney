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
        register("androidApplicationCompose") {
            id = "runney.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "runney.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "runney.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeatureUi") {
            id = "runney.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }
        register("androidRoom") {
            id = "runney.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmLibrary") {
            id = "runney.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("jvmKtor") {
            id = "runney.jvm.ktor"
            implementationClass = "JvmKtorConventionPlugin"
        }
    }
}