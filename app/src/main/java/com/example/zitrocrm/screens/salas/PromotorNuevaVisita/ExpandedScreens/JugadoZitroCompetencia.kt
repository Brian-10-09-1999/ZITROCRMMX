package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.MasJugado
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Progresivos
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Zona
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.ui.theme.blackdark
import com.example.zitrocrm.ui.theme.graydark


@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun JugadoZitroCompetencia(
    card5: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    viewModelNV: PromotorNuevaVisitaViewModel,
    list_mas_jugado: ArrayList<MasJugado>,
    tipo: MutableState<Boolean>
) {
    val alert_proveedor = remember { mutableStateOf(false) }
    val proveedor_info = remember { mutableStateListOf("", "0", "0") }
    val alert = remember { mutableStateOf(false) }
    val producto_paqueteria = remember { mutableStateListOf("", "0") }
    val zona = remember { mutableStateListOf<Zona>() }
    val zona_fumar = remember { mutableStateOf(false) }
    val zona_nofumar = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val tiro_minimo = remember { mutableStateOf("") }
    val tiro_maximo = remember { mutableStateOf("") }
    val tiro_promedio = remember { mutableStateOf("") }
    val apuestas_promedio = remember { mutableStateOf("") }
    val promedio_ocupacion = remember { mutableStateOf("") }
    val unidadOcupacion = remember { mutableStateOf("") }
    var formatexpanded = remember { mutableStateOf(false) }
    val format_hr_min = listOf("Hr", "Min")
    val progresivos = remember { mutableListOf<Progresivos>() }
    val sap2 = remember { mutableStateOf(false) }
    val lap2 = remember { mutableStateOf(false) }
    val icon =
        if (alert_proveedor.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    val icon2 = if (alert.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    val focusManager = LocalFocusManager.current

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
            Box {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.15f)
                            .align(CenterVertically)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.nav_maquinas_icon),
                            contentDescription = "IconList",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(start = 10.dp)//.padding(start = 10.dp, top = 2.dp, bottom = 2.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = card5,
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
                            .align(CenterVertically)
                    ) {
                        CardArrow(
                            onClick = onCardArrowClick,
                            expanded = expanded
                        )
                    }
                }
            }
            JugadoZitroCompetenciaExpand(
                expanded = expanded,
                viewModel = viewModelNV,
                alert_proveedor = alert_proveedor,
                proveedor_info = proveedor_info,
                array_mas_jugado = list_mas_jugado,
                tipo = tipo,
                alert = alert,
                producto_paqueteria = producto_paqueteria,
                array_zona = zona,
                zona_fumar = zona_fumar,
                zona_nofumar = zona_nofumar,
                context = context,
                tiro_minimo = tiro_minimo,
                tiro_maximo = tiro_maximo,
                tiro_promedio = tiro_promedio,
                apuestas_promedio = apuestas_promedio,
                promedio_ocupacion = promedio_ocupacion,
                unidadOcupacion = unidadOcupacion,
                formatexpanded = formatexpanded,
                format_hr_min = format_hr_min,
                progresivos = progresivos,
                icon = icon,
                icon2 = icon2,
                focusManager = focusManager,
                sap2 = sap2,
                lap2 = lap2
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun JugadoZitroCompetenciaExpand(
    expanded: Boolean = true,
    viewModel: PromotorNuevaVisitaViewModel,
    alert_proveedor: MutableState<Boolean>,
    proveedor_info: SnapshotStateList<String>,
    array_mas_jugado: ArrayList<MasJugado>,
    tipo: MutableState<Boolean>,
    alert: MutableState<Boolean>,
    producto_paqueteria: SnapshotStateList<String>,
    array_zona: SnapshotStateList<Zona>,
    zona_fumar: MutableState<Boolean>,
    zona_nofumar: MutableState<Boolean>,
    context: Context,
    tiro_minimo: MutableState<String>,
    tiro_maximo: MutableState<String>,
    tiro_promedio: MutableState<String>,
    apuestas_promedio: MutableState<String>,
    promedio_ocupacion: MutableState<String>,
    unidadOcupacion: MutableState<String>,
    formatexpanded: MutableState<Boolean>,
    format_hr_min: List<String>,
    progresivos: MutableList<Progresivos>,
    icon: ImageVector,
    icon2: ImageVector,
    focusManager: FocusManager,
    sap2: MutableState<Boolean>,
    lap2: MutableState<Boolean>,
) {
    AnimatedVisibility(
        visible = expanded,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(8.dp)
        ) {
            Box(
                Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    //--------------------ALERT PROVEEDOR---------------------//
                    AlertProveedorSeleccionado(
                        list_proveedor = viewModel.proveedores_selections,
                        alert_proveedor = alert_proveedor,
                        proveedor_info = proveedor_info,
                        onclick = {
                            viewModel
                                .getLibreriaCompetencia(
                                    juegos = false,
                                    tipo = proveedor_info[2].toInt(),
                                    proveedorid = proveedor_info[1].toInt(),
                                    clasificacion = proveedor_info[2].toInt(),
                                    context = context
                                )
                            producto_paqueteria[0] = ""
                            producto_paqueteria[1] = "0"
                        },
                        competencia = false
                    )
//----------------------------------------ALERT SELECCIONA JUEGO--------------------------------------//
                    AlertPaqueteria(
                        viewModel = viewModel,
                        alert = alert,
                        proveedor_info = proveedor_info,
                        tipo = tipo,
                        paqueteria_familia = producto_paqueteria
                    )
//----------------------------------------SELECCIONA PROVEEDOR----------------------------------------//
                    OutlinedTextField(
                        enabled = false,
                        value = proveedor_info[0],
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                alert_proveedor.value = true
                            },
                        label = {
                            if (proveedor_info[0].isBlank()) {
                                Text("Selecciona el Proveedor")
                            } else {
                                Text("Proveedor")
                            }
                        }, trailingIcon = {
                            Icon(icon, "contentDescription")
                        }, colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
//----------------------------------------SELECCIONA JUEGO-----------------------------------------//
                    if(viewModel.list_familia_paqueteria.isNotEmpty()){
                        OutlinedTextField(
                            enabled = false,
                            value = producto_paqueteria[0],
                            onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    alert.value = true
                                },
                            label = { Text("Juego") },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            ),
                            trailingIcon = {
                                if (viewModel.list_familia_paqueteria.isNotEmpty()) {
                                    IconButton(onClick = {
                                        alert.value = true
                                    }) {
                                        Icon(icon2, contentDescription = null)
                                    }
                                }
                            }
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    //--------------------------------------SELECCIONA ZONA--------------------------------------//
                    Text(
                        text = "Zona",
                        fontStyle = FontStyle.Normal,
                        modifier = Modifier.align(Alignment.Start),
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 16.sp
                    )
                    Spacer(Modifier.height(10.dp))
                    Row {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .height(50.dp)
                                .fillMaxWidth(.5f)
                                .background(color = blackdark)
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = CenterVertically
                        ) {
                            Icon(Icons.Filled.SmokingRooms, "Diferencia")
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "FUMAR",
                                fontStyle = FontStyle.Normal,
                                style = MaterialTheme.typography.subtitle2,
                                fontSize = 14.sp
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .align(CenterVertically),
                                checked = zona_fumar.value,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.graydark),
                                    checkmarkColor = colorResource(R.color.white)
                                ),
                                onCheckedChange = {
                                    zona_fumar.value = it
                                    if (it) array_zona.add(Zona("1", "Fumar"))
                                    else array_zona.remove(Zona("1", "Fumar"))
                                }
                            )
                        }
                        Spacer(Modifier.width(5.dp))
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .height(50.dp)
                                .fillMaxWidth()
                                .background(color = blackdark)
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = CenterVertically
                        ) {
                            Icon(Icons.Filled.SmokeFree, "")
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "NO FUMAR",
                                fontStyle = FontStyle.Normal,
                                style = MaterialTheme.typography.subtitle2,
                                fontSize = 14.sp
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .align(CenterVertically),
                                checked = zona_nofumar.value,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.graydark),
                                    checkmarkColor = colorResource(R.color.white)
                                ),
                                onCheckedChange = {
                                    zona_nofumar.value = it
                                    if (it) array_zona.add(Zona("2", "No Fumar"))
                                    else array_zona.remove(Zona("2", "No Fumar"))
                                }
                            )
                        }
                    }
