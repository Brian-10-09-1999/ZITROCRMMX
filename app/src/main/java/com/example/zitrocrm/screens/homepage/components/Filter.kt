package com.example.zitrocrm.screens.homepage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.Filter.FilterViewModel
import com.example.zitrocrm.ui.theme.reds
import com.example.zitrocrm.repository.SharedPrefence

@Composable
fun FilterScreen(navController: NavController, filterviewmodel : FilterViewModel){
    val context = LocalContext.current
    val datastore = SharedPrefence(context)
    val token = datastore.getToken()
    val dialogShape = RoundedCornerShape(18.dp)
    var clientes = remember {
        mutableStateOf(false)
    }
    var regiones = remember {
        mutableStateOf(false)
    }
    var salasm = remember {
        mutableStateOf(false)
    }
    Scaffold(
    topBar = {
        TopAppBar(
            elevation = 0.dp,
            modifier = Modifier.height(70.dp),
            title = {
                Box(modifier = Modifier.fillMaxSize()) {
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
    ) {
        Column() {
            dropDownMenuCliente(
                navController,
                clientes,
                regiones,
                salasm
            )
        }
        if (clientes.value) {
            AlertDialog(
                onDismissRequest = {
                    clientes.value = false
                },
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
                                Icons.Filled.ArrowBack, "Hora", modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(horizontal = 10.dp)
                                    .clickable {
                                        clientes.value = false
                                    }
                            )
                            Text(
                                text = "SELECCIONA EL CLIENTE",
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                    LazyColumn() {
                        itemsIndexed(filterviewmodel.clientesFilter){index,label->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    clientes.value = false
                                    datastore.saveCLiente(label.id!!,label.nombre!!)
                                    datastore.saveRegionInf(0,"")
                                    datastore.saveSala(0,"",0)
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Text(text = label.nombre.toString())
                            }
                        }
                    }
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false
                ),
                modifier = Modifier,
                shape = dialogShape
            )
        }
        if (regiones.value) {
            AlertDialog(
                onDismissRequest = {
                    regiones.value = false
                },
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
                                Icons.Filled.ArrowBack, "Hora", modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(horizontal = 10.dp)
                                    .clickable {
                                        regiones.value = false
                                    }
                            )
                            Text(
                                text = "SELECCIONA LA REGION",
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                    LazyColumn() {
                        itemsIndexed(filterviewmodel.regionesFilter){index, region_it ->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    regiones.value = false
                                    datastore.saveRegionInf(
                                        region_it.id!!,
                                        region_it.nombre!!
                                    )
                                    filterviewmodel.getSalasFilter(
                                        token = token.toString(),
                                        cliente = datastore.getIDCliente(),
                                        region = region_it.id!!.toInt()
                                    )
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Text(text = region_it.nombre.toString())
                            }
                        }
                    }
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false
                ),
                modifier = Modifier,
                shape = dialogShape
            )
        }
        if (salasm.value) {
            AlertDialog(
                onDismissRequest = {
                    salasm.value = false
                },
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
                                Icons.Filled.ArrowBack, "Hora", modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(horizontal = 10.dp)
                                    .clickable {
                                        salasm.value = false
                                    }
                            )
                            Text(
                                text = "SELECCIONA LA SALA",
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                    if(filterviewmodel.salasFilter.isNotEmpty()){
                        LazyColumn() {
                            itemsIndexed(filterviewmodel.salasFilter){ index,label->
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        salasm.value = false
                                        datastore.saveSala(
                                            label.id!!,
                                            label.nombre!!,
                                            label.OfficeID!!.toInt()
                                        )
                                    }
                                    .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                    Text(text = label.nombre.toString())
                                }
                            }
                        }
                    }else{
                        Text(text = "No hay resultados", modifier = Modifier.padding(10.dp))
                    }
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false
                ),
                modifier = Modifier,
                shape = dialogShape
            )
        }
    }
}
@Composable
private fun dropDownMenuCliente(
    navController: NavController,
    clientes: MutableState<Boolean>,
    regiones: MutableState<Boolean>,
    salasm: MutableState<Boolean>
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val datastore = SharedPrefence(context)
    val cliente = ""+datastore.getCliente()
    val region = ""+datastore.getRegion()
    val salas = ""+datastore.getSala()
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    val icon2 = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    val icon3 = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        Text(
            text = "Filtrar Busqueda",
            modifier = Modifier
                .padding(vertical = 15.dp)
                .align(alignment = Alignment.CenterHorizontally),
            style = MaterialTheme.typography.subtitle2,
            fontSize = 19.sp,
            color = Color.White
        )
        CompositionLocalProvider(
            LocalTextInputService provides null
        ) {
            OutlinedTextField(
                value = cliente,
                onValueChange = { cliente },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text("Cliente") },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable {
                            clientes.value = true
                        }
                    )
                }
            )
        }
    }
    Column(Modifier.padding(20.dp)) {
        CompositionLocalProvider(
            LocalTextInputService provides null
        ) {
            OutlinedTextField(
                value = region,
                onValueChange = { region },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {Text("Region")},
                trailingIcon = {
                    Icon(icon2,"contentDescription",
                        Modifier.clickable {
                            regiones.value = true
                        }
                    )
                }
            )
        }
    }
    Column(Modifier.padding(20.dp)) {
        CompositionLocalProvider(
            LocalTextInputService provides null
        ) {
            OutlinedTextField(
                value = salas,
                onValueChange = { salas },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {Text("Sala")},
                trailingIcon = {
                    Icon(icon3,"contentDescription",
                        Modifier.clickable {
                            salasm.value = true
                        }
                    )
                }
            )
        }
    }
    Column(modifier = Modifier.padding(20.dp)) {
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),

            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.reds)
            )
        ) {
            Text(
                text = "Filtrar",
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}





