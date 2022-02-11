package unibo.it.datastore.model

internal data class User(
    val token: String?,
    val email: String?
) : Data
