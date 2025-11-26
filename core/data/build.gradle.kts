plugins {
    alias(libs.plugins.runney.android.library)
    alias(libs.plugins.runney.jvm.ktor)
}

android {
    namespace = "com.phillqins.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(projects.core.domain)
    implementation(projects.core.database)
}