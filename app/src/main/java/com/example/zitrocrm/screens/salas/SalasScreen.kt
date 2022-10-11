package com.example.zitrocrm.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.Filter.FilterViewModel
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.ui.theme.blackdark
import com.example.zitrocrm.ui.theme.reds
import com.example.zitrocrm.repository.SharedPrefence

/** SIZE BUTTONS AND SIZE ICONS **/
val SizeButtons = 140.dp
val SizeIconsTittle = 40.dp

@Composable
fun SalasScreen(
    navController: NavController,
    viewModelFilter: FilterViewModel,
    viewModelPromotorNuevaVisita : PromotorNuevaVisitaViewModel
){
    val context = LocalContext.current
    val datastore = SharedPrefence(context)
    val token = datastore.getToken().toString()

    Scaffold(
        topBar = {
            CustomTopAppBarSalas(navController)
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModelFilter.getFilters(token, context = context)
                    //navController.navigate(route = Destination.FilterScreen.route)
                }
            ) {
                Image(
                    painter = painterResource(R.drawable.button_filter_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(57.dp)
                        .clip(CircleShape)
                )
            } },
    ) {
        ContentSalas(navController,viewModelPromotorNuevaVisita)
    }
}
/** TOPBAR **/
@Composable
private fun CustomTopAppBarSalas(navController: NavController) {
    val datastore = SharedPrefence(LocalContext.current)
    val cliente = ""+datastore.getCliente().toString()
    val region = ""+datastore.getRegion().toString()
    val sala = ""+datastore.getSala().toString()

    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.height(70.dp),
        title = {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.align(alignment = Alignment.CenterStart).padding(start = 35.dp)) {
                    Text(text = "FILTROS DE BUSQUEDA ", fontSize = 9.sp, color = Color.White)
                    Text(text = "Cliente: "+cliente, fontSize = 7.sp, color = Color.White)
                    Text(text = "Region: "+region, fontSize = 7.sp, color = Color.White)
                    Text(text = "Sala: "+sala, fontSize = 7.sp, color = Color.White)
                }
                Image(
                    painter = painterResource(R.drawable.back_button),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .align(Alignment.CenterStart)
                        .size(29.dp)
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
/** CONTENT PAGE SALA **/
@Composable
private fun ContentSalas(
    navController: NavController,
    viewModelPromotorNuevaVisita : PromotorNuevaVisitaViewModel
){
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(state = ScrollState(1))) {
        Column(modifier = Modifier.fillMaxSize()) {
            titleSalas()
            titleactividad()
            contentactividad(navController)
            titlereporte()
            contentreporte(navController,viewModelPromotorNuevaVisita)
            titlelayouts()
            contentlayouts(navController)
            titlelgalery()
            contentgalery(navController)
            titlelcompetencia()
            contentcompetencia(navController)
            titlelresumensala()
            contentresumensala(navController)
        }
    }
}
/** CHILD CONTENT PAGE **/
@Composable
private fun titleSalas(){
    Column(modifier = Modifier
        .padding(10.dp, 10.dp, 10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(blackdark)
        .fillMaxHeight()
        .padding(5.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = CenterHorizontally)
    {

        Image(
            painter = painterResource(R.drawable.nav_sala_icon),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .size(50.dp)
        )
        Text(
            text = "SALAS",
            fontStyle = FontStyle.Normal,modifier = Modifier.align(CenterHorizontally))

    }
}

@Composable
private fun titleactividad(){
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(blackdark)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Image(
            painter = painterResource(R.drawable.actividades_fecha_icon),
            contentDescription = "",modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(5.dp)
                .size(SizeIconsTittle)
        )
        Text(
            text = "ACTIVIDADES POR FECHA",
            fontStyle = FontStyle.Normal,
            modifier = Modifier.align(alignment = Alignment.Center))
    }
}

@Composable
private fun contentactividad(navController: NavController){
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.horizontalScroll(state = ScrollState(1))) {
            Image(
                painter = painterResource(R.drawable.button_orden_servicio),
                contentDescription = "",modifier = Modifier
                    .clickable { navController.navigate("home_screen"){
                        popUpTo("home_screen")
                    }
                    }
                    .size(SizeButtons)
            )
        }
    }
}

@Composable
private fun titlereporte(){
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(blackdark)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Image(
            painter = painterResource(R.drawable.reporte_campo_icon),
            contentDescription = "",modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(5.dp)
                .size(SizeIconsTittle)
        )
        Text(
            text = "REPORTE DE CAMPO",
            fontStyle = FontStyle.Normal,
            modifier = Modifier.align(alignment = Alignment.Center))
    }
}

