<<<<<<<< HEAD:features/lookup-api/src/main/java/unibo/it/lookup_api/presentation/LookupState.kt
package unibo.it.lookup_api.presentation
========
package unibo.it.sk8.lookup
>>>>>>>> main:app/src/main/java/unibo/it/sk8/lookup/LookupState.kt

sealed class LookupState(
    val isAction: Boolean = true,
    val isLoading: Boolean = false,
    val isFound: Boolean = false
) {
    object Action : LookupState(isAction = true)
    object Loading : LookupState(isLoading = true)
    object Found : LookupState(isFound = true)
}
