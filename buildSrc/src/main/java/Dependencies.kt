object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val kotlin = "1.6.10"

    const val compileSdk = 31
    const val targetSdk = 31
    const val minSdk = 21

    const val composeMaterial = "1.1.0-rc01"
    const val material = "1.3.0"
    const val ktx = "1.0.2"
    const val room = "2.4.1"
    const val dataStore = "1.0.0"
    const val playCore = "1.10.0"
    const val coroutines = "1.5.2"

    const val koin = "3.1.5"

    const val testJunit = "4.12"
    const val testRunner = "1.1.1"
    const val testCore = "1.4.0"
    const val testMockk = "1.12.0"
    const val testUiAutomator = "2.2.0"
    const val testJunitExt = "1.1.0"
    const val testRoom = "2.1.0"
    const val barista = "4.0.0"

    const val compose = "1.1.0-rc01"
    const val material3 = "1.0.0-alpha03"
    const val composeNav = "2.4.0-alpha10"
    const val composeVm = "1.0.0-alpha07"
    const val composeActivity = "1.3.1"
    const val appCompat = "1.4.1"

    const val accompanist = "0.21.5-rc"

    const val ktLint = "0.43.2"

    const val coil = "1.3.2"

    const val androidGradle = "7.0.4"
    const val gradle = "7.2.0-alpha06"
    const val bosch = "1.19.0"


    const val logging = "1.12.5"
    const val logback = "1.2.6"
    const val logcat = "0.1"
}

object Tools {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradle}"
}

const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.bosch}"

object Deps {
    const val logging = "io.github.microutils:kotlin-logging:${Versions.logging}"
    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
    const val logcat = "com.squareup.logcat:logcat:${Versions.logcat}"
    val android = AndroidDeps
    val coroutines = Coroutines
    val koin = Koin
    val compose = Compose
    val accompanist = Accompanist
    val test = TestDeos
    val quality = Quality
}

object AndroidDeps {
    const val appCompat =  "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val playCore = "com.google.android.play:core:${Versions.playCore}"
    val dataStore = Datastore
    val room = Room
}

object Accompanist {
    const val animation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
}

object KotlinDeps {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.koin}"
    const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    const val test = "io.insert-koin:koin-test:${Versions.koin}"
}

object Compose {
    const val material= "androidx.compose.material:material:${Versions.composeMaterial}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val material3 = "androidx.compose.material3:material3:${Versions.material3}"
    const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val icons = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val layout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNav}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeVm}"
    const val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val animation = "androidx.compose.animation:animation:${Versions.compose}"
    const val uiTest = "androidx.compose.ui:ui-test:${Versions.compose}"
    const val junit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val manifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

}

object TestDeos {
    const val junit = "junit:junit:${Versions.testJunit}"
    const val runner = "androidx.test:runner:${Versions.testRunner}"
    const val core = "androidx.test:core:${Versions.testCore}"
    const val coreKtx = "androidx.test:core-ktx:${Versions.testCore}"
    const val uiAutomator = "androidx.test.uiautomator:uiautomator:${Versions.testUiAutomator}"
    const val junitExt = "androidx.test.ext:junit:${Versions.testJunitExt}"
    const val mockk = "io.mockk:mockk:${Versions.testMockk}"
    const val room = "androidx.room:room-testing:${Versions.testRoom}"
    const val barista = "com.adevinta.android:barista:${Versions.barista}"
}

object Datastore {
    const val datastore_core = "androidx.datastore:datastore-core:${Versions.dataStore}"
    const val datastore_preferences =
        "androidx.datastore:datastore-preferences:${Versions.dataStore}"
}

object Room {
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    // optional - Kotlin Extensions and Coroutines support for Room
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
}

object Coil {
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"
}

object Amplify {
    private const val version = "1.31.0"
    const val aws_auth_cognito = "com.amplifyframework:aws-auth-cognito:$version"
    const val aws_datastore = "com.amplifyframework:aws-datastore:$version"
    const val aws_core_kotlin = "com.amplifyframework:core-kotlin:0.15.0"
}

object Reflections {
    const val reflection = "org.jetbrains.kotlin:kotlin-reflect:1.5.31"
}

object Desugar {
    const val desugar = "com.android.tools:desugar_jdk_libs:1.0.10"
}

object Gsm {
    const val gsm = "com.google.gms:google-services:4.3.10"
}

object Shimmer {
    const val shimmer = "com.valentinilk.shimmer:compose-shimmer:1.0.2"
}

object Quality {
    const val ktlint = "com.pinterest:ktlint:${Versions.ktLint}"
}