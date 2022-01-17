package unibo.it.sk8.lookup

sealed class LookupState(
    val isAction: Boolean = true,
    val isLoading: Boolean = false,
    val isFound: Boolean = false
) {
    object Action : LookupState(isAction = true)
    object Loading : LookupState(isLoading = true)
    object Found : LookupState(isFound = true)
}
