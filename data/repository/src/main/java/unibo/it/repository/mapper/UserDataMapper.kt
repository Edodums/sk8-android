package unibo.it.repository.mapper

import unibo.it.domain.model.UserData as DomainUserData
import unibo.it.repository.model.UserData as RepoUserData

internal class UserDataMapper {
    fun toRepo(domainUserData: DomainUserData): RepoUserData =
        RepoUserData(
            token = domainUserData.token ?: "",
            email = domainUserData.email ?: ""
        )

    fun toDomain(repoUserData: RepoUserData): DomainUserData =
        DomainUserData(
            token = repoUserData.token,
            email = repoUserData.email
        )
}