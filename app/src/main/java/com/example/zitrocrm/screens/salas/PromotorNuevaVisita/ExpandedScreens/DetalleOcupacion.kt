package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Horarios
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.GetVisita.RowsDO2
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ProveedorFilter.Rows
import com.example.zitrocrm.repository.SharedPrefence
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.ui.theme.blackdark

@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DetalleOcupacionCard(
    card2: String,
    onCardArrowClick: () -> Unit,
    expand: Boolean,
    viewModelNV: PromotorNuevaVisitaViewModel,
    horario: String,
    context: Context,
    tipo: MutableState<Boolean>,
) {
    Card(
        backgroundColor = blackdark,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 4.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.15f)
                        .align(Alignment.CenterVertically)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.detalles),
                        contentDescription = "IconList",
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = card2,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 12.dp,
                                vertical = 25.dp
                            )
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(0.15f)
                        .align(Alignment.CenterVertically)
                ) {
                    CardArrow(
                        onClick = onCardArrowClick,
                        expanded = expand
                    )
                }
            }
            DetalleOcupacionExpand(
                expandedd = expand,
                viewModelNV = viewModelNV,
                text = horario,
                context = context,
                tipo = tipo
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun DetalleOcupacionExpand(
    expandedd: Boolean = true,
    viewModelNV: PromotorNuevaVisitaViewModel,
    text: String,
    context: Context,
    tipo: MutableState<Boolean>,
) {
    val alert = remember { mutableStateOf("") }
    val proveedor_info = remember { mutableStateListOf("", "0", "0") }
    val paqueteria_familia = remember { mutableStateListOf("", "0") }
    AnimatedVisibility(
        visible = expandedd,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(vertical = 8.dp, horizontal = 18.dp)
        ) {
            Spacer(Modifier.height(5.dp))
            if (viewModelNV.listdetalleOcu.isNotEmpty()) {
                OutlinedTextField(
                    enabled = false,
                    value = text,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.5.dp)
                        .clickable {
                            alert.value = "Horarios"
                        },
                    label = { Text("Horarios de Ocupacion") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Timer,
                            "contentDescription",
                            tint = Color.White
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White
                    )
                )
            }
            OutlinedTextField(
                enabled = false,
                value = proveedor_info[0],
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.5.dp)
                    .clickable {
                        alert.value = "Proveedor"
                    },
                label = { Text("Proveedor") },
                trailingIcon = {
                    Icons.Filled.KeyboardArrowDown
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White
                )
            )
            if (proveedor_info[1].toInt() != 97&&tipo.value||proveedor_info[1].toInt()==24&&tipo.value==false) {
                OutlinedTextField(
                    enabled = false,
                    value = paqueteria_familia[0],
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.5.dp)
                        .clickable {
                            alert.value = "Paqueteria_Familia"
                        },
                    label = {
                        if(tipo.value&&proveedor_info[1].toInt()==24||tipo.value==false&&proveedor_info[1].toInt()==24)Text("Familia")
                        else Text("Paquetería")
                    },
                    trailingIcon = {
                        Icons.Filled.KeyboardArrowDown
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White
                    )
                )
                Spacer(Modifier.height(5.dp))
            }
            Spacer(Modifier.height(10.dp))
            val isValidate by derivedStateOf {
                proveedor_info[0].isNotBlank()
                        && proveedor_info[1].toInt()>0
            }
            val isRotated = rememberSaveable { mutableStateOf(false) }
            val rotationAngle by animateFloatAsState(
                targetValue = if (isRotated.value) 360F else 0F,
                animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
            )
            Button(
                enabled = isValidate,
                onClick = {
                    if(viewModelNV.texthours().isNotBlank()){
                        if (viewModelNV.listdetalleOcu.isNotEmpty()&&viewModelNV.listdetalleOcu.find { it.proveedor == proveedor_info[0] }?.proveedor.toString() == proveedor_info[0]) {
                            Toast.makeText(context, "Ya ha sido agregado anteriormente este proveedor: ${proveedor_info[0]}", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Se agrego correctamente: ${proveedor_info[0]}", Toast.LENGTH_SHORT).show()
                            viewModelNV.addDetalleOcupacion(
                                proveedor_info = proveedor_info,
                                paqueteria_familia = paqueteria_familia,
                                isRotated = isRotated
                            )
                        }
                    }else{
                        Toast.makeText(context, "Selecciona el horario de la visita.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .graphicsLayer {
                        rotationAngle
                    },
                elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.reds)
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Precio Inicio",
                    tint = Color.White
                )
            }
        }
        AlertDetalleOcupacion(
            viewModel = viewModelNV,
            alert = alert,
            proveedor_info = proveedor_info,
            tipo = tipo,
            paqueteria_familia = paqueteria_familia
        )
    }
}


