package com.example.zitrocrm.screens

import android.app.Activity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zitrocrm.R
import com.example.zitrocrm.screens.login.LoginViewModel
import com.example.zitrocrm.ui.theme.reds
import com.example.zitrocrm.repository.SharedPrefence

//SIZE AND PADDING ICONS AND TEXT DRAWER
val size = 30.dp
val padding = 5.dp

@Composable
fun drawercontent(navController: NavController, loginViewModel: LoginViewModel){
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(state = ScrollState(1))
            .background(Color.Black)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .background(color = reds)) {
                Cardusersettings(navController, loginViewModel)
            }
            drawercontentpage(navController)
            drawercontentpage2(navController)
            drawercontentpage3(navController)
            drawercontentpage4(navController)
            drawercontentpage5(navController)
            drawercontentpage6(navController)
            drawercontentpage7(navController)
            drawercontentpage8(navController)
            drawercontentpage9(navController)
            drawercontentpage10(navController)

        }
    }
}

@Composable
fun Cardusersettings(navController: NavController, loginViewModel: LoginViewModel){
    val datastore = SharedPrefence(LocalContext.current)

    val username = ""+datastore.getName().toString()
    val userlastname =""+datastore.getLastName().toString()
    val useremail = ""+datastore.getEmail().toString()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
        Image(
            painter = painterResource(R.drawable.crm_logo),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .size(40.dp)
        )
        Text(
            text = "${username} ${userlastname}",modifier = Modifier.padding(10.dp,0.dp))

        Text(
            text = "${useremail}",modifier = Modifier.padding(10.dp,0.dp))
    }
}

@Composable
fun drawercontentpage(navController: NavController){
    Box(modifier = Modifier
        .clickable { }
        .padding(padding)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.nav_cliente_icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .size(size)
            )
            Text(
                text = "Cliente",modifier = Modifier.padding(10.dp,0.dp))}
    }
}

@Composable
fun drawercontentpage2(navController: NavController){
    Box(modifier = Modifier
        .clickable {navController.navigate("salas_screen")}
        .padding(padding)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.nav_sala_icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .size(size)
            )
            Text(
                text = "Salas",modifier = Modifier.padding(10.dp,0.dp))}
    }
}

@Composable
fun drawercontentpage3(navController: NavController){
    Box(modifier = Modifier
        .clickable { }
        .padding(padding)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.nav_maquinas_icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .size(size)
            )
            Text(
                text = "Maquinas",modifier = Modifier.padding(10.dp,0.dp))}
    }
}

@Composable
fun drawercontentpage4(navController: NavController){
    Box(modifier = Modifier
        .clickable { }
        .padding(padding)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.nav_cuentas_icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .size(size)
            )
            Text(
                text = "Cuentas",modifier = Modifier.padding(10.dp,0.dp))}
    }
}

@Composable
fun drawercontentpage5(navController: NavController){
    Box(modifier = Modifier
        .clickable { }
        .padding(padding)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.nav_negociaciones_icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .size(size)
            )
            Text(
                text = "Negociaciones",modifier = Modifier.padding(10.dp,0.dp))}
    }
}

@Composable
fun drawercontentpage6(navController: NavController){
    Box(modifier = Modifier
        .clickable { }
        .padding(padding)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.nav_contratos_icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .size(size)
            )
            Text(
                text = "Contratos",modifier = Modifier.padding(10.dp,0.dp))}
    }
}

@Composable
fun drawercontentpage7(navController: NavController){
    Box(modifier = Modifier
        .clickable { }
        .padding(padding)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.nav_facturacion_icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .size(size)
                )
            Text(
                text = "Facturas",modifier = Modifier.padding(10.dp,0.dp))}
    }
}

@Composable
fun drawercontentpage8(navController: NavController){
    Box(modifier = Modifier
        .clickable { }
        .padding(padding)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.nav_venta_refaccion),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .size(size)
            )
            Text(
                text = "Venta de Refacciones",modifier = Modifier.padding(10.dp,0.dp))}
    }
}

@Composable
fun drawercontentpage9(navController: NavController){
    Box(modifier = Modifier
        .clickable { }
        .padding(padding)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.nav_ajustes_perfil_icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(5.dp)
                    .size(size)
            )
            Text(
                text = "Ajustes Perfil",modifier = Modifier.padding(10.dp,0.dp))}
    }
}

@Composable
fun drawercontentpage10(navController: NavController){
    val datastore = SharedPrefence(LocalContext.current)
    val activity = (LocalContext.current as? Activity)
    Box(modifier = Modifier
        .clickable {
            activity?.finish()
            datastore.clearSharedPreference()
        }
        .padding(padding)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.ExitToApp,
                "contentDescription",
                tint = Color.White,
                modifier = Modifier
                    .padding(10.dp)
                    .size(22.dp)
            )
            Text(
                text = "Salir",modifier = Modifier.padding(10.dp,0.dp))}
    }
}
