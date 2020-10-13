plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}
group = "com.access_compnay.android.kmm_komitake_sample"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9-native-mt")
}
android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.access_compnay.android.kmm_komitake_sample.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}