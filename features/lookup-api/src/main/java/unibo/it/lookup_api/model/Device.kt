package unibo.it.lookup_api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Device(
    /*val serviceUuid: UUID,
    val characteristicUuid: UUID,
    val descriptorUuid: UUID,
    val bluetoothGattDescriptor: BluetoothGattDescriptor*/
    val UUID: String,
    val address: String,
    val name: String,
    val data: List<String>,
    val createdAt: Date,
    val isConnected: Boolean
) : Parcelable