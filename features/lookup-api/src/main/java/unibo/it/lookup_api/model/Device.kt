package unibo.it.lookup_api.model

import android.bluetooth.BluetoothGattDescriptor
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Device(
    val serviceUuid: UUID,
    val characteristicUuid: UUID,
    val descriptorUuid: UUID,
    val bluetoothGattDescriptor: BluetoothGattDescriptor
) : Parcelable