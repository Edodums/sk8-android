package unibo.it.settings.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import unibo.it.common.ui.BasicLogo
import unibo.it.settings.R
import unibo.it.settings.presentation.ui.theme.lunacy_settings
import unibo.it.settings_api.presentation.DefaultSettingState
import unibo.it.settings_api.presentation.EscCommand
import unibo.it.settings_api.presentation.EscCommandOptions
import unibo.it.settings_api.presentation.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = getViewModel(),
    onSavingClick: () -> Unit
) {
    val defaults by remember(viewModel) { viewModel }.loadDefaults()
        .collectAsState(initial = DefaultSettingState.Loading)

    val actions = SettingActions(
        onSavingClick = onSavingClick
    )

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer,
        topBar = { BasicLogo() },
        content = {
            when (defaults) {
                DefaultSettingState.Loading -> {}
                is DefaultSettingState.Loaded -> SettingContent(defaultSettings = (defaults as DefaultSettingState.Loaded).settings, onSavingClick = actions.onSavingClick)
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SettingContent(
    viewModel: SettingsViewModel = getViewModel(),
    defaultSettings: HashMap<EscCommand, EscCommandOptions>,
    onSavingClick: () -> Unit
) {
    val commands: List<EscCommand> = viewModel.getEscCommand()
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        commands.forEach { command ->
            item {
                val expanded = remember { mutableStateOf(false) }
                val defaultOption = defaultSettings[command]?.label ?: ""
                val selectedOptionText = remember { mutableStateOf(defaultOption) }

                ExposedDropdownMenuBox(
                    expanded = expanded.value,
                    onExpandedChange = {
                        expanded.value = !expanded.value
                    }
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = selectedOptionText.value,
                        onValueChange = { /* empty */ },
                        label = { Text(text = command.label) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded.value
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = {
                            expanded.value = false
                        }
                    ) {
                        command.options.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedOptionText.value = selectionOption.label
                                    viewModel.commandValueChanged(
                                        command.commandName,
                                        selectionOption.option
                                    )
                                    expanded.value = false
                                }
                            ) {
                                Text(text = selectionOption.label)
                            }
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
            Button(
                onClick = {
                    viewModel.saveChanges()

                    val result = viewModel.sendChangesToESP()
                    Toast.makeText(
                        context,
                        result,
                        Toast.LENGTH_SHORT
                    ).show()

                    onSavingClick()
                },
                enabled = true,
                modifier = Modifier.width(300.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = lunacy_settings,
                    disabledContainerColor = MaterialTheme.colorScheme.background
                ),
                contentPadding = PaddingValues(12.dp),
                content = {
                    Text(
                        text = stringResource(id = R.string.save_changes),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.displayMedium,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )
                }
            )
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
        }
    }
}
