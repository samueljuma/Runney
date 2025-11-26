plugins {
    alias(libs.plugins.runney.android.library)
    alias(libs.plugins.runney.jvm.ktor)
}

android {
    namespace = "com.phillqins.run.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}