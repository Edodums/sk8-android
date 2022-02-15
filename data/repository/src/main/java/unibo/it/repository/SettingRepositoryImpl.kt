package unibo.it.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import unibo.it.domain.model.Setting
import unibo.it.domain.repository.SettingRepository
import unibo.it.repository.datasource.SettingDataSource
import unibo.it.repository.mapper.SettingMapper

internal class SettingRepositoryImpl(
    private val settingDataSource: SettingDataSource,
    private val settingMapper: SettingMapper
) : SettingRepository {
    override suspend fun saveChanges(changes: List<Setting>) =
        settingDataSource.insertSettings(changes.map { settingMapper.toRepo(it) })

    override suspend fun loadDefaults(): Flow<List<Setting>> =
        settingDataSource.loadSettings().map { settingMapper.toDomain(it) }

}