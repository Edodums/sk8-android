package unibo.it.domain.model

import java.util.*

data class Device(
    val UUID: String,
    val address: String,
    val name: String,
    val data: List<String>,
    var isConnected: Boolean,
    val createdAt: Date
)
