plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
}

dependencies{
    implementation(projects.data.repository)

    implementation(Deps.koin.android)
    implementation(Deps.coroutines.android)
    implementation(Deps.coroutines.core)
    implementation(Deps.android.dataStore.datastore_core)
    implementation(Deps.android.dataStore.datastore_preferences)
    implementation(Amplify.aws_datastore)
}