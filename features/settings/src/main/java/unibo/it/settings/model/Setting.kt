package unibo.it.settings.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = SettingSerializer::class)
data class Setting(
    val escCommand: String,
    val escOption: String
)


object SettingSerializer : KSerializer<Setting> {
    override fun serialize(encoder: Encoder, value: Setting) {
        encoder.encodeString("""{"${value.escCommand}":"${value.escOption}"}""")
    }

    override fun deserialize(decoder: Decoder): Setting {
        val decoder = decoder.decodeString()
        val escCommand: String = decoder.substringBefore(":")
        val escOption: String = decoder.substringAfter(":")
        return Setting(escCommand, escOption)
    }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("String", PrimitiveKind.STRING)
}