//----------------------------------------SELECCIONA TIRO MINIMO----------------------------------------//
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = tiro_minimo.value,
                            onValueChange = {
                                if (viewModel.getValidationSum(it)) tiro_minimo.value = it
                                else tiro_minimo.value = ""
                            },
                            label = { Text("Tiro Minimo") },
                            modifier = Modifier.fillMaxWidth(.5f),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Right) }
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.MonetizationOn,
                                    contentDescription = "Precio Inicio"
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            )
                        )
                        Spacer(Modifier.width(5.dp))
//----------------------------------------SELECCIONA TIRO MAXIMO----------------------------------------//
                        OutlinedTextField(
                            value = tiro_maximo.value,
                            onValueChange = {
                                if (viewModel.getValidationSum(it)) tiro_maximo.value = it
                                else tiro_maximo.value = ""
                            },
                            label = { Text("Tiro Maximo") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.MonetizationOn,
                                    contentDescription = "Precio Inicio"
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            )
                        )
                    }
//----------------------------------------SELECCIONA TIRO PROMEDIO----------------------------------------//
                    OutlinedTextField(
                        value = tiro_promedio.value,
                        onValueChange = {
                            if (viewModel.getValidationSum(it)) tiro_promedio.value = it
                            else tiro_promedio.value = ""
                        },
                        label = { Text("Tiro Promedio") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.MonetizationOn,
                                contentDescription = "Tiro"
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
//----------------------------------------SELECCIONA APUESTA PROMEDIO----------------------------------------//
                    OutlinedTextField(
                        value = apuestas_promedio.value,
                        onValueChange = { apuestas_promedio.value = it },
                        label = { Text("Apuestas Promedio") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.ConfirmationNumber,
                                contentDescription = "Precio Inicio"
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
//----------------------------------------PROMEDIO OCUPACION----------------------------------------//
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = promedio_ocupacion.value,
                            onValueChange = {
                                if (viewModel.getValidationSum(it)) promedio_ocupacion.value = it
                                else promedio_ocupacion.value = ""
                            },
                            label = { Text("Promedio de Ocupaci??n") },
                            modifier = Modifier.fillMaxWidth(.65f),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus() }
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Timer,
                                    contentDescription = "Precio Inicio"
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            )
                        )
                        Spacer(Modifier.width(5.dp))
//----------------------------------------SELECCIONA FORMATO DE HORA O MIN----------------------------------------//
                        OutlinedTextField(
                            enabled = false,
                            value = unidadOcupacion.value,
                            onValueChange = { },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Ascii,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
                            ),
                            modifier = Modifier
                                .clickable {
                                    formatexpanded.value = !formatexpanded.value
                                }
                                .fillMaxWidth(1f),
                            label = { Text("Hr/Min") },
                            trailingIcon = {
                                Icon(icon, "contentDescription",
                                    Modifier.clickable {
                                        formatexpanded.value = !formatexpanded.value
                                    })
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            )
                        )
                    }
