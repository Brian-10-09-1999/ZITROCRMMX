package com.example.zitrocrm.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.Filter.FilterViewModel
import com.example.zitrocrm.repository.Models.models_nueva_visita.ArrayFoto
import com.example.zitrocrm.screens.*
import com.example.zitrocrm.screens.homepage.components.FilterScreen
import com.example.zitrocrm.screens.login.LoginViewModel
import com.example.zitrocrm.screens.login.components.animateValues
import com.example.zitrocrm.screens.login.components.progressBarString
import com.example.zitrocrm.screens.salas.JuegosNuevosBingo.JuegosNuevosBingoScreens
import com.example.zitrocrm.screens.salas.JuegosNuevosBingo.JuegosNuevosViewModel
import com.example.zitrocrm.screens.salas.PromotorNewScreenn
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.utils.AlertState
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreen() {
    val loginViewModel: LoginViewModel = viewModel()
    val filterViewModel: FilterViewModel = viewModel()
    val viewModelPromotorNuevaVisita: PromotorNuevaVisitaViewModel = viewModel()
    val JuegosNuevosViewModel: JuegosNuevosViewModel = viewModel()
    val navController = rememberNavController()
    val loadingProgressBar = 0
    val imageError = loginViewModel.imageErrorAuth.value
    NavHost(
        navController = navController,
        startDestination = Destination.getStartDestination()
    ) {

        composable(route = Destination.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = Destination.LoginScreen.route) {
            if (loginViewModel.isSuccessLoading.value) {
                LaunchedEffect(key1 = Unit) {
                    /*navController.navigate(route = Destination.HomeScreen.route) {
                        popUpTo(route = Destination.LoginScreen.route) {
                            inclusive = true
                        }
                    }*/
                    navController.navigate(route = Destination.SalasScreen.route) {
                        popUpTo(route = Destination.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                LoginScreen(
                    onclickLogin = (loginViewModel::login),
                    imageError = imageError,
                    loginViewModel = loginViewModel
                )
            }
        }
        composable(route = Destination.HomeScreen.route) {
            if (filterViewModel.NavFilter.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(route = Destination.FilterScreen.route)
                    filterViewModel.NavFilter.value = false
                }
            }
            HomeScreen(
                navController,
                filterViewModel
            )
        }
        composable(route = Destination.FilterScreen.route) {
            FilterScreen(navController, filterViewModel)
        }
        composable(route = Destination.SalasScreen.route) {
            if (filterViewModel.NavFilter.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(route = Destination.FilterScreen.route)
                    filterViewModel.NavFilter.value = false
                }
            }
            SalasScreen(
                navController,
                filterViewModel,
                viewModelPromotorNuevaVisita
            )
        }
        composable(route = Destination.PromotorNewScreen.route) {
            if (filterViewModel.NavFilter.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(route = Destination.FilterScreen.route)
                }
                filterViewModel.NavFilter.value = false
            }
            PromotorNewScreenn(
                navController,
                viewModelPromotorNuevaVisita,
                filterViewModel,
            )
        }
        composable(route = Destination.PromotorReportScreen.route) {
            PromotorReportScreen(navController)
        }
        composable(route = Destination.JuegosNuevosBingoScreens.route) {
            JuegosNuevosBingoScreens(viewModel = JuegosNuevosViewModel)
        }
    }
}

@Composable
fun Alert_State(
    AlertState: AlertState
) {
    val coloricon by animateColorAsState(
        when (AlertState.alert_type.value) {
            "LOADING" -> colorResource(R.color.LOADING)
            "INFO" -> colorResource(R.color.INFO)
            "SUCCESS" -> colorResource(R.color.SUCCESS)
            "WARNING" -> colorResource(R.color.WARNING)
            "ERROR" -> colorResource(R.color.ERROR)
            else -> {
                colorResource(R.color.reds)
            }
        }
    )
    val icons = when (AlertState.alert_type.value) {
        "LOADING" -> Icons.Filled.Downloading
        "INFO" -> Icons.Filled.Info
        "SUCCESS" -> Icons.Filled.DoneOutline
        "WARNING" -> Icons.Filled.Warning
        "ERROR" -> Icons.Filled.Error
        else -> {
            Icons.Filled.Check
        }
    }
    val alert = AlertState.alert_type.value
    if (alert.isNotBlank()) {
        if (alert == "LOADING") {
            val animationSpec = infiniteRepeatable<Float>(
                animation = tween(
                    durationMillis = 2500,
                    easing = LinearEasing,
                )
            )
            val xRotation by animateValues(
                values = listOf(0f, 180f, 180f, 0f, 0f),
                animationSpec = animationSpec
            )
            val yRotation by animateValues(
                values = listOf(0f, 0f, 180f, 180f, 0f),
                animationSpec = animationSpec
            )
            AlertDialog(
                onDismissRequest = {
                },
                confirmButton = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.crm_logo), contentDescription = "",
                            modifier = Modifier
                                .graphicsLayer {
                                    rotationX = xRotation
                                    rotationY = yRotation
                                }
                                .size(100.dp),
                        )
                        Spacer(modifier = Modifier.size(15.dp))
                        Text(
                            text = "Cargando..",
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = progressBarString.value,
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.size(25.dp))
                    }
                }, backgroundColor = Color.Transparent
            )
        } else {
            AlertDialog(
                onDismissRequest = {},
                title = null,
                buttons = {
                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                            .background(color = colorResource(R.color.reds))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        ) {
                            Icon(
                                icons, null, modifier = Modifier
                                    .align(CenterVertically)
                                    .padding(horizontal = 10.dp)
                                    .clickable {
                                        AlertState.alert_type.value = ""
                                    }, tint = coloricon
                            )
                            Text(
                                text = AlertState.tittle_alert.value,
                                modifier = Modifier.align(CenterVertically)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = AlertState.content_alert.value,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                },
                shape = RoundedCornerShape(18.dp)
            )
        }
    }
}