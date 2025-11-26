plugins {
    alias(libs.plugins.runney.android.library)
    alias(libs.plugins.runney.android.room)
}

android {
    namespace = "com.phillqins.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson) // Helps generate ids from the client side
    implementation(projects.core.domain)
}