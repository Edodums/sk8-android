package unibo.it.domain.repository

import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    fun isDeviceConnected(): Flow<Boolean>
}