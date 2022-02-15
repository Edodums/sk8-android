package unibo.it.settings.model

import kotlinx.serialization.Serializable

@Serializable
data class Setting(
    val escCommand: String,
    val escOption: String
)