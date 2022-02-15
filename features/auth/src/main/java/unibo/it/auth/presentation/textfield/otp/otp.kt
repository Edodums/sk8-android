package unibo.it.auth.presentation.textfield.otp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import unibo.it.auth.R
import unibo.it.auth.presentation.ui.theme.outline_OTP_focused
import unibo.it.auth.presentation.ui.theme.outline_OTP_unfocused
import unibo.it.auth_api.presentation.AuthViewModel

@Composable
fun OTP() {
    OTPScreen()
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
private fun OTPScreen(
    viewModel: OTPViewModel = getViewModel(),
    authViewModel: AuthViewModel = getViewModel()
) {
    val scale = 0.8f
    val otpState by viewModel.otpStates.collectAsState(OTPStates.StillNot)
    val coroutinesScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.check_your_email),
            style = MaterialTheme.typography.displayMedium,
            color = White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Image(
            painter = painterResource(id = R.drawable.new_message),
            contentDescription = "Verification code has been sent",
            modifier = Modifier.scale(scale)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        OTPLine(viewModel)
        Spacer(modifier = Modifier.padding(20.dp))
        Button(
            onClick = {
                coroutinesScope.launch {
                    authViewModel.verify(viewModel.getOTP())
                }
            },
            enabled = otpState.isValid,
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.background
            ),
            contentPadding = PaddingValues(12.dp),
            content = {
                Text(text = "Verify")
            }
        )
    }

}

@ExperimentalCoroutinesApi
@Composable
private fun OTPLine(viewModel: OTPViewModel) {
    Row(
        Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val focusRequesters = List(Constant.OTP_CHARS) { FocusRequester() }

        List(Constant.OTP_CHARS) { index ->
            var nextFocusRequester: FocusRequester? = null

            if (index < Constant.OTP_CHARS - 1) {
                nextFocusRequester = focusRequesters[index + 1]
            }

            OTPChar(viewModel, index, focusRequesters[index], nextFocusRequester)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalCoroutinesApi
@Composable
private fun OTPChar(
    viewModel: OTPViewModel,
    index: Int,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester? = null
) {
    Column(
        Modifier
            .background(Transparent)
            .padding(all = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val otpState = remember { OTPState() }
        val sideLength = 50.dp
        val onlyOne = 1
        var keyboardAction = ImeAction.Next
        val bringIntoViewRequester = remember { BringIntoViewRequester() }
        val coroutineScope = rememberCoroutineScope()

        if (index == Constant.OTP_CHARS) {
            keyboardAction = ImeAction.Send
        }

        OutlinedTextField(
            value = otpState.text,
            onValueChange = {
                otpState.text = it

                if (it.length == onlyOne) {
                    nextFocusRequester?.requestFocus()
                }

                if (otpState.isValid) {
                    viewModel.addOTPChar(index, otpState.text)
                }
            },
            modifier = Modifier
                .width(sideLength)
                .height(sideLength)
                .focusRequester(focusRequester)
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusChanged {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            // This sends a request to all parents that asks them to scroll so
                            // that this item is brought into view.
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                }
                .focusTarget(),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = White,
                backgroundColor = Transparent,
                focusedBorderColor = outline_OTP_focused,
                unfocusedBorderColor = outline_OTP_unfocused,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = keyboardAction
            )
        )
    }
}

object Constant {
    const val OTP_CHARS = 6
}