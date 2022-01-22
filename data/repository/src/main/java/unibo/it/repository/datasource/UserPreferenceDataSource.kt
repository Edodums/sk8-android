package unibo.it.repository.datasource

import kotlinx.coroutines.flow.Flow
import unibo.it.repository.model.UserData

interface UserPreferenceDataSource {
    /**
     * Literally save the token ( i.e. in Preferences )
     */
    suspend fun saveToken(userData: UserData)

    /**
     * Get user data
     */
    suspend fun loadData(): Flow<UserData?>
}