@Composable
fun AlertDetalleOcupacion(
    viewModel: PromotorNuevaVisitaViewModel,
    alert: MutableState<String>,
    proveedor_info: SnapshotStateList<String>,
    tipo: MutableState<Boolean>,
    paqueteria_familia: SnapshotStateList<String>,
) {
    val datastore = SharedPrefence(LocalContext.current)
    val token = datastore.getToken().toString()
    when (alert.value) {
        "Horarios" -> {
            AlertDialog(
                onDismissRequest = {
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
                                        alert.value = ""
                                    }
                            )
                            Text(
                                text = "SELECCIONA LOS HORARIOS",
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                    LazyColumn {
                        itemsIndexed(viewModel.listHorarios) { index, item ->
                            Row {
                                val isChecked = remember { mutableStateOf(false) }
                                viewModel.listdetalleOcu.forEachIndexed() { indexx, items ->
                                    items.horarios.filter { it.horario == item.horario }.forEach {
                                        isChecked.value = true
                                    }
                                }
                                Checkbox(
                                    checked = isChecked.value,
                                    onCheckedChange = {
                                        try {
                                            if (it) {
                                                var lower = 0
                                                viewModel.listdetalleOcu.forEachIndexed() { indexx, items ->
                                                    items.horarios.forEachIndexed { indd, itt ->
                                                        if (itt.horario!! <= item.horario!!) {
                                                            lower = indd + 1
                                                        }
                                                    }
                                                    items.horarios.add(
                                                        lower,
                                                        Horarios(
                                                            horario = item.horario,
                                                            ocupacion1 = "",
                                                            ocupacionLt1 = "",
                                                            porcentaje = ""
                                                        )
                                                    )
                                                }
                                            } else {
                                                viewModel.listdetalleOcu.forEachIndexed() { indexx, items ->
                                                    items.horarios.filter { it.horario == item.horario }
                                                        .forEach {
                                                            items.horarios.remove(
                                                                Horarios(
                                                                    horario = it.horario,
                                                                    ocupacion1 = it.ocupacion1,
                                                                    ocupacionLt1 = it.ocupacionLt1,
                                                                    porcentaje = it.porcentaje
                                                                )
                                                            )
                                                        }
                                                }
                                            }
                                            viewModel.aa()
                                            isChecked.value = it
                                        } catch (e: Exception) {
                                            Log.d("Exception", "Exception Hours", e)
                                        }
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = colorResource(R.color.reds),
                                        uncheckedColor = colorResource(R.color.graydark),
                                        checkmarkColor = colorResource(R.color.white)
                                    )
                                )
                                Text(
                                    text = "${item.horario}:00 hrs",
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                            Divider(color = Color.DarkGray, thickness = 3.dp)
                        }
                        item {
                            Button(
                                onClick = {
                                    alert.value = ""
                                },
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth()
                                    .height(60.dp),
                                elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
                                shape = RoundedCornerShape(10),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(id = R.color.reds)
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = "Precio Inicio",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false
                ),
                shape = RoundedCornerShape(18.dp)
            )
        }
        "Proveedor" -> {
            AlertDialog(
                onDismissRequest = { },
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
                                        alert.value = ""
                                    }
                            )
                            Text(
                                text = "SELECCIONA EL PROVEEDOR",
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                    if (viewModel.proveedorService.isNotEmpty()) {
                        LazyColumn {
                            itemsIndexed(viewModel.proveedorService) { index, item ->
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        proveedor_info[0] = item.nombre.toString()
                                        proveedor_info[1] = item.id.toString()
                                        proveedor_info[2] = item.tipo.toString()
                                        if (tipo.value || tipo.value == false && item.id == 24) {
                                            viewModel.getLibreriaCompetencia(
                                                token = token,
                                                tipo = item.tipo!!.toInt(),
                                                proveedorid = item.id!!,
                                                clasificacion = item.tipo!!.toInt()
                                            )
                                        }
                                        alert.value = ""
                                        paqueteria_familia[0] = ""
                                        paqueteria_familia[1] = "0"
                                    }
                                    .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                    Text(text = item.nombre.toString())
                                }
                            }
                        }
                    } else {
                        Text(text = "No hay resultados", modifier = Modifier.padding(10.dp))
                    }
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false
                ),
                shape = RoundedCornerShape(15.dp)
            )
        }
        "Paqueteria_Familia" -> {
            AlertDialog(
                onDismissRequest = {},
                title = null,
                buttons = {
                    val title = if(tipo.value&&proveedor_info[1].toInt()==24||tipo.value==false&&proveedor_info[1].toInt()==24) "SELECCIONA LA FAMILIA"
                    else "SELECCIONA LA PAQUETERIA"
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
                                        alert.value = ""
                                    }
                            )
                            Text(
                                text = title,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                    if (viewModel.list_familia_paqueteria.isNotEmpty()) {
                        LazyColumn {
                            itemsIndexed(viewModel.list_familia_paqueteria) { index, label ->
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        paqueteria_familia[0] = label.nombre!!
                                        paqueteria_familia[1] = label.id!!.toString()
                                        alert.value = ""
                                    }
                                    .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                    Text(text = label.nombre.toString())
                                }
                            }
                        }
                    } else {
                        Text(text = "No hay resultados", modifier = Modifier.padding(10.dp))
                    }
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false
                ),
                shape = RoundedCornerShape(15.dp)
            )
        }
    }
}

