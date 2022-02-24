package unibo.it.controls.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ControlSerializer::class)
data class Control(
    val key: String = "speed",
    val value: SpeedCommand = SpeedCommand.Stop
) {
    sealed class SpeedCommand {
        object Forward : SpeedCommand()
        object Stop : SpeedCommand()
        object Reverse : SpeedCommand()
    }
}

object ControlSerializer : KSerializer<Control> {
    override fun serialize(encoder: Encoder, value: Control) {
        val convertedValue = convertToStringValue(value.value)
        encoder.encodeString("""{"${value.key}":"$convertedValue"}""")
    }

    override fun deserialize(decoder: Decoder): Control {
        val decodedString = decoder.decodeString()
        val speedKey: String = decodedString.substringBefore(":")
        val speedCommandValue: Control.SpeedCommand =
            convertToSpeedCommand(decodedString.substringAfter(":"))
        return Control(speedKey, speedCommandValue)
    }

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("String", PrimitiveKind.STRING)

    private fun convertToStringValue(speedCommand: Control.SpeedCommand): String {
        return when (speedCommand) {
            Control.SpeedCommand.Forward -> "F" // F -> Forward
            Control.SpeedCommand.Stop -> "B" // B -> Brake
            Control.SpeedCommand.Reverse -> "R" // R -> Reverse Forward
        }
    }

    private fun convertToSpeedCommand(speedCommand: String): Control.SpeedCommand {
        return when (speedCommand) {
            "F" -> Control.SpeedCommand.Forward
            "B" -> Control.SpeedCommand.Stop
            "R" -> Control.SpeedCommand.Reverse
            else -> Control.SpeedCommand.Stop
        }
    }
}