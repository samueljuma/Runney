import com.android.build.gradle.LibraryExtension
import com.phillqins.convention.ExtensionType
import com.phillqins.convention.configureBuildTypes
import com.phillqins.convention.configureKotlinAndroid
import com.phillqins.convention.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.apply("org.jetbrains.kotlin.jvm")
            configureKotlinJvm()
        }
    }
}