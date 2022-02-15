package unibo.it.repository.mapper

import unibo.it.domain.model.Setting as DomainSetting
import unibo.it.repository.model.Setting as RepoSetting

class SettingMapper {
    fun toRepo(domainSetting: DomainSetting): RepoSetting =
        RepoSetting(
            escCommand = domainSetting.escCommand,
            escOption = domainSetting.escOption
        )

    fun toRepo(domainSettingList: List<DomainSetting>): List<RepoSetting> =
        domainSettingList.map { toRepo(it) }

    fun toDomain(repoSetting: RepoSetting): DomainSetting =
        DomainSetting(
            escCommand = repoSetting.escCommand,
            escOption = repoSetting.escOption
        )

    fun toDomain(repoSettingList: List<RepoSetting>): List<DomainSetting> =
        repoSettingList.map { toDomain(it) }
}