//----------------------------------------SELECCIONA HORA O MINUTO----------------------------------------//
                    DropdownMenu(
                        expanded = formatexpanded.value,
                        onDismissRequest = { formatexpanded.value = false },
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth()
                    ) {
                        format_hr_min.forEach { label ->
                            DropdownMenuItem(onClick = {
                                unidadOcupacion.value = label
                                formatexpanded.value = false
                            }) {
                                Text(text = label)
                            }
                        }
                    }
                    Spacer(Modifier.height(10.dp))
//----------------------------------------SELECCIONA PROGRESIVOS----------------------------------------//
                    Text(
                        text = "Progresivos (SAP, LAP)",
                        fontStyle = FontStyle.Normal,
                        modifier = Modifier.align(Alignment.Start),
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 16.sp
                    )
                    Spacer(Modifier.height(10.dp))
                    Row {
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .height(50.dp)
                                .fillMaxWidth(.5f)
                                .background(color = blackdark)
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = CenterVertically
                        ) {
                            Icon(Icons.Filled.Money, "Diferencia")
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "SAP",
                                fontStyle = FontStyle.Normal,
                                style = MaterialTheme.typography.subtitle2,
                                fontSize = 16.sp
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .align(CenterVertically),
                                checked = sap2.value,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.graydark),
                                    checkmarkColor = colorResource(R.color.white)
                                ),
                                onCheckedChange = {
                                    sap2.value = it
                                    if (it) {
                                        progresivos.add(Progresivos("1", "Sap"))
                                    } else {
                                        progresivos.remove(Progresivos("1", "Sap"))
                                    }
                                }
                            )
                        }
                        Spacer(Modifier.width(5.dp))
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .height(50.dp)
                                .fillMaxWidth()
                                .background(color = blackdark)
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = CenterVertically
                        ) {
                            Icon(Icons.Filled.Money, "Diferencia")
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "LAP",
                                fontStyle = FontStyle.Normal,
                                style = MaterialTheme.typography.subtitle2,
                                fontSize = 16.sp
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .align(CenterVertically),
                                checked = lap2.value,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.graydark),
                                    checkmarkColor = colorResource(R.color.white)
                                ),
                                onCheckedChange = {
                                    lap2.value = it
                                    if (it) {
                                        progresivos.add(Progresivos("2", "Lap"))
                                    } else {
                                        progresivos.remove(Progresivos("2", "Lap"))
                                    }
                                }
                            )
                        }
                    }
