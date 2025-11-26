plugins {
    alias(libs.plugins.runney.android.library)
    alias(libs.plugins.runney.jvm.ktor)
}

android {
    namespace = "com.phillqins.auth.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.auth.domain)
}