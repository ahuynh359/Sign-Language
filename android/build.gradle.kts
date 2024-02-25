buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.1")
        val nav_version = "2.7.7"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }


}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.dagger.hilt.android") version "2.46.1" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}
allprojects {
    repositories {
        maven { url = uri("https://www.jitpack.io" ) }
    }
}