//----------------------------------------VALIDACION BOTTON----------------------------------------//
                    Spacer(Modifier.height(10.dp))
                    val isRotated = rememberSaveable { mutableStateOf(false) }
                    val rotationAngle by animateFloatAsState(
                        targetValue = if (isRotated.value) 360F else 0F,
                        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
                    )
                    val isValidate by derivedStateOf {
                        unidadOcupacion.value.isNotBlank()
                                && apuestas_promedio.value.isNotBlank()
                                && proveedor_info[0].isNotBlank()
                                && proveedor_info[1].toInt() > 0
                                && tiro_minimo.value.isNotBlank()
                                && tiro_maximo.value.isNotBlank()
                                && tiro_promedio.value.isNotBlank()
                                && array_zona.isNotEmpty()
                                && promedio_ocupacion.value.isNotBlank()
                                && progresivos.isNotEmpty()
                    }
//----------------------------------------BTN AGREGAR----------------------------------------//
                    Button(
                        enabled = isValidate,
                        onClick = {
                            viewModel.addZitroyComp(
                                unidadOcupacion = unidadOcupacion,
                                apuestas_promedio = apuestas_promedio,
                                producto_paqueteria = producto_paqueteria,
                                proveedor_info = proveedor_info,
                                tiro_minimo = tiro_minimo,
                                tiro_maximo = tiro_maximo,
                                tiro_promedio = tiro_promedio,
                                array_zona = array_zona,
                                isRotated = isRotated,
                                array_mas_jugado = array_mas_jugado,
                                progresivos = progresivos,
                                promedio_ocupacion = promedio_ocupacion,
                                zona_nofumar = zona_nofumar,
                                zona_fumar = zona_fumar,
                                sap2 = sap2,
                                lap2 = lap2
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .graphicsLayer {
                                rotationY = rotationAngle
                                cameraDistance = 8 * density
                            },
                        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
                        shape = RoundedCornerShape(10),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.reds)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    Spacer(Modifier.width(20.dp))
                }
            }
        }
    }
}


