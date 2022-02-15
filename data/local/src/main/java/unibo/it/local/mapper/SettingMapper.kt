package unibo.it.local.mapper

import unibo.it.local.model.Setting as LocalSetting
import unibo.it.repository.model.Setting as RepoSetting

internal class SettingMapper {
    fun fromRepo(repoSetting: RepoSetting): LocalSetting =
        LocalSetting(
            escCommand = repoSetting.escCommand,
            escOption = repoSetting.escOption
        )

    fun fromRepo(repoSettingList: List<RepoSetting>): List<LocalSetting> =
        repoSettingList.map { fromRepo(it) }


    fun toRepo(localSetting: LocalSetting): RepoSetting =
        RepoSetting(
            escCommand = localSetting.escCommand,
            escOption = localSetting.escOption
        )


    fun toRepo(localSettingList: List<LocalSetting>): List<RepoSetting> =
        localSettingList.map { toRepo(it) }
}