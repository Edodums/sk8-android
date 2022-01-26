package unibo.it.datastore.model

internal data class User(
    val token: String?,
    val email: String?,
    var deviceConnected: Boolean = false
) : Data
