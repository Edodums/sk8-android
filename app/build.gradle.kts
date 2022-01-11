import extensions.addComposeConfig
import extensions.addComposeDependencies

plugins {
    id(GradlePlugin.ANDROID_APPLICATION)
    id(GradlePlugin.KOTLIN_ANDROID)
    id(GradlePlugin.KOTLIN_QUALITY)
}


android {
    defaultConfig {
        applicationId = "unibo.it.sk8"
        compileSdk = Versions.compileSdk
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Releases.versionCode
        versionName = Releases.versionName
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        setProperty("archivesBaseName", "${parent?.name?.capitalize()}-$versionName")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }
    }

  /*  lint {
        warningsAsErrors = true
        abortOnError = true
        htmlReport = true
        checkDependencies = true

        lintConfig = file("${rootDir}/config/filters/lint.xml")
        htmlOutput = file("${buildDir}/reports/lint.html")
    }*/

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    addComposeConfig()

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(projects.libraries.core)
    implementation(projects.data.local)
    implementation(projects.data.datastore)
    implementation(projects.data.repository)
    implementation(projects.domain)
    implementation(projects.features.auth)

    implementation(Deps.logcat)
    implementation(Deps.compose.navigation)
    implementation(Deps.compose.activity)
    implementation(Deps.accompanist.animation)
    implementation(Deps.koin.android)
    implementation(Amplify.aws_core_kotlin)
    implementation(Amplify.aws_auth_cognito)
    implementation("androidx.compose.material:material:1.0.5")

    addComposeDependencies()
}

/*apply plugin: 'com.google.gms.google-services'*/
