import BuildSetup.APPLICATION_ID
import BuildSetup.BUILD_TOOLS_VERSION
import BuildSetup.COMPILE_SDK_VERSION
import BuildSetup.COMPOSE_KOTLIN_COMPILER
import BuildSetup.MIN_SDK_VERSION
import BuildSetup.NAMESPACE
import BuildSetup.TARGET_SDK_VERSION
import BuildSetup.TEST_INSTRUMENTATION_RUNNER
import BuildSetup.VERSION_CODE
import BuildSetup.VERSION_NAME

plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
}

@Suppress("UnstableApiUsage")
android {
    namespace = NAMESPACE
    compileSdk = COMPILE_SDK_VERSION
    buildToolsVersion = BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = APPLICATION_ID

        minSdk = MIN_SDK_VERSION
        targetSdk = TARGET_SDK_VERSION
        versionCode = VERSION_CODE
        versionName = VERSION_NAME

        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        debug {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }

        release {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = COMPOSE_KOTLIN_COMPILER
    }

    packaging {
        resources.excludes.addAll(
            listOf("/META-INF/{AL2.0,LGPL2.1}")
        )
    }
}

dependencies {
    implementation(AndroidX.CORE)
    implementation(AndroidX.LIFECYCLE)
    implementation(AndroidX.Compose.ACTIVITY)
    implementation(platform(AndroidX.Compose.BOM))
    implementation(AndroidX.Compose.LIFECYCLE)
    implementation(AndroidX.Compose.UI)
    implementation(AndroidX.Compose.UI_GRAPHICS)
    implementation(AndroidX.Compose.UI_TOOLING)
    implementation(AndroidX.Compose.MATERIAL_3)
    implementation(AndroidX.Compose.NAVIGATION)

    implementation(platform(Other.KOTLIN_BOM))
    implementation(Other.COIL)

    implementation(Koin.KOIN)
    implementation(Koin.KOIN_COMPOSE)

    implementation(Square.RETROFIT)
    implementation(Square.OKHTTP)
    implementation(Square.OKHTTP_LOG_INTERCEPTOR)
    implementation(Square.GSON_CONVERTER)

    testImplementation(Test.JUNIT)
    testImplementation(Test.MOCKK)
    testImplementation(Test.CORE_TESTING)
    testImplementation(Test.COROUTINES)
    androidTestImplementation(Test.ANDROIDX_JUNIT)
    androidTestImplementation(Test.ESPRESSO)
    androidTestImplementation(platform(AndroidX.Compose.BOM))
    androidTestImplementation(Test.COMPOSE_UI_JUNIT4)

    debugImplementation(AndroidX.Compose.UI_TOOLING)
    debugImplementation(Test.COMPOSE_TEST_MANIFEST)
}
