package com.mokelab.lesson.sakammotonew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mokelab.lesson.sakammotonew.navigation.Routes
import com.mokelab.lesson.sakammotonew.screen.AppBlockScreen
import com.mokelab.lesson.sakammotonew.screen.CounterScreen
import com.mokelab.lesson.sakammotonew.screen.HomeScreen
import com.mokelab.lesson.sakammotonew.screen.LoginScreen
import com.mokelab.lesson.sakammotonew.screen.TimerScreen
import com.mokelab.lesson.sakammotonew.viewmodel.AppBlockViewModel
import com.mokelab.lesson.sakammotonew.viewmodel.CounterViewModel
import com.mokelab.lesson.sakammotonew.viewmodel.LoginViewModel
import com.mokelab.lesson.sakammotonew.viewmodel.TimerViewModel
import com.mokelab.lesson.sakammotonew.ui.theme.SakammotonewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SakammotonewTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.LOGIN,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.LOGIN) {
                            val viewModel: LoginViewModel = viewModel()
                            LoginScreen(
                                viewModel = viewModel,
                                onLoginSuccess = {
                                    navController.navigate(Routes.HOME) {
                                        popUpTo(Routes.LOGIN) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(Routes.HOME) {
                            HomeScreen(
                                onGoCounter = { navController.navigate(Routes.COUNTER) },
                                onGoTimer = { navController.navigate(Routes.TIMER) },
                                onGoAppBlock = { navController.navigate(Routes.APP_BLOCK) },
                                onLogout = {
                                    navController.navigate(Routes.LOGIN) {
                                        popUpTo(Routes.HOME) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(Routes.APP_BLOCK) {
                            val viewModel: AppBlockViewModel = viewModel()
                            AppBlockScreen(
                                viewModel = viewModel,
                                onBack = { navController.popBackStack() }
                            )
                        }
                        composable(Routes.COUNTER) {
                            val viewModel: CounterViewModel = viewModel()
                            CounterScreen(
                                viewModel = viewModel,
                                onGoTimer = { navController.navigate(Routes.TIMER) },
                                onBack = { navController.popBackStack() }
                            )
                        }
                        composable(Routes.TIMER) {
                            val viewModel: TimerViewModel = viewModel()
                            TimerScreen(
                                viewModel = viewModel,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
