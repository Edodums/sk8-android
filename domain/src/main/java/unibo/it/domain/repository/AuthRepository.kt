package unibo.it.domain.repository

import kotlinx.coroutines.flow.Flow
import unibo.it.domain.model.UserData

interface AuthRepository {
    /**
     * Literally save the token ( i.e. in Preferences )
     */
    suspend fun saveToken(userData: UserData)

    /**
     * Get user data
     */
    suspend fun loadData(): Flow<UserData>
}