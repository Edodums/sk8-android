package unibo.it.sk8.buildsrc

object Versions {
    const val ktLint = "0.43.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.4"
    const val ktLint = "com.pinterest:ktlint:${Versions.ktLint}"


    object Kotlin {
        private const val version = "1.5.31"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"

        object Coroutines {
            private const val version = "1.5.2"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object AndroidX {
        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        }

        const val appcompat = "androidx.appcompat:appcompat:1.3.0"

        object Compose {
            const val snapshot = ""
            const val version = "1.1.0-beta03"

            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val material = "androidx.compose.material:material:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val animation = "androidx.compose.animation:animation:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
            const val material3 = "androidx.compose.material3:material3:1.0.0-alpha02"
            const val icons = "androidx.compose.material:material-icons-extended:$version"
            const val navigation_compose = "androidx.navigation:navigation-compose:2.4.0-beta02"
            const val hilt_navigation_compose = "androidx.hilt:hilt-navigation-compose:1.0.0-beta01"
        }

        object Lifecycle {
            private const val version = "2.4.0"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
        }

        object Datastore {
            const val datastore_core = "androidx.datastore:datastore-core:1.0.0"
            const val datastore_preferences = "androidx.datastore:datastore-preferences:1.0.0"
        }

        object Room {
            private const val version = "2.3.0"

            const val room_runtime = "androidx.room:room-runtime:$version"
            const val room_compiler = "androidx.room:room-compiler:$version"

            // optional - Kotlin Extensions and Coroutines support for Room
            const val room_ktx = "androidx.room:room-ktx:$version"
        }
    }

    object JUnit {
        private const val version = "4.13"
        const val junit = "junit:junit:$version"
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:1.3.2"
    }

    object Hilt {
        private const val version = "2.40.4"

        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
    }

    object Amplify {
        private const val version = "1.31.0"
        const val aws_auth_cognito = "com.amplifyframework:aws-auth-cognito:$version"
        const val aws_datastore = "com.amplifyframework:aws-datastore:$version"
    }

    object Mockito {
        const val mockito_core = "org.mockito:mockito-core:4.1.0"
    }

    object Desugar {
        const val desugar = "com.android.tools:desugar_jdk_libs:1.0.10"
    }

    object Gsm {
        const val gsm = "4.3.10"
    }
}

object Urls {
    const val mavenCentralSnapshotRepo = "https://oss.sonatype.org/content/repositories/snapshots/"
    const val composeSnapshotRepo = "https://androidx.dev/snapshots/builds/" +
            "${Libs.AndroidX.Compose.snapshot}/artifacts/repository/"
}
