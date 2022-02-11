package unibo.it.repository.model

import java.util.*

data class Device(
    val UUID: String,
    val address: String,
    val name: String,
    val data: List<String>,
    val createdAt: Date,
    val isConnected: Boolean
)