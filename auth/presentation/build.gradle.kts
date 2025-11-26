plugins {
    alias(libs.plugins.runney.android.feature.ui)
}

android {
    namespace = "com.phillqins.auth.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}