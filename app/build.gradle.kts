plugins {
    id(Plugin.ANDROID_APPLICATION)
    id(Plugin.KOTLIN_ANDROID)
}


android {
    defaultConfig {
        applicationId = "unibo.it.sk8"
        versionCode = Releases.versionCode
        versionName = Releases.versionName

        compileSdk = Versions.compileSdk
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

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
    implementation(projects.libraries.test)

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
    implementation(Deps.compose.material)

    addComposeDependencies()
}
