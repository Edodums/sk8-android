package unibo.it.settings.mapper

import unibo.it.domain.model.Setting
import unibo.it.settings_api.presentation.EscCommand
import unibo.it.settings_api.presentation.EscCommandOptions
import unibo.it.settings_api.presentation.getByCommandName

class SettingMapper {
    fun toView(settings: List<Setting>): HashMap<EscCommand, EscCommandOptions>  {
        val map = hashMapOf<EscCommand, EscCommandOptions>()

        settings.forEach { setting ->
            val escCommand: EscCommand = getByCommandName(setting.escCommand)
            val escOption: EscCommandOptions =
                escCommand.options.first { options -> setting.escOption == options.option }

            map[escCommand] = escOption
        }

        return map
    }

}