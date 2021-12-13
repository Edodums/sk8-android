package unibo.it.sk8.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import unibo.it.sk8.R
import unibo.it.sk8.ui.theme.lunacy_auth_background

@ExperimentalCoroutinesApi
@Composable
fun Auth(
    viewModel: AuthViewModel
) {

    Scaffold(
        backgroundColor = lunacy_auth_background,
        topBar = { WhiteLogo() },
        content = {
            AuthContent()
        }
    )
}

@Composable
fun AuthContent() {
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

        Form()
    }
}

@Composable
fun WhiteLogo() {
    val scale = 3f
    val padding = 64.dp

    Image(
        painter = painterResource(id = R.drawable.logo_auth_white),
        contentDescription = "Sk8 Logo White",
        modifier = Modifier
            .padding(all = padding)
            .scale(scale)
    )
}

@Composable
fun Form() {
    val verticalPadding = 48.dp
    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
        unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
        focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
        unfocusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
    )

    var email = rememberSaveable { mutableStateOf("") }
    var password = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = verticalPadding),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            OutlinedTextField(
                value = email.value,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
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
                    Text(text = "Email")
                },
                onValueChange = {
                    email.value = it
                }
            )

            OutlinedTextField(
                value = password.value,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Send
                ),
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "Lock")
                },
                colors = textFieldColors,
                label = {
                    Text(text = "Password")
                },
                onValueChange = {
                    password.value = it
                }
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = onclickAuth(email.value, password.value),
                enabled = buttonIsEnabled(email.value, password.value),
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.onPrimary),
                content = {
                    Text(text = "Continue")
                }
            )
        }
    )
}

private fun buttonIsEnabled(email: String, password: String): Boolean {
    return email.isNotEmpty() && password.isNotEmpty()
}

fun onclickAuth(email: String, password: String): () -> Unit {

    return {}
}
