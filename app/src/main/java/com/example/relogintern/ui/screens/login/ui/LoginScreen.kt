package com.example.relogintern.ui.screens.login.ui

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
import com.example.relogintern.ui.screens.auth.viewmodel.AuthViewModel
import com.example.relogintern.ui.screens.login.viewmodel.LoginViewModel
import com.example.relogintern.util.TextFieldType
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegistrationClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val loginScreenState by viewModel.loginScreenState.collectAsState()

    LaunchedEffect(loginScreenState) {
        if (loginScreenState.authData != null) {
            onLoginClick()
            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
        }
    }

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(id = R.string.login_title),
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
        Spacer(modifier = Modifier.height(6.dp))
        AuthLinkText(
            text = stringResource(R.string.have_not_account)
        ) {
            coroutineScope.launch {
                onRegistrationClick()
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        AuthButton(
            buttonText = stringResource(id = R.string.sign_in_button)
        ) {
            coroutineScope.launch {
                viewModel.login(email, password)
            }
        }
    }
}