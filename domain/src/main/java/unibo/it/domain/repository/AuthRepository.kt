package unibo.it.domain.repository

interface AuthRepository {
    /**
     * Literally save the token ( i.e. in Preferences )
     */
    suspend fun saveToken(token: String, email: String)
}