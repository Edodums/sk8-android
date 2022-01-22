package unibo.it.domain.repository

import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    suspend fun isDeviceConnected(): Flow<Boolean>
}