plugins {
    id(GradlePlugin.ANDROID_LIBRARY)
}

dependencies {
    implementation(projects.data.repository)
    implementation(Deps.coroutines.core)
    implementation(Datastore.datastore_preferences)
    implementation(Datastore.datastore_core)
    implementation(Deps.koin.android)
    implementation(Deps.compose.viewModel)
}