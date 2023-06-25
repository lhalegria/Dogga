package extensions

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

fun RepositoryHandler.applyDefault() {
    google()
    maven("https://maven.google.com/")
    maven("https://plugins.gradle.org/m2/")
}
