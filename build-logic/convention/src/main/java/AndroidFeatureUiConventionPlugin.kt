import com.android.build.gradle.LibraryExtension
import com.phillqins.convention.addUiLayerDependencies
import com.phillqins.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureUiConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("runney.android.library.compose")
            }

            dependencies {
                addUiLayerDependencies(target)
            }

        }
    }
}