package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.MasJugado
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ProveedorFilter.Rows
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.components.alertJuegosFilter
import com.example.zitrocrm.ui.theme.blackdark


@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun JugadoZitroCompetencia(
    card5: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    viewModelNV: PromotorNuevaVisitaViewModel,
    list_mas_jugado: ArrayList<MasJugado>,
) {
    val alert_proveedor = remember { mutableStateOf(false) }
    val proveedor_info = remember { mutableStateListOf("", "0", "0") }
    val proveedor = remember{
        mutableStateOf(Rows(id=0,nombre="",tipo = 0))
    }
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
                            .align(Alignment.CenterVertically)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.nav_maquinas_icon),
                            contentDescription = "IconList",
                            modifier = Modifier.padding(start = 10.dp, top = 2.dp, bottom = 2.dp)
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
                            .align(Alignment.CenterVertically)
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
                proveedor_info = proveedor,
                list_mas_jugado = list_mas_jugado
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalAnimationApi
@Composable
fun JugadoZitroCompetenciaExpand(
    expanded: Boolean = true,
    viewModel: PromotorNuevaVisitaViewModel,
    alert_proveedor: MutableState<Boolean>,
    proveedor_info: MutableState<Rows>,
    list_mas_jugado: ArrayList<MasJugado>,
) {
    AlertProveedorSeleccionado(
        list_proveedor = viewModel.proveedores_selections,
        alert_proveedor = alert_proveedor,
        proveedor_info = proveedor_info
    )
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
                    val icon = if (alert_proveedor.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
                    val focusManager = LocalFocusManager.current
                    val keyboardController = LocalSoftwareKeyboardController.current
                    OutlinedTextField(
                        enabled = false,
                        value = proveedor_info.value.nombre.toString(),
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                alert_proveedor.value = true
                            },
                        label = {
                            if (proveedor_info.value.nombre.toString().isBlank()) {
                                Text("Selecciona el Proveedor")
                            } else {
                                Text("Proveedor")
                            }
                        },
                        trailingIcon = {
                            Icon(icon, "contentDescription")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    val producto = remember {
                        mutableStateListOf<String>("","0")
                    }
                    if (proveedor_info.value.id!!.toInt() == 24) {
                        OutlinedTextField(
                            enabled = false,
                            value = viewModel.producto_mas_jugado.value,
                            onValueChange = { },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Ascii,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { keyboardController?.hide() }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    //navController.navigate(route = Destination.Dialog.route)
                                    alertJuegosFilter.value = true
                                },
                            label = { Text("Producto") },
                            trailingIcon = {
                                Icon(icon, "contentDescription",
                                    Modifier.clickable {
                                        //navController.navigate(route = Destination.Dialog.route)
                                        alertJuegosFilter.value = true
                                    }
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            )
                        )
                    } else {
                        OutlinedTextField(
                            value = viewModel.producto_mas_jugado.value,
                            onValueChange = { viewModel.producto_mas_jugado.value = it },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Ascii,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
                            ),
                            modifier = Modifier
                                .fillMaxWidth(),
                            label = { Text("Producto") },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            )
                        )
                    }

                    Spacer(Modifier.height(10.dp))
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
                                .clip(RoundedCornerShape(10.dp))
                                .height(50.dp)
                                .fillMaxWidth(.5f)
                                .background(color = blackdark)
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Filled.SmokingRooms, "Diferencia")
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "FUMAR",
                                fontStyle = FontStyle.Normal,
                                style = MaterialTheme.typography.subtitle2,
                                fontSize = 16.sp
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .align(Alignment.CenterVertically),
                                checked = viewModel.fumar2.value,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.graydark),
                                    checkmarkColor = colorResource(R.color.white)
                                ),
                                onCheckedChange = {
                                    viewModel.fumar2.value = it
                                    viewModel.checksZonaId()
                                }
                            )
                        }
                        Spacer(Modifier.width(5.dp))
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .height(50.dp)
                                .fillMaxWidth()
                                .background(color = blackdark)
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Filled.SmokeFree, "Diferencia")
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "NO FUMAR",
                                fontStyle = FontStyle.Normal,
                                style = MaterialTheme.typography.subtitle2,
                                fontSize = 16.sp
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .align(Alignment.CenterVertically),
                                checked = viewModel.nofumar2.value,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.graydark),
                                    checkmarkColor = colorResource(R.color.white)
                                ),
                                onCheckedChange = {
                                    viewModel.nofumar2.value = it
                                    viewModel.checksZonaId()
                                }
                            )
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = viewModel.tiro_minimo.value,
                            onValueChange = { viewModel.tiro_minimo.value = it },
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
                        OutlinedTextField(
                            value = viewModel.tiro_maximo.value,
                            onValueChange = { viewModel.tiro_maximo.value = it },
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
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModel.tiro_promedio.value,
                        onValueChange = { viewModel.tiro_promedio.value = it },
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
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModel.apuestas_promedio.value,
                        onValueChange = { viewModel.apuestas_promedio.value = it },
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
                    Spacer(Modifier.height(10.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = viewModel.promedio_ocupacion.value,
                            onValueChange = {
                                viewModel.promedio_ocupacion.value = it
                            },
                            label = { Text("Promedio de Ocupación") },
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
                        var formatexpanded by remember { mutableStateOf(false) }
                        val format_hr_min = listOf("Hr", "Min");
                        OutlinedTextField(
                            enabled = false,
                            value = viewModel.unidadOcupacion.value,
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
                                    formatexpanded = !formatexpanded
                                }
                                .fillMaxWidth(1f),
                            label = { Text("Hr/Min") },
                            trailingIcon = {
                                Icon(icon, "contentDescription",
                                    Modifier.clickable { formatexpanded = !formatexpanded })
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            )
                        )
                        /*DropdownMenu(
                            expanded = formatexpanded,
                            onDismissRequest = { formatexpanded = false },
                            modifier = Modifier
                                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                        ) {
                            format_hr_min.forEach { label ->
                                DropdownMenuItem(onClick = {
                                    viewModel.unidadOcupacion.value = label
                                    formatexpanded = false
                                }) {
                                    Text(text = label)
                                }
                            }
                        }*/
                    }
                    Spacer(Modifier.height(10.dp))
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
                                .clip(RoundedCornerShape(10.dp))
                                .height(50.dp)
                                .fillMaxWidth(.5f)
                                .background(color = blackdark)
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
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
                                    .align(Alignment.CenterVertically),
                                checked = viewModel.sap2.value,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.graydark),
                                    checkmarkColor = colorResource(R.color.white)
                                ),
                                onCheckedChange = {
                                    viewModel.sap2.value = it
                                    viewModel.checksZonaId()
                                }
                            )
                        }
                        Spacer(Modifier.width(5.dp))
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .height(50.dp)
                                .fillMaxWidth()
                                .background(color = blackdark)
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
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
                                    .align(Alignment.CenterVertically),
                                checked = viewModel.lap2.value,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.graydark),
                                    checkmarkColor = colorResource(R.color.white)
                                ),
                                onCheckedChange = {
                                    viewModel.lap2.value = it
                                    viewModel.checksZonaId()
                                }
                            )
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    val isValidate by derivedStateOf {
                        viewModel.proveedor_lo_mas_jugado.value.isNotBlank()
                                && viewModel.tiro_minimo.value.isNotBlank()
                                && viewModel.tiro_maximo.value.isNotBlank()
                                && viewModel.tiro_promedio.value.isNotBlank()
                                && viewModel.apuestas_promedio.value.isNotBlank()
                                && viewModel.promedio_ocupacion.value.isNotBlank()
                                && viewModel.unidadOcupacion.value.isNotBlank()
                    }
                    if (isValidate == false) {
                        Text(
                            text = "¡ Hay campos vacios !",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    val isRotated = rememberSaveable { mutableStateOf(false) }
                    val rotationAngle by animateFloatAsState(
                        targetValue = if (isRotated.value) 360F else 0F,
                        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)

                    )
                    Button(
                        enabled = isValidate,
                        onClick = {
                            viewModel.addLoMasJugadoZitroComp()
                            isRotated.value = !isRotated.value
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
                            contentDescription = "Precio Inicio",
                            tint = Color.White
                        )
                    }
                    Spacer(Modifier.width(20.dp))

                    viewModel.dataLoMasJugadoZitroZomp.forEach { item ->
                        dataItemMasjugado(item)
                        var expandcards by remember { mutableStateOf(false) }
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .clickable { expandcards = !expandcards }
                        ) {
                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Filled.SnippetFolder, "Diferencia")
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 20.dp)
                                ) {
                                    Column() {
                                        Text(
                                            text = "Provedor: ${item.proveedor!!.nombre} \nProducto: ${item.juego!!.nombre}",
                                            fontSize = 15.sp,
                                            textAlign = TextAlign.Start,
                                            modifier = Modifier.padding(
                                                vertical = 15.dp,
                                                horizontal = 5.dp
                                            )
                                        )
                                        if (expandcards) {
                                            Text(
                                                text = "Tiro Minimo $: ${item.tiroMinimo} \nTiro Maximo $: ${item.tiroMaximo} \nTiro Promedio \$: ${item.tiroPromedio}\n Apuesta Prom $: ${item.apuestasPromedio} ",
                                                fontSize = 15.sp,
                                                textAlign = TextAlign.Start,
                                                modifier = Modifier.padding(
                                                    start = 5.dp,
                                                    end = 5.dp,
                                                    bottom = 15.dp
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
        }
    }
}

@Composable
fun dataItemMasjugado(
    item: MasJugado
) {
    var expandcards by remember{
        mutableStateOf(false)
    }
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                Column {
                    Row {
                        Icon(Icons.Filled.SnippetFolder, "Diferencia", modifier = Modifier.align(
                            Alignment.CenterVertically
                        ))
                        Text(
                            text = "PROVEEDOR: ${item.proveedor!!.nombre}",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)
                        )
                    }
                    Text(
                        text = "Tiro Minimo $: ${item.tiroMinimo} \nTiro Maximo $: ${item.tiroMaximo} \nTiro Promedio \$: ${item.tiroPromedio}\n Apuesta Prom $: ${item.apuestasPromedio} ",
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 15.dp
                        )
                    )
                    /*Row {
                        OutlinedTextField(
                            value = acum_inicio.value,
                            onValueChange = {
                                if (viewModelNV.getValidationSum(it)) {
                                    item.inicio = it.toInt()
                                    acum_inicio.value = item.inicio.toString()
                                } else {
                                    acum_inicio.value = ""
                                    item.inicio = 0
                                }
                            },
                            label = { Text("Inicio") },
                            modifier = Modifier
                                .padding(vertical = 2.5.dp)
                                .fillMaxWidth(.5f),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            ),
                        )
                        OutlinedTextField(
                            value = acum_fin.value,
                            onValueChange = {
                                if (viewModelNV.getValidationSum(it)) {
                                    item.fin = it.toInt()
                                    acum_fin.value = item.fin.toString()
                                } else {
                                    acum_fin.value = ""
                                    item.fin = 0
                                }
                            },
                            label = { Text("Fin") },
                            modifier = Modifier
                                .padding(vertical = 2.5.dp)
                                .fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            ),
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
                        if (expandcards && item.evento != null && item.horaInicio != null && item.premio != null) {
                            Text(
                                text = "Evento: ${item.evento}",
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            )
                            Text(
                                text = "Inicio:  ${item.horaInicio}:00 hrs    Fin:  ${item.horaFin}:00 hrs",
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(5.dp)
                            )
                            Text(
                                text = "Premio:  ${item.premio} ",
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(5.dp)
                            )
                        } else if (expandcards) {
                            Text(
                                text = "No asignaste ningún evento para este proveedor.",
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            )
                        }
                    }*/
                }
            }
            IconButton(onClick = {
               //viewModelNV.removeAcumulados(item)
            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "delete"
                )
            }
        }
    }
}

