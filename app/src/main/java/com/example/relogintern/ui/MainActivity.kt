package com.example.relogintern.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.relogintern.ui.screens.auth.viewmodel.AuthViewModel
import com.example.relogintern.ui.screens.login.ui.LoginScreen
import com.example.relogintern.ui.screens.main.ui.MainScreen
import com.example.relogintern.ui.screens.registration.ui.RegistrationScreen
import com.example.relogintern.ui.theme.RelogInternTheme
import com.example.relogintern.util.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RelogInternTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val isLogged by authViewModel.userTokenState.collectAsState()

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = if (isLogged.isLogged) {
                            Screens.MainScreen.name
                        } else {
                            Screens.LoginScreen.name
                        }
                    ) {
                        composable(Screens.LoginScreen.name) {
                            LoginScreen(
                                onLoginClick = {
                                    navController.navigate(Screens.MainScreen.name) {
                                        popUpTo(Screens.LoginScreen.name) {
                                            inclusive = true
                                        }
                                    }
                                },
                                onRegistrationClick = {
                                    navController.navigate(Screens.RegistrationScreen.name){
                                        popUpTo(Screens.LoginScreen.name) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                        composable(Screens.RegistrationScreen.name) {
                            RegistrationScreen(
                                onRegistrationClick = {
                                    navController.navigate(Screens.MainScreen.name) {
                                        popUpTo(Screens.RegistrationScreen.name) {
                                            inclusive = true
                                        }
                                    }
                                },
                                onLoginClick = {
                                    navController.navigate(Screens.LoginScreen.name) {
                                        popUpTo(Screens.RegistrationScreen.name) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                        composable(Screens.MainScreen.name) {
                            MainScreen(
                                onSignOutClick = {
                                    navController.navigate(Screens.LoginScreen.name) {
                                        popUpTo(Screens.MainScreen.name) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}