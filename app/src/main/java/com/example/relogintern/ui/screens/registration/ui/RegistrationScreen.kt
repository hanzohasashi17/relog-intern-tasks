package com.example.relogintern.ui.screens.registration.ui

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.relogintern.R
import com.example.relogintern.ui.components.AuthButton
import com.example.relogintern.ui.components.AuthLinkText
import com.example.relogintern.ui.components.AuthTextField
import com.example.relogintern.ui.screens.registration.viewmodel.RegistrationViewModel
import com.example.relogintern.util.TextFieldType
import kotlinx.coroutines.launch

@SuppressLint("ShowToast")
@Composable
fun RegistrationScreen(
    onRegistrationClick: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val registrationScreenState by viewModel.registrationScreenState.collectAsState()

    LaunchedEffect(registrationScreenState.authData) {
        if (registrationScreenState.authData != null) {
            onRegistrationClick()
            Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
        }
    }

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordRepeat by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(id = R.string.registration_title),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(48.dp))
        AuthTextField(
            value = email,
            label = stringResource(id = R.string.email),
            placeholder = stringResource(id = R.string.email_placeholder),
            textFieldType = TextFieldType.EmailType
        ) {
            email = it
        }
        Spacer(modifier = Modifier.height(12.dp))
        AuthTextField(
            value = password,
            label = stringResource(id = R.string.password),
            placeholder = stringResource(id = R.string.password_placeholder),
            textFieldType = TextFieldType.PasswordType,
            isPasswordField = true
        ) {
            password = it
        }
        Spacer(modifier = Modifier.height(12.dp))
        AuthTextField(
            value = passwordRepeat,
            label = stringResource(id = R.string.password_repeat),
            placeholder = stringResource(id = R.string.password_placeholder),
            textFieldType = TextFieldType.PasswordType,
            isPasswordField = true
        ) {
            passwordRepeat = it
        }
        Spacer(modifier = Modifier.height(6.dp))
        AuthLinkText(
            text = stringResource(R.string.have_account)
        ) {
            coroutineScope.launch {
                onLoginClick()
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        AuthButton(
            buttonText = stringResource(id = R.string.sign_up_button)
        ) {
            coroutineScope.launch {
                if (password == passwordRepeat) {
                    viewModel.registration(email, password)
                }
            }
        }
    }
}