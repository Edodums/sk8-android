package unibo.it.lookup_api.presentation

sealed class LookupState(
    val isAction: Boolean = true,
    val isLoading: Boolean = false,
    val isFound: Boolean = false,
    val isPaired: Boolean = false
) {
    object Action : LookupState(isAction = true)
    object Loading : LookupState(isLoading = true)
    object Found : LookupState(isFound = true)
    object Paired : LookupState(isPaired = true)
}
