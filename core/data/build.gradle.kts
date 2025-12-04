plugins {
    alias(libs.plugins.runney.android.library)
    alias(libs.plugins.runney.jvm.ktor)
}

android {
    namespace = "com.phillqins.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.bundles.koin)
    implementation(projects.core.domain)
    implementation(projects.core.database)
}