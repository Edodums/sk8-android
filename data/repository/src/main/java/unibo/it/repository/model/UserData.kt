package unibo.it.repository.model

data class UserData(
    val token: String,
    val email: String,
    var deviceConnected: Boolean = false
)
