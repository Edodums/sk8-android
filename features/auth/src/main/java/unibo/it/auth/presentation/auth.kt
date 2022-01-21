package unibo.it.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import unibo.it.auth.R
import unibo.it.auth.presentation.textfield.EmailState
import unibo.it.auth.presentation.textfield.PasswordState
import unibo.it.auth.presentation.textfield.TextFieldState
import unibo.it.auth.presentation.textfield.otp.OTP
import unibo.it.auth_api.presentation.AuthState
import unibo.it.auth_api.presentation.AuthViewModel
import unibo.it.common.ui.AuthLogo

@FlowPreview
@ExperimentalCoroutinesApi
@Composable
fun AuthScreen(
    viewModel: AuthViewModel = getViewModel(),
    onVerified: () -> Unit
) {
    val authState by remember(viewModel) { viewModel }.loadAuthState()
        .collectAsState(AuthState.Sign)
    val actions = AuthActions(
        onVerified = onVerified
    )

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.onPrimary,
        topBar = { AuthLogo() },
        content = {
            when (authState) {
                AuthState.OTP -> OTP()
                AuthState.Sign -> SignScreen(viewModel)
                AuthState.Verified ->
                    actions.onVerified()
            }
        }
    )
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
private fun SignScreen(viewModel: AuthViewModel) {
    val columnHorizontalPadding = 48.dp

    Column(
        modifier = Modifier
            .padding(horizontal = columnHorizontalPadding)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            fontSize = 48.sp,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )

        SignForm(viewModel)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun SignForm(viewModel: AuthViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val verticalPadding = 48.dp
    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
        unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
        leadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
        trailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
        disabledTrailingIconColor = MaterialTheme.colorScheme.primaryContainer,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = verticalPadding),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            val focusRequester = remember { FocusRequester() }
            val emailState = remember { EmailState() }
            val passwordState = remember { PasswordState() }

            Email(
                emailState = emailState,
                onImeAction = { focusRequester.requestFocus() },
                textFieldColors = textFieldColors
            )

            Password(
                passwordState = passwordState,
                textFieldColors = textFieldColors,
                modifier = Modifier.focusRequester(focusRequester),
                onImeAction = {
                    coroutineScope.launch {
                        viewModel.signIn(emailState.text, passwordState.text)
                    }
                }
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.signIn(emailState.text, passwordState.text)
                    }
                },
                enabled = emailState.isValid && passwordState.isValid,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colorScheme.onBackground,
                    disabledBackgroundColor = MaterialTheme.colorScheme.background
                ),
                contentPadding = PaddingValues(12.dp),
                content = {
                    Text(text = stringResource(id = R.string.continue_))
                }
            )
        }
    )
}

@Composable
private fun Email(
    emailState: TextFieldState = rememberSaveable { EmailState() },
    textFieldColors: TextFieldColors,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {}
) {
    OutlinedTextField(
        value = emailState.text,
        modifier = Modifier.fillMaxWidth(),
        isError = emailState.showErrors(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Person"
            )
        },
        colors = textFieldColors,
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = stringResource(id = R.string.email),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
        onValueChange = {
            emailState.text = it
        }
    )
}

@Composable
private fun Password(
    passwordState: TextFieldState,
    textFieldColors: TextFieldColors,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {}
) {
    val showPassword = rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = passwordState.text,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                passwordState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    passwordState.enableShowErrors()
                }
            },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Lock"
            )
        },
        trailingIcon = {
            if (showPassword.value) {
                IconButton(onClick = { showPassword.value = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = stringResource(id = R.string.hide_password)
                    )
                }
            } else {
                IconButton(onClick = { showPassword.value = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = stringResource(id = R.string.show_password)
                    )
                }
            }
        },
        colors = textFieldColors,
        label = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = stringResource(id = R.string.password),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
        onValueChange = {
            passwordState.text = it
            passwordState.enableShowErrors()
        },
        visualTransformation = if (showPassword.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        )
    )
}
