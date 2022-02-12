package unibo.it.lookup_api.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import java.util.*

@Parcelize
@TypeParceler<Date, DateParceler>()
data class Device(
    /*val serviceUuid: UUID,
    val characteristicUuid: UUID,
    val descriptorUuid: UUID,
    val bluetoothGattDescriptor: BluetoothGattDescriptor*/
    val UUID: String,
    val address: String,
    val name: String,
    val data: List<String>,
    val isConnected: Boolean,
    val createdAt: Date,
) : Parcelable

object DateParceler : Parceler<Date> {
    override fun create(parcel: Parcel) = Date(parcel.readLong())
    override fun Date.write(parcel: Parcel, flags: Int) = parcel.writeLong(time)
}