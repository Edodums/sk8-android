package unibo.it.repository.datasource

interface UserPreferenceDataSource {
    /**
     * Literally save the token ( i.e. in Preferences )
     */
    suspend fun saveToken(token: String, email: String)
}