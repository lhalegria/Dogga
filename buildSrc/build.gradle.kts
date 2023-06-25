import Build_gradle.PluginsVersions.GRADLE_ANDROID
import Build_gradle.PluginsVersions.KOTLIN

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

plugins {
    `kotlin-dsl`
}

object PluginsVersions {
    const val GRADLE_ANDROID = "8.0.2"
    const val KOTLIN = "1.8.21"
}

dependencies {
    implementation("com.android.tools.build:gradle:$GRADLE_ANDROID")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN")
}
