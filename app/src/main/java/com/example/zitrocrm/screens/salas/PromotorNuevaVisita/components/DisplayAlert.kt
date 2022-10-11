package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.Filter.FilterViewModel
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel

/**FILTERS**/

var alertProveedorOcupacionSlots = mutableStateOf(false)

var alertProveedorLoMasJugado = mutableStateOf(false)

var alertJuegosFilter = mutableStateOf(false)

var alertJuegosComentariosJugadores = mutableStateOf(false)

var alertProveedorSonido = mutableStateOf(false)

var alertObservacionesCompetecia = mutableStateOf(false)

var alertDetalleSave = mutableStateOf(false)

var alertLibreriaOcupacionZitroBingo = mutableStateOf(false)


var alertLibreriaOcupacionSlots = mutableStateOf(false)

var alertSubJuegosOcupacionBingo = mutableStateOf(false)

var alertSubJuegosOcupacionSlots = mutableStateOf(false)


@Composable
fun DisplayAlert(
    viewModel: PromotorNuevaVisitaViewModel,
    viewModel2: FilterViewModel,
    modifier: Modifier = Modifier,
    onDialogStateChange: ((Boolean) -> Unit)? = null,
    onDismissRequest: (() -> Unit)? = null,
    navController: NavController
) {
    val dialogShape = RoundedCornerShape(18.dp)
    /*if (alertProveedorAcumulados.value) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
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
                                    alertProveedorAcumulados.value = false
                                    navController.popBackStack()
                                }
                        )
                        Text(
                            text = "SELECCIONA EL PROVEEDOR",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                if (viewModel.proveedores_selections.isNotEmpty()) {
                    LazyColumn() {
                        itemsIndexed(viewModel.proveedores_selections) { index, label ->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.selectProveedorAcumulados(
                                        id = label.id!!,
                                        nombre = label.nombre!!,
                                    )
                                    //navController.popBackStack()
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Text(text = label.nombre.toString())
                            }
                        }
                        /*item {
                            viewModel.proveedorOcupacion.forEach { label ->
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.selectProveedorAcumulados(
                                            id = label.id!!,
                                            nombre = label.nombre!!,
                                        )
                                        navController.popBackStack()
                                    }
                                    .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                    Text(text = label.nombre.toString())
                                }
                            }
                        }*/
                    }
                } else {
                    Text(text = "No hay resultados", modifier = Modifier.padding(10.dp))
                }

            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            ),
            modifier = modifier,
            shape = dialogShape
        )
    }*/

    /*if (alertLibreriaOcupacionZitroBingo.value) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
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
                                    alertLibreriaOcupacionZitroBingo.value = false
                                    //navController.popBackStack()
                                }
                        )
                        Text(
                            text = "SELECCIONA LA LIBRERIA",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                if (viewModel.juegosFilterBingo.isNotEmpty()) {
                    LazyColumn() {
                        itemsIndexed(viewModel.juegosFilterBingo) { index, label ->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    /**SE SELECCIONA LA PAQUETERIA ZITRO BINGO**/
                                    //navController.popBackStack()
                                    viewModel.selectLibreriaOcupacion(
                                        id = label.id!!,
                                        nombre = label.nombre!!,
                                    )
                                    viewModel.getJuegosOcupacion(1, token, label.id!!)
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Text(text = label.nombre.toString())
                            }
                        }
                        /*item {
                            viewModel.juegosFilterBingo.forEach { label ->
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        /**SE SELECCIONA LA LIBRERIA ZITRO BINGO**/
                                        navController.popBackStack()
                                        viewModel.selectLibreriaOcupacion(
                                            id = label.id!!,
                                            nombre = label.nombre!!,
                                        )
                                        viewModel.getJuegosOcupacion(1,token, label.id!!)
                                    }
                                    .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                    Text(text = label.nombre.toString())
                                }
                            }
                        }*/
                    }
                } else {
                    Text(text = "No hay resultados", modifier = Modifier.padding(10.dp))
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            ),
            modifier = modifier,
            shape = dialogShape
        )
    }*/
    /*if (alertLibreriaOcupacionZitroSlots.value) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
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
                                    alertLibreriaOcupacionZitroSlots.value = false
                                    //navController.popBackStack()
                                }
                        )
                        Text(
                            text = "SELECCIONA LA LIBRERIA",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                if (viewModel.juegosFilterSlots.isNotEmpty()) {
                    LazyColumn() {
                        itemsIndexed(viewModel.juegosFilterSlots) { index, label ->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    /**SE SELECCIONA LA LIBRERIA DE ZITRO SLOTS **/
                                    //navController.popBackStack()
                                    /*viewModel.selectLibreriaOcupacionSlots(
                                        id = label.id!!,
                                        nombre = label.nombre!!,
                                    )*/
                                    viewModel.getJuegosOcupacion(2, token, label.id!!)
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Text(text = label.nombre.toString())
                            }
                        }
                        /*item {
                            viewModel.juegosFilterSlots.forEach { label ->
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        /**SE SELECCIONA LA LIBRERIA DE ZITRO SLOTS **/
                                        navController.popBackStack()
                                        viewModel.selectLibreriaOcupacionSlots(
                                            id = label.id!!,
                                            nombre = label.nombre!!,
                                        )
                                        viewModel.getJuegosOcupacion(2,token, label.id!!)
                                    }
                                    .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                    Text(text = label.nombre.toString())
                                }
                            }
                        }*/
                    }
                } else {
                    Text(text = "No hay resultados", modifier = Modifier.padding(10.dp))
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            ),
            modifier = modifier,
            shape = dialogShape
        )
    }*/
    if (alertSubJuegosOcupacionSlots.value) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
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
                                    alertSubJuegosOcupacionSlots.value = false
                                    //navController.popBackStack()
                                }
                        )
                        Text(
                            text = "SELECCIONA EL JUEGO",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                /*if(viewModel.subjuegos_ocupacion_slots.isNotEmpty()){
                    LazyColumn() {
                        itemsIndexed(viewModel.subjuegos_ocupacion_slots){index,label->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    /**SELECCIONA SUB JUEGO DE OCUPACION SLOTS**/
                                    //navController.popBackStack()
                                    viewModel.selectSubJuegosSlots(
                                        id = label.id!!,
                                        nombre = label.nombre!!
                                    )
                                    //viewModel.getJuegosOcupacion(token, label.id!!)
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Text(text = label.nombre.toString())
                            }
                        }
                        /*item {
                            viewModel.subjuegos_ocupacion_slots.forEach { label ->
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        /**SELECCIONA SUB JUEGO DE OCUPACION SLOTS**/
                                        navController.popBackStack()
                                        viewModel.selectSubJuegosSlots(
                                            id = label.id!!,
                                            nombre = label.nombre!!
                                        )
                                        //viewModel.getJuegosOcupacion(token, label.id!!)
                                    }
                                    .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                    Text(text = label.nombre.toString())
                                }
                            }
                        }*/
                    }
                }else{
                    Text(text = "No hay resultados", modifier = Modifier.padding(10.dp))
                }*/
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            ),
            modifier = modifier,
            shape = dialogShape
        )
    }
    if (alertProveedorLoMasJugado.value) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
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
                                    alertProveedorLoMasJugado.value = false
                                    //navController.popBackStack()
                                }
                        )
                        Text(
                            text = "SELECCIONA EL PROVEEDOR",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                if (viewModel.proveedores_selections.isNotEmpty()) {
                    LazyColumn() {
                        itemsIndexed(viewModel.proveedores_selections) { index, label ->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    //navController.popBackStack()
                                    viewModel.selectProveedorLoMasJugado(
                                        id = label.id!!,
                                        nombre = label.nombre!!,
                                    )
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Text(text = label.nombre.toString())
                            }
                        }
                        /*item {
                            viewModel.proveedorOcupacion.forEach { label ->
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.popBackStack()
                                        viewModel.selectProveedorLoMasJugado(
                                            id = label.id!!,
                                            nombre = label.nombre!!,
                                        )
                                    }
                                    .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                    Text(text = label.nombre.toString())
                                }
                            }
                        }*/
                    }
                } else {
                    Text(text = "No hay resultados", modifier = Modifier.padding(10.dp))
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            ),
            modifier = modifier,
            shape = dialogShape
        )
    }
    if (alertJuegosFilter.value) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
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
                                    alertJuegosFilter.value = false
                                    //navController.popBackStack()
                                }
                        )
                        Text(
                            text = "SELECCIONA EL PRODUCTO",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                LazyColumn() {
                    itemsIndexed(viewModel.juegosFilter) { index, label ->
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                //navController.popBackStack()
                                viewModel.selectJuegoMasJugado(
                                    id = label.id!!,
                                    nombre = label.nombre!!,
                                )
                            }
                            .padding(horizontal = 20.dp, vertical = 10.dp)) {
                            Text(text = label.nombre.toString())
                        }
                    }
                    /*item {
                        viewModel.juegosFilter.forEach { label ->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.popBackStack()
                                    viewModel.selectJuegoMasJugado(
                                        id = label.id!!,
                                        nombre = label.nombre!!,
                                    )
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Text(text = label.nombre.toString())
                            }
                        }
                    }*/
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            ),
            modifier = modifier,
            shape = dialogShape
        )
    }
    if (alertJuegosComentariosJugadores.value) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
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
                                    alertJuegosComentariosJugadores.value = false
                                    //navController.popBackStack()
                                }
                        )
                        Text(
                            text = "SELECCIONA EL PRODUCTO",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                LazyColumn() {
                    itemsIndexed(viewModel.juegosFilter) { index, label ->
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                //navController.popBackStack()
                                viewModel.selectJuegoComentGeneralesJugadores(
                                    id = label.id!!,
                                    nombre = label.nombre!!,
                                )
                            }
                            .padding(horizontal = 20.dp, vertical = 10.dp)) {
                            Text(text = label.nombre.toString())
                        }
                    }
                    /*item {
                        viewModel.juegosFilter.forEach { label ->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.popBackStack()
                                    viewModel.selectJuegoComentGeneralesJugadores(
                                        id = label.id!!,
                                        nombre = label.nombre!!,
                                    )
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Text(text = label.nombre.toString())
                            }
                        }
                    }*/
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            ),
            modifier = modifier,
            shape = dialogShape
        )
    }
    if (alertProveedorSonido.value) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
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
                                    alertProveedorSonido.value = false
                                    //navController.popBackStack()
                                }
                        )
                        Text(
                            text = "SELECCIONA EL PROVEEDOR",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                if (viewModel.proveedores_selections.isNotEmpty()) {
                    LazyColumn() {
                        itemsIndexed(viewModel.proveedores_selections) { index, label ->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    //navController.popBackStack()
                                    viewModel.selectProveedorSonido(
                                        id = label.id!!,
                                        nombre = label.nombre!!,
                                    )
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Text(text = label.nombre.toString())
                            }
                        }
                        /*item {
                            viewModel.proveedorOcupacion.forEach { label ->
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.popBackStack()
                                        viewModel.selectProveedorSonido(
                                            id = label.id!!,
                                            nombre = label.nombre!!,
                                        )
                                    }
                                    .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                    Text(text = label.nombre.toString())
                                }
                            }
                        }*/
                    }
                } else {
                    Text(text = "No hay resultados", modifier = Modifier.padding(10.dp))
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            ),
            modifier = modifier,
            shape = dialogShape
        )
    }
    if (alertObservacionesCompetecia.value) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
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
                                    alertObservacionesCompetecia.value = false
                                    //navController.popBackStack()
                                }
                        )
                        Text(
                            text = "SELECCIONA EL PROVEEDOR",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                if (viewModel.proveedores_selections.isNotEmpty()) {
                    LazyColumn() {
                        itemsIndexed(viewModel.proveedorObservacionesCompetencia) { index, label ->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    //navController.popBackStack()
                                    viewModel.selectProvedorObservaciones(
                                        id = label.id!!,
                                        nombre = label.nombre!!,
                                    )
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Text(text = label.nombre.toString())
                            }
                        }
                    }
                    /*Column(modifier = Modifier
                        .verticalScroll(state = ScrollState(1))) {
                        viewModel.proveedorObservacionesCompetencia.forEach { label ->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.popBackStack()
                                    viewModel.selectProvedorObservaciones(
                                        id = label.id!!,
                                        nombre = label.nombre!!,
                                    )
                                }
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Text(text = label.nombre.toString())
                            }
                        }
                    }*/
                } else {
                    Text(text = "No hay resultados", modifier = Modifier.padding(10.dp))
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            ),
            modifier = modifier,
            shape = dialogShape
        )
    }
    /*if (alertDetalleSave.value) {
        AlertDialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                onDismissRequest?.invoke()
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
                                    alertDetalleSave.value = false
                                    viewModel.networkstate_ID.value = 0
                                    navController.popBackStack()
                                }
                        )
                        Text(
                            text = "DETALLE DE ENVIO DEL REPORTE",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                LazyColumn() {
                    item {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 10.dp)) {
                            if(viewModel.networkstate_ID.value>0){
                                Text(text = "Se Guardo Correctamente",
                                    modifier = Modifier.align(CenterHorizontally)
                                )
                                Text(text = "ID DEL REPORTE: "+viewModel.networkstate_ID.value.toString(),
                                    modifier = Modifier.align(CenterHorizontally)
                                )
                            }else if (viewModel.networkstate_ID.value==0){
                                Text(text = "! ERROR ¡ COMPRUEBA TU INFORMACION "+viewModel.networkstate.value,
                                    modifier = Modifier.align(CenterHorizontally)
                                )
                            }
                            Button(
                                onClick = {
                                    alertDetalleSave.value = false
                                    navController.popBackStack()
                                    viewModel.networkstate_ID.value = 0
                                },
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth()
                                    .height(60.dp),
                                elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
                                shape = RoundedCornerShape(10),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(id = com.example.zitrocrm.R.color.reds)
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = "Precio Inicio",
                                )
                            }
                        }
                    }
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            ),
            modifier = modifier,
            shape = dialogShape
        )
    }*/
}
