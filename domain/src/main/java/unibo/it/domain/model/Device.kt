package unibo.it.domain.model

data class Device(
    val UUID: String,
    val address: String,
    val data: List<Any>
)