@Composable
fun DetalleOcupacion(
    index: Int,
    item: RowsDO2,
    viewModelNV: PromotorNuevaVisitaViewModel
) {
    var dropProv by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    var maquinas1 by remember { mutableStateOf(item.maquinas1.toString()) }
    if (item.maquinas1 == 0) maquinas1 = ""
    var maquinaslt1 by remember { mutableStateOf(item.maquinasLt1.toString()) }
    if (item.maquinasLt1 == 0) maquinaslt1 = ""
    var total by remember { mutableStateOf(item.total.toString()) }
    if (item.total == 0) total = ""
    if (item.horarios.isEmpty()) {
        viewModelNV.listHorarios.forEach {
            item.horarios.add(
                Horarios(
                    horario = it.horario,
                    ocupacion1 = it.ocupacion1,
                    ocupacionLt1 = it.ocupacionLt1,
                    porcentaje = it.porcentaje
                )
            )
        }
    }
    Column(modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                dropProv = !dropProv
            }
        ) {
            Box {
                Text(
                    text = "N.${index + 1}",
                    modifier = Modifier
                        .padding(vertical = 15.dp, horizontal = 5.dp)
                        .align(Alignment.CenterStart),
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 14.sp
                )
                Text(
                    text = "${item.proveedor}",
                    modifier = Modifier
                        .padding(vertical = 15.dp, horizontal = 5.dp)
                        .align(Alignment.Center),
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 14.sp
                )
                IconButton(
                    onClick = { dropProv = !dropProv },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {
                    CardArrow(
                        onClick = { dropProv = !dropProv },
                        expanded = dropProv
                    )
                }

            }
        }
        Spacer(Modifier.height(5.dp))
        if (dropProv) {
            if(item.juego != null){
                Text(
                    text = item.juego.toString(),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 5.dp),
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 14.sp
                )
            }
            OutlinedTextField(
                enabled = if (item.tipo==1) false else true,
                value = maquinas1,
                onValueChange = {
                    if (viewModelNV.getValidationSum(it)) {
                        item.maquinas1 = it.toInt()
                    } else {
                        item.maquinas1 = 0
                    }
                    maquinas1 = item.maquinas1.toString()
                    item.total = (item.maquinas1!!.toInt() + item.maquinasLt1!!.toInt())
                    total = item.total.toString()
                },
                label = { Text("Maquinas $1.00") },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                leadingIcon = {
                    Icon(
                        Icons.Filled.PointOfSale,
                        "contentDescription",
                        tint = Color.White
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 3.dp)
                    .fillMaxWidth(),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
            )
            OutlinedTextField(
                value = maquinaslt1,
                onValueChange = {
                    if (viewModelNV.getValidationSum(it)) {
                        item.maquinasLt1 = it.toInt()
                    } else {
                        item.maquinasLt1 = 0
                    }
                    maquinaslt1 = item.maquinasLt1.toString()
                    item.total = (item.maquinas1!!.toInt() + item.maquinasLt1!!.toInt())
                    total = item.total.toString()
                },
                label = { Text("Maquinas < $1.00") },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                leadingIcon = {
                    Icon(
                        Icons.Filled.PointOfSale,
                        "contentDescription",
                        tint = Color.White
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 3.dp)
                    .fillMaxWidth(),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )
            OutlinedTextField(
                enabled = false,
                value = total,
                onValueChange = { },
                label = { Text("Total") },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 3.dp)
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        Icons.Filled.PointOfSale,
                        "contentDescription",
                        tint = Color.White
                    )
                },
            )
            Spacer(Modifier.height(10.dp))
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                item.horarios.forEachIndexed { index, horarios ->
                    val ocu1 = remember { mutableStateOf(horarios.ocupacion1.toString()) }
                    val ocum1 = remember { mutableStateOf(horarios.ocupacionLt1.toString()) }
                    Spacer(Modifier.height(10.dp))
                    Card(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .padding(horizontal = 2.dp)
                    ) {
                        Column(Modifier.padding(5.dp)) {
                            Text(
                                text = "${horarios.horario}:00 hrs",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Spacer(Modifier.height(3.dp))
                            OutlinedTextField(
                                enabled = if (item.tipo==1) false else true,
                                value = ocu1.value,
                                onValueChange = {
                                    if (viewModelNV.getValidationSum(it)) {
                                        horarios.ocupacion1 = it
                                        if (it.toInt() > item.maquinas1!!.toInt()) {
                                            horarios.ocupacion1 = item.maquinas1.toString()
                                        }
                                    } else {
                                        horarios.ocupacion1 = ""
                                    }
                                    ocu1.value = horarios.ocupacion1.toString()
                                },
                                modifier = Modifier
                                    .width(160.dp),
                                label = { Text("Ocupación $ 1.00") },
                                leadingIcon = {
                                    Icon(
                                        Icons.Filled.PriceCheck,
                                        "contentDescription",
                                        tint = Color.White
                                    )
                                },
                                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next,
                                    keyboardType = KeyboardType.Number
                                )
                            )
                            OutlinedTextField(
                                value = ocum1.value,
                                onValueChange = {
                                    if (viewModelNV.getValidationSum(it)) {
                                        horarios.ocupacionLt1 = it
                                        if (it.toInt() > item.maquinasLt1!!.toInt()) {
                                            horarios.ocupacionLt1 = item.maquinasLt1.toString()
                                        }
                                    } else {
                                        horarios.ocupacionLt1 = ""
                                    }
                                    ocum1.value = horarios.ocupacionLt1.toString()
                                },
                                modifier = Modifier
                                    .width(160.dp),
                                label = { Text("Ocupación < $ 1.00") },
                                leadingIcon = {
                                    Icon(
                                        Icons.Filled.PriceCheck,
                                        "contentDescription",
                                        tint = Color.White
                                    )
                                },
                                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next,
                                    keyboardType = KeyboardType.Number
                                )
                            )
                            try {
                                horarios.porcentaje = viewModelNV.getPorcentaje(
                                    viewModelNV.getSum(
                                        horarios.ocupacion1!!,
                                        horarios.ocupacionLt1!!
                                    ).toString(), item.total.toString()
                                ).toInt().toString()
                            } catch (e: Exception) {
                                Log.d("Porcenjae", "Error", e)
                            }
                            OutlinedTextField(
                                enabled = false,
                                value = horarios.porcentaje!!,
                                onValueChange = { },
                                modifier = Modifier
                                    .width(160.dp),
                                label = { Text("Ocupacion %") },
                                leadingIcon = {
                                    Icon(
                                        Icons.Filled.PointOfSale,
                                        "contentDescription",
                                        tint = Color.White
                                    )
                                },
                                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next,
                                    keyboardType = KeyboardType.Number
                                )
                            )
                        }
                        Spacer(Modifier.width(10.dp))
                    }
                }
            }
        }
    }
}