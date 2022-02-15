package unibo.it.local.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import unibo.it.local.mapper.SettingMapper
import unibo.it.local.provider.DaoProvider
import unibo.it.repository.datasource.SettingDataSource
import unibo.it.repository.model.Setting

internal class SettingLocalDataSource(
    daoProvider: DaoProvider,
    private val settingMapper: SettingMapper,
) : SettingDataSource {
    private val settingDao = daoProvider.getSettingDao()

    override suspend fun insertSetting(setting: Setting) =
        settingDao.insertSetting(settingMapper.fromRepo(setting))

    override suspend fun insertSettings(settings: List<Setting>) =
        settingDao.insertSettings(settingMapper.fromRepo(settings))

    override fun loadSettings(): Flow<List<Setting>> =
        settingDao.loadSettings().map {
            settingMapper.toRepo(it)
        }

    override suspend fun updateSetting(setting: Setting) =
        settingDao.updateSetting(settingMapper.fromRepo(setting))

    override suspend fun deleteSetting(escCommand: String) =
        settingDao.deleteSetting(escCommand)
}