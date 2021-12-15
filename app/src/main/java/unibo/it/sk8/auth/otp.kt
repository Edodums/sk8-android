package unibo.it.sk8.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import unibo.it.sk8.R

import unibo.it.sk8.ui.theme.outline_OTP_focused
import unibo.it.sk8.ui.theme.outline_OTP_unfocused
import unibo.it.sk8.utils.Constant


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun OTPScreen(viewModel: AuthViewModel) {
    val scale = 1f
    val otpState by viewModel.otpStates.collectAsState()

    /*val isOTPValid = otpStates.value.states.size == Constant.OTP_CHARS*/
    Column() {
        Text(text = stringResource(id = R.string.check_your_email))
        Image(
            painter = painterResource(id = R.drawable.new_message),
            contentDescription = "Verification code has been sent",
            modifier = Modifier.scale(scale)
        )
        OTPLine(viewModel)
        Button(
            onClick = {
                viewModel.verify(viewModel.getOTP())
            },
            enabled = otpState.isValid,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colorScheme.onBackground,
                disabledBackgroundColor = MaterialTheme.colorScheme.background
            ),
            contentPadding = PaddingValues(12.dp),
            content = {
                androidx.compose.material3.Text(text = "Verify")
            }
        )
    }

}

@ExperimentalCoroutinesApi
@Composable
fun OTPLine(viewModel: AuthViewModel) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        for (index in 1..Constant.OTP_CHARS) {
            OTPChar(viewModel, index)
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun OTPChar(viewModel: AuthViewModel, index: Int) {
    Column(
        Modifier
            .background(Transparent)
            .padding(all = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val focusRequester = remember { FocusRequester() }
        val otpState = remember { OTPState() }

        OutlinedTextField(
            value = otpState.text,
            onValueChange = {
                otpState.text = it

                if (otpState.isValid) {
                    viewModel.addOTPChar(index, it)
                }
            },
            modifier = Modifier
                .width(50.dp)
                .focusRequester(focusRequester),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = White,
                backgroundColor = Transparent,
                focusedBorderColor = outline_OTP_focused,
                unfocusedBorderColor = outline_OTP_unfocused,
            )
        )
    }
}
