plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
    id(GradlePlugin.COMPOSE)
}

dependencies {
    implementation(projects.libraries.core)
    implementation(projects.features.authApi)
    implementation(projects.domain)

    implementation(Deps.android.ktx)
    implementation(Deps.android.material)
    implementation(Deps.coroutines.core)
    implementation(Deps.koin.android)
    implementation(Deps.koin.compose)
    implementation(Amplify.aws_auth_cognito)
    implementation(Amplify.aws_core_kotlin)

    testImplementation(projects.libraries.test)
    androidTestImplementation(projects.libraries.test)
}
