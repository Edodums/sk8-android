package unibo.it.settings_api.presentation

sealed class DefaultSettingState {
    object Loading : DefaultSettingState()
    data class Loaded(var settings: HashMap<EscCommand, EscCommandOptions>): DefaultSettingState()
}