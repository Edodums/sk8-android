package unibo.it.repository.mapper

import unibo.it.domain.model.UserData as DomainUserData
import unibo.it.repository.model.UserData as RepoUserData

internal class UserDataMapper {
    fun toRepo(domainUserData: DomainUserData): RepoUserData =
        RepoUserData(
            token = domainUserData.token,
            email = domainUserData.email,
            deviceConnected = domainUserData.deviceConnected
        )

    fun toDomain(viewUserData: RepoUserData): DomainUserData =
        DomainUserData(
            token = viewUserData.token,
            email = viewUserData.email,
            deviceConnected = viewUserData.deviceConnected
        )
}