@Composable
fun DataItemMasjugado(
    item: MasJugado,
    viewModelNV: PromotorNuevaVisitaViewModel
) {
    var expandcards by remember { mutableStateOf(false) }
    val tiro_min = remember { mutableStateOf(item.tiroMinimo.toString()) }
    val tiro_max = remember { mutableStateOf(item.tiroMaximo.toString()) }
    val tiro_prom = remember { mutableStateOf(item.tiroPromedio.toString()) }
    val apuesta_prom = remember { mutableStateOf(item.apuestasPromedio.toString()) }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp, horizontal = 15.dp)
        .clickable { expandcards = !expandcards }
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                Column {
                    Row {
                        Icon(
                            Icons.Filled.SnippetFolder, "", modifier = Modifier.align(
                                CenterVertically
                            )
                        )
                        Text(
                            text = "PROVEEDOR: ${item.proveedor!!.nombre}",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                        )
                    }
                    Row {
                        OutlinedTextField(
                            value = tiro_min.value,
                            onValueChange = {
                                if (viewModelNV.getValidationSum(it)) {
                                    item.tiroMinimo = it.toInt()
                                    tiro_min.value = item.tiroMinimo.toString()
                                } else {
                                    tiro_min.value = ""
                                    item.tiroMinimo = 0
                                }
                            },
                            label = { Text("Tiro M??nimo") },
                            modifier = Modifier
                                .padding(vertical = 2.5.dp)
                                .fillMaxWidth(.5f),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.MonetizationOn,
                                    contentDescription = null
                                )
                            },
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = tiro_max.value,
                            onValueChange = {
                                if (viewModelNV.getValidationSum(it)) {
                                    item.tiroMaximo = it.toInt()
                                    tiro_max.value = item.tiroMaximo.toString()
                                } else {
                                    tiro_max.value = ""
                                    item.tiroMaximo = 0
                                }
                            },
                            label = { Text("Tiro M??ximo") },
                            modifier = Modifier
                                .padding(vertical = 2.5.dp)
                                .fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.MonetizationOn,
                                    contentDescription = null
                                )
                            },
                            singleLine = true
                        )
                    }
                    Row {
                        OutlinedTextField(
                            value = tiro_prom.value,
                            onValueChange = {
                                if (viewModelNV.getValidationSum(it)) {
                                    item.tiroPromedio = it.toInt()
                                    tiro_prom.value = item.tiroPromedio.toString()
                                } else {
                                    tiro_prom.value = ""
                                    item.tiroPromedio = 0
                                }
                            },
                            label = { Text("Tiro Promedio") },
                            modifier = Modifier
                                .padding(vertical = 2.5.dp)
                                .fillMaxWidth(.5f),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.MonetizationOn,
                                    contentDescription = null
                                )
                            },
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = apuesta_prom.value,
                            onValueChange = {
                                item.apuestasPromedio = it
                                apuesta_prom.value = item.apuestasPromedio.toString()
                            },
                            label = { Text("Apuesta Promedio") },
                            modifier = Modifier
                                .padding(vertical = 2.5.dp)
                                .fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            ),
                            singleLine = true
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(5.dp))
                            .background(graydark)
                            .padding(2.dp)
                    ) {
                        if (expandcards) {
                            Text(
                                text = "Promedio Ocupacion: ${item.promedioOcupacion} min",
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(
                                    horizontal = 5.dp
                                )
                            )
                            Row {
                                if (item.zona.isNotEmpty()) {
                                    Column() {
                                        item.zona.forEach {
                                            Text(
                                                text = "Zona: ${it.zona}",
                                                fontSize = 15.sp,
                                                textAlign = TextAlign.Start,
                                                modifier = Modifier.padding(
                                                    horizontal = 5.dp
                                                )
                                            )
                                        }
                                    }
                                }
                                if (item.progresivos.isNotEmpty()) {
                                    Column() {
                                        item.progresivos.forEach {
                                            Text(
                                                text = "Progresivos: ${it.progresivos}",
                                                fontSize = 15.sp,
                                                textAlign = TextAlign.Start,
                                                modifier = Modifier.padding(
                                                    horizontal = 5.dp
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            IconButton(onClick = {
                viewModelNV.removeMasJugados(item)
            }, modifier = Modifier.size(20.dp)) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "delete"
                )
            }
        }
    }
}

@Composable
fun AlertPaqueteria(
    viewModel: PromotorNuevaVisitaViewModel,
    alert: MutableState<Boolean>,
    proveedor_info: SnapshotStateList<String>,
    tipo: MutableState<Boolean>,
    paqueteria_familia: SnapshotStateList<String>,
) {
    if (alert.value) {
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
                            Icons.Filled.ArrowBack, "Hora", modifier = Modifier
                                .align(CenterVertically)
                                .padding(horizontal = 10.dp)
                                .clickable {
                                    alert.value = false
                                }
                        )
                        Text(
                            text = "SELECCIONA EL JUEGO",
                            modifier = Modifier.align(CenterVertically)
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
                                    alert.value = false
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
