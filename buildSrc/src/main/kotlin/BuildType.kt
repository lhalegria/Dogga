
interface BuildType {

    val isMinifyEnabled: Boolean

    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false

    const val applicationIdSuffix = ".debug"
    const val versionNameSuffix = "-DEV"
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
}
