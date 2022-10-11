package com.example.zitrocrm.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.zitrocrm.R
import com.example.zitrocrm.ui.theme.blackdark
import com.example.zitrocrm.ui.theme.reds

@Composable
fun PromotorReportScreen (navController: NavController){
    Scaffold(
        topBar = {
            CustomTopAppPromotor(navController)
        }
    ) {
        ContentPromotorR (navController)
    }

}
@Composable
private fun CustomTopAppPromotor(navController: NavController) {
    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.height(70.dp),
        title = {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(R.drawable.back_button),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .align(Alignment.CenterStart)
                        .size(29.dp),
                )
                Image(
                    painter = painterResource(R.drawable.crm_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(5.dp)
                )
            }
        },
        backgroundColor = reds,
    )
}

@Composable
private fun ContentPromotorR(navController: NavController){
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(state = ScrollState(1))) {
        Column(modifier = Modifier.fillMaxSize()) {
            titleReportVisita(navController)
            buttonsReporteVisita(navController)
        }
    }
}


@Composable
private fun titleReportVisita(navController: NavController){
    Column(modifier = Modifier
        .padding(10.dp, 10.dp, 10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(blackdark)
        .fillMaxHeight()
        .padding(5.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Image(
            painter = painterResource(R.drawable.reporte_visitas_icon),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .size(50.dp)
        )
        Text(
            text = "REPORTE DE VISITA",
            fontStyle = FontStyle.Normal,modifier = Modifier.align(Alignment.CenterHorizontally))

    }
}
@Composable
private fun buttonsReporteVisita (navController: NavController){
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.horizontalScroll(state = ScrollState(1))) {
            Image(
                painter = painterResource(R.drawable.buttton_ranking),
                contentDescription = "",modifier = Modifier
                    .clickable { navController.navigate("home_screen"){
                        popUpTo("home_screen")
                    }
                    }.size(150.dp)
            )
            Image(
                painter = painterResource(R.drawable.button_detalle_ocupacion),
                contentDescription = "",modifier = Modifier
                    .clickable { navController.navigate("home_screen"){
                        popUpTo("home_screen")
                    }
                    }.size(150.dp)
            )
            Image(
                painter = painterResource(R.drawable.button_acumulado_bingo),
                contentDescription = "",modifier = Modifier
                    .clickable { navController.navigate("home_screen"){
                        popUpTo("home_screen")
                    }
                    }.size(150.dp)
            )
            Image(
                painter = painterResource(R.drawable.button_analisis_tecnico),
                contentDescription = "",modifier = Modifier
                    .clickable { navController.navigate("home_screen"){
                        popUpTo("home_screen")
                    }
                    }.size(150.dp)
            )
        }
    }
}