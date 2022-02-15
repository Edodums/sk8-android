package unibo.it.common.ble

import android.bluetooth.BluetoothManager
import android.content.Context
import android.util.Log
import com.juul.kable.Peripheral
import com.juul.kable.State
import com.juul.kable.characteristicOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.pow

private val readCharacteristic =
    characteristicOf(Constants.UUID_SERVICE, Constants.UUID_NOTIFY_READ_CHAR)
val writeCharacteristic =
    characteristicOf(Constants.UUID_SERVICE, Constants.UUID_WRITE_CHAR)
/*
peripheral.observe(readCharacteristic) {
    runCatching {
        peripheral.write(
            writeCharacteristic,
            Json.encodeToString(it).toByteArray(),
            WriteType.WithResponse
        )
    }
}.collect {
}*/

/*fun writeToEsp32(peripheral: Peripheral, data: String): () -> Unit = {
    CoroutineScope(Dispatchers.IO).launch {
        Log.i("TUTELA", "AMBIENTA")
        runCatching {
            Log.i("TUTELA", "AMBIENTA DENTRO")
            peripheral.write(
                writeCharacteristic,
                data.toByteArray(),
                WriteType.WithResponse
            )
        }
    }
}

fun observeEsp32(peripheral: Peripheral, callback: () -> Unit) = flow {
    peripheral.observe(readCharacteristic) {
        callback()
    }.collect {
        emit(String(it))
    }
}

suspend fun observeEsp32(peripheral: Peripheral) = flow {
    peripheral.observe(readCharacteristic).collect {
        emit(String(it))
    }
}*/

fun CoroutineScope.enableAutoReconnect(peripheral: Peripheral) {
    val connectionAttempt = AtomicInteger()
    peripheral.state
        .filter { it is State.Disconnected }
        .onEach {
            val timeMillis =
                backoff(base = 500L, multiplier = 2f, retry = connectionAttempt.getAndIncrement())
            Log.i("Connect", "Waiting $timeMillis ms to reconnect...")
            delay(timeMillis)
            peripheral.connect()
        }
        .launchIn(this)
}

private fun backoff(
    base: Long,
    multiplier: Float,
    retry: Int,
): Long = (base * multiplier.pow(retry - 1)).toLong()

fun getBluetoothManager(context: Context): BluetoothManager {
    return context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
}

fun ByteArray.toHexString(): String =
    joinToString(separator = " ", prefix = "0x") { String.format("%02X", it) }

private object Constants {
    const val UUID_SERVICE = "6E400001-B5A3-F393-E0A9-E50E24DCCA9E"
    const val UUID_WRITE_CHAR = "6E400002-B5A3-F393-E0A9-E50E24DCCA9E"
    const val UUID_NOTIFY_READ_CHAR = "6E400003-B5A3-F393-E0A9-E50E24DCCA9E"
}