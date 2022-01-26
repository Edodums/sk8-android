package unibo.it.datastore.mapper

import unibo.it.datastore.model.User as DataStoreUser
import unibo.it.repository.model.UserData as RepoUser

internal class UserMapper {
    fun toDataStore(repoUser: RepoUser): DataStoreUser =
        DataStoreUser(
            token = repoUser.token,
            email = repoUser.email,
            deviceConnected = repoUser.deviceConnected
        )

    fun toRepo(dataStore: DataStoreUser): RepoUser =
        RepoUser(
            token = dataStore.token ?: "",
            email = dataStore.email ?: "",
            deviceConnected = dataStore.deviceConnected
        )
}