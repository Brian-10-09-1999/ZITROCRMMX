package com.example.zitrocrm.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zitrocrm.network.models_dto.Filter.FilterViewModel
import com.example.zitrocrm.screens.*
import com.example.zitrocrm.screens.homepage.components.FilterScreen
import com.example.zitrocrm.screens.login.LoginViewModel
import com.example.zitrocrm.screens.salas.JuegosNuevosBingo.JuegosNuevosBingoScreens
import com.example.zitrocrm.screens.salas.JuegosNuevosBingo.JuegosNuevosViewModel
import com.example.zitrocrm.screens.salas.PromotorNewScreenn
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.components.DisplayAlert

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreen(){
    val loginViewModel: LoginViewModel = viewModel()
    val filterViewModel: FilterViewModel = viewModel()
    val viewModelPromotorNuevaVisita: PromotorNuevaVisitaViewModel = viewModel()
    val JuegosNuevosViewModel : JuegosNuevosViewModel = viewModel()
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
                    navController.navigate(route = Destination.SalasScreen.route){
                        popUpTo(route = Destination.LoginScreen.route){
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
        composable(route = Destination.HomeScreen.route){
            if(filterViewModel.NavFilter.value){
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
        composable(route = Destination.FilterScreen.route){
            FilterScreen(navController,filterViewModel)
        }
        composable(route = Destination.SalasScreen.route){
            if(filterViewModel.NavFilter.value){
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
        composable(route = Destination.PromotorNewScreen.route){
            if(filterViewModel.NavFilter.value){
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(route = Destination.FilterScreen.route)
                }
                filterViewModel.NavFilter.value = false
            }
            PromotorNewScreenn(
                navController,
                viewModelPromotorNuevaVisita,
                filterViewModel
            )
        }
        composable(route = Destination.PromotorReportScreen.route){
            PromotorReportScreen(navController)
        }
        composable(route = Destination.Dialog.route){
            DisplayAlert(viewModel = viewModelPromotorNuevaVisita, viewModel2 = filterViewModel, navController = navController)
        }
        composable(route = Destination.JuegosNuevosBingoScreens.route){
            JuegosNuevosBingoScreens(viewModel = JuegosNuevosViewModel)
        }
    }
    DisplayAlert(viewModel = viewModelPromotorNuevaVisita, viewModel2 = filterViewModel, navController = navController)
}