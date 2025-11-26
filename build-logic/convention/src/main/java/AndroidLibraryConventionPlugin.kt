import com.android.build.gradle.LibraryExtension
import com.phillqins.convention.ExtensionType
import com.phillqins.convention.configureBuildTypes
import com.phillqins.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension>(){
                configureKotlinAndroid(this)
                configureBuildTypes(commonExtension = this, extensionType = ExtensionType.LIBRARY)

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }
            }

            dependencies {
                "testImplementation"(kotlin("test"))
            }
        }
    }
}