@Composable
private fun contentreporte(
    navController: NavController,
    viewModelPromotorNuevaVisita : PromotorNuevaVisitaViewModel
){
    val datastore = SharedPrefence(LocalContext.current)
    val clienteid = ""+datastore.getIDCliente().toString()
    val token = datastore.getToken().toString()
    val salaid = datastore.getSalaId().toString()
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.horizontalScroll(state = ScrollState(1))) {
            Image(
                painter = painterResource(R.drawable.button_promotor_nueva_visita),
                contentDescription = "",modifier = Modifier
                    .clickable { navController.navigate("promotor_new_screen")
                        viewModelPromotorNuevaVisita.getFoliosTecnicos(token,salaid.toInt(),clienteid.toInt())
                        if (viewModelPromotorNuevaVisita.tipo.value){
                            viewModelPromotorNuevaVisita.getNuevaVisitaFilters(token, 1)
                        }else{
                            viewModelPromotorNuevaVisita.getNuevaVisitaFilters(token, 2)
                        }
                    }
                    .size(SizeButtons)
            )
            Image(
                painter = painterResource(R.drawable.button_promotor_reporte_visita),
                contentDescription = "",modifier = Modifier
                    .clickable {navController.navigate("promotor_report_screen") }
                    .size(SizeButtons)
            )
            Image(
                painter = painterResource(R.drawable.button_coordinadores),
                contentDescription = "",modifier = Modifier
                    .clickable { }
                    .size(SizeButtons)
            )
            Image(
                painter = painterResource(R.drawable.button_kam),
                contentDescription = "",modifier = Modifier
                    .clickable { }
                    .size(SizeButtons)
            )
        }
    }
}

@Composable
private fun titlelayouts(){
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(blackdark)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Image(
            painter = painterResource(R.drawable.layouts_icon),
            contentDescription = "",modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(5.dp)
                .size(SizeIconsTittle)
        )
        Text(
            text = "LAYOUTS",
            fontStyle = FontStyle.Normal,
            modifier = Modifier.align(alignment = Alignment.Center))
    }
}
@Composable
private fun contentlayouts(navController: NavController){
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.horizontalScroll(state = ScrollState(1))) {
            Image(
                painter = painterResource(R.drawable.button_layouts),
                contentDescription = "",modifier = Modifier
                    .clickable { }
                    .size(SizeButtons)
            )
        }
    }
}

@Composable
private fun titlelgalery(){
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(blackdark)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Image(
            painter = painterResource(R.drawable.galery_icon),
            contentDescription = "",modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(5.dp)
                .size(SizeIconsTittle)
        )
        Text(
            text = "GALERIA",
            fontStyle = FontStyle.Normal,
            modifier = Modifier.align(alignment = Alignment.Center))
    }
}
@Composable
private fun contentgalery(navController: NavController){
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.horizontalScroll(state = ScrollState(1))) {
            Image(
                painter = painterResource(R.drawable.button_consulta),
                contentDescription = "",modifier = Modifier
                    .clickable { }
                    .size(SizeButtons)
            )
            Image(
                painter = painterResource(R.drawable.button_carga_archivos),
                contentDescription = "",modifier = Modifier
                    .clickable { }
                    .size(SizeButtons)
            )
        }
    }
}

@Composable
private fun titlelcompetencia(){
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(blackdark)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Image(
            painter = painterResource(R.drawable.competencia_icon),
            contentDescription = "",modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(5.dp)
                .size(SizeIconsTittle)
        )
        Text(
            text = "COMPETENCIA",
            fontStyle = FontStyle.Normal,
            modifier = Modifier.align(alignment = Alignment.Center))
    }
}
@Composable
private fun contentcompetencia(navController: NavController){
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.horizontalScroll(state = ScrollState(1))) {
            Image(
                painter = painterResource(R.drawable.button_juegos_nuevos_bingos),
                contentDescription = "",modifier = Modifier
                    .clickable {
                        navController.navigate("juegos_nuevos_bingo")
                        }
                    .size(SizeButtons)
            )
            Image(
                painter = painterResource(R.drawable.button_juegos_nuevos_slots),
                contentDescription = "",modifier = Modifier
                    .clickable { }
                    .size(SizeButtons)
            )
            Image(
                painter = painterResource(R.drawable.button_juegos_sala),
                contentDescription = "",modifier = Modifier
                    .clickable { }
                    .size(SizeButtons)
            )
        }
    }
}

@Composable
private fun titlelresumensala(){
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(blackdark)
        .fillMaxWidth()
        .padding(5.dp))
    {
        Image(
            painter = painterResource(R.drawable.resumen_sala_icon),
            contentDescription = "",modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(5.dp)
                .size(SizeIconsTittle)
        )
        Text(
            text = "RESUMEN DE SALA",
            fontStyle = FontStyle.Normal,
            modifier = Modifier.align(alignment = Alignment.Center))
    }
}
@Composable
private fun contentresumensala(navController: NavController){
    Box(modifier = Modifier
        .padding(10.dp, 5.dp)
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .padding(5.dp))
    {
        Row(modifier = Modifier.horizontalScroll(state = ScrollState(1))) {
            Image(
                painter = painterResource(R.drawable.button_mensual),
                contentDescription = "",modifier = Modifier
                    .clickable { }
                    .size(SizeButtons)
            )
        }
    }
}