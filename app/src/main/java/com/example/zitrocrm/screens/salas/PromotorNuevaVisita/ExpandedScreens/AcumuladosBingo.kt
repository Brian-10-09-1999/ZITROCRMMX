package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Acumulados
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ProveedorFilter.Rows
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.ui.theme.blackdark
import com.example.zitrocrm.ui.theme.graydark
import java.util.*
import kotlin.collections.ArrayList

@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun AcumuladosBingoCard(
    card4: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    viewModel: PromotorNuevaVisitaViewModel,
    acumulados: ArrayList<Acumulados>
) {
    val inicio = remember { mutableStateOf("") }
    val fin = remember { mutableStateOf("") }
    val diferencia = remember { mutableStateOf("") }
    val evento = remember { mutableStateOf("") }
    val hora_inicio = remember { mutableStateOf("") }
    val hora_fin = remember { mutableStateOf("") }
    val premio = remember { mutableStateOf("") }
    val alert_proveedor = remember { mutableStateOf(false) }
    val proveedor_info = remember { mutableStateListOf("", "0", "0") }
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]
    val context = LocalContext.current
    val mTimePickerDialogInicio = TimePickerDialog(
        context,R.style.DatePickerTheme,
        { _, mHour: Int, mMinute: Int ->
            if (mHour == 0) hora_inicio.value = "24"
            else hora_inicio.value = mHour.toString()
            if (hora_inicio.value.isNotBlank() && hora_fin.value.isNotBlank()) {
                if (hora_fin.value.toInt() < hora_inicio.value.toInt()) {
                    Toast.makeText(
                        context,
                        "La hora seleccionada es incorrecta",
                        Toast.LENGTH_SHORT
                    ).show()
                    hora_inicio.value = ""
                }
            }
        }, mHour, mMinute, true
    )
    val mTimePickerDialogFin = TimePickerDialog(
        context,R.style.DatePickerTheme,
        { _, mHour: Int, mMinute: Int ->
            if (mHour == 0) hora_fin.value = "24"
            else hora_fin.value = mHour.toString()

            if (hora_inicio.value.isNotBlank() && hora_fin.value.isNotBlank()) {
                if (hora_fin.value.toInt() < hora_inicio.value.toInt()) {
                    Toast.makeText(
                        context,
                        "La hora seleccionada es incorrecta",
                        Toast.LENGTH_SHORT
                    ).show()
                    hora_fin.value = ""
                }
            }
        }, mHour, mMinute, true
    )
    val icon =
        if (alert_proveedor.value)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown
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
                            painter = painterResource(id = R.drawable.bingo),
                            contentDescription = "IconList",
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = card4,
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
            AcumuladosBingoExpand(
                expanded = expanded,
                viewModel = viewModel,
                acumulados = acumulados,
                inicio = inicio,
                fin = fin,
                diferencia = diferencia,
                evento = evento,
                hora_inicio = hora_inicio,
                hora_fin = hora_fin,
                premio = premio,
                alert_proveedor = alert_proveedor,
                proveedor_info = proveedor_info,
                mTimePickerDialogInicio = mTimePickerDialogInicio,
                mTimePickerDialogFin = mTimePickerDialogFin,
                icon = icon,
                focusManager = focusManager
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun AcumuladosBingoExpand(
    expanded: Boolean = true,
    viewModel: PromotorNuevaVisitaViewModel,
    acumulados: ArrayList<Acumulados>,
    inicio: MutableState<String>,
    fin: MutableState<String>,
    diferencia: MutableState<String>,
    evento: MutableState<String>,
    hora_inicio: MutableState<String>,
    hora_fin: MutableState<String>,
    premio: MutableState<String>,
    alert_proveedor: MutableState<Boolean>,
    proveedor_info: SnapshotStateList<String>,
    mTimePickerDialogInicio: TimePickerDialog,
    mTimePickerDialogFin: TimePickerDialog,
    icon: ImageVector,
    focusManager: FocusManager
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
                .padding(15.dp)
        ) {
//---------------------------------------------SELECCIONA PROVEEDOR-----------------------------------------//
            OutlinedTextField(
                enabled = false,
                value = proveedor_info[0],
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp)
                    .clickable {
                        alert_proveedor.value = true
                    },
                label = {
                    if (proveedor_info[0].isBlank()) {
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp)
            ) {
//---------------------------------------------INICIO DEL ACUMULADO-----------------------------------------//
                OutlinedTextField(
                    value = inicio.value,
                    onValueChange = {
                        if (viewModel.getValidationSum(it)) {
                            inicio.value = it
                            if (viewModel.getValidationSum(it) && viewModel.getValidationSum(fin.value)) {
                                val dif = (fin.value.toInt() - inicio.value.toInt()).toString()
                                diferencia.value = dif
                            }
                        } else {
                            inicio.value = ""
                        }
                    },
                    label = { Text("Inicio") },
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
//---------------------------------------------FIN DEL ACUMULADO-----------------------------------------//
                OutlinedTextField(
                    value = fin.value,
                    onValueChange = {
                        if (viewModel.getValidationSum(it)) {
                            fin.value = it
                            if (viewModel.getValidationSum(it) && viewModel.getValidationSum(inicio.value)) {
                                val dif = (fin.value.toInt() - inicio.value.toInt()).toString()
                                diferencia.value = dif
                            }
                        } else {
                            fin.value = ""
                        }
                    },
                    label = { Text("Fin") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) },
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
//---------------------------------------------DIFERENCIA DEL INICIO Y FIN-----------------------------------------//
            OutlinedTextField(
                enabled = false,
                value = diferencia.value,
                onValueChange = {},
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .fillMaxWidth(),
                label = { Text("Diferencia") },
                leadingIcon = {
                    Icon(Icons.Filled.MonetizationOn, "contentDescription")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White
                )
            )
//---------------------------------------------AGREGA EVENTO-----------------------------------------//
            OutlinedTextField(
                value = evento.value,
                onValueChange = { evento.value = it },
                label = { Text("Evento") },
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Event,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White
                )
            )
//-------------------------------------------SELECCIONA HORA INICIO------------------------------------------//
            Row(modifier = Modifier.padding(vertical = 1.dp)) {
                OutlinedTextField(
                    enabled = false,
                    value = hora_inicio.value,
                    onValueChange = {},
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                        .fillMaxWidth(.49f)
                        .clickable {
                            mTimePickerDialogInicio.show()
                        },
                    label = { Text("Hora inicio") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Timer,
                            "contentDescription"
                        )
                    },
                    trailingIcon = {
                        if (hora_inicio.value.isNotBlank()) {
                            Text("Hrs")
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White
                    ),
                )
//-------------------------------------------SELECCIONA HORA FIN------------------------------------------//
                OutlinedTextField(
                    enabled = false,
                    value = hora_fin.value,
                    onValueChange = {},
                    modifier = Modifier
                        .padding(horizontal = 1.dp)
                        .fillMaxWidth()
                        .clickable {
                            mTimePickerDialogFin.show()
                        },
                    label = { Text("Hora fin") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Timer,
                            "contentDescription"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White
                    ),
                    trailingIcon = {
                        if (hora_fin.value.isNotBlank()) {
                            Text("Hrs")
                        }
                    }
                )
            }
//-------------------------------------------AGREGA PREMIO DEL ACUMULADO------------------------------------------//
            OutlinedTextField(
                value = premio.value,
                onValueChange = { premio.value = it },
                label = { Text("Premio") },
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() },
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.WorkspacePremium,
                        contentDescription = "Precio Inicio"
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White
                ),
            )
//-----------------------------------------VALIDACIONES DEL BOTTON------------------------------------------//
            val isValidate by derivedStateOf {
                proveedor_info[0].isNotBlank()
                        && proveedor_info[1].toInt() > 0
                        && inicio.value.isNotBlank()
                        && fin.value.isNotBlank()
            }
            val isValidate2 by derivedStateOf {
                evento.value.isNotBlank()
                        && hora_inicio.value.isNotBlank()
                        && hora_fin.value.isNotBlank()
                        && premio.value.isNotBlank()
                        && proveedor_info[0].isNotBlank()
                        && proveedor_info[1].toInt() > 0
                        && inicio.value.isNotBlank()
                        && fin.value.isNotBlank()
            }
            val isValidate3 by derivedStateOf {
                evento.value.isNotBlank()
                        || hora_inicio.value.isNotBlank()
                        || hora_fin.value.isNotBlank()
                        || premio.value.isNotBlank()
            }
            Spacer(Modifier.height(10.dp))
            val isRotated = rememberSaveable { mutableStateOf(false) }
            val rotationAngle by animateFloatAsState(
                targetValue = if (isRotated.value) 360F else 0F,
                animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
            )
//-----------------------------------------BTN AGREGAR ACUMULADO------------------------------------------//
            Button(
                enabled = if (isValidate3) isValidate2 else isValidate,
                onClick = {
                    viewModel.addAcumulados(
                        proveedor_info = proveedor_info,
                        inicio = inicio,
                        fin = fin,
                        evento = evento,
                        hora_inicio = hora_inicio,
                        hora_fin = hora_fin,
                        premio = premio,
                        acumulados = acumulados,
                        isRotated = isRotated,
                        diferencia = diferencia,
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
                    contentDescription = "Precio Inicio",
                    tint = Color.White
                )
            }
            AlertProveedorSeleccionado(
                list_proveedor = viewModel.proveedores_selections,
                alert_proveedor = alert_proveedor,
                proveedor_info = proveedor_info,
                onclick = {},
                competencia = false
            )
        }
    }
}


@Composable
fun DataItemAcumulado(
    item: Acumulados,
    index: Int,
    viewModelNV: PromotorNuevaVisitaViewModel
) {
    var expandcards by remember { mutableStateOf(false) }
    val acum_inicio = remember { mutableStateOf(item.inicio.toString()) }
    val acum_fin = remember { mutableStateOf(item.fin.toString()) }
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
                        Icon(Icons.Filled.SnippetFolder, "Diferencia", modifier = Modifier.align(CenterVertically))
                        Text(
                            text = "PROVEEDOR: ${item.proveedor!!.nombre}",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)
                        )
                    }
                    Row {
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
                    }
                }
            }
            IconButton(onClick = {
                viewModelNV.removeAcumulados(item)
            },modifier = Modifier.size(20.dp)) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "delete"
                )
            }
        }
    }
}

@Composable
fun AlertProveedorSeleccionado(
    list_proveedor: MutableList<Rows>,
    alert_proveedor: MutableState<Boolean>,
    proveedor_info: SnapshotStateList<String>,
    onclick : () ->Unit,
    competencia : Boolean
) {
    if (alert_proveedor.value) {
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
                                    alert_proveedor.value = false
                                }
                        )
                        Text(
                            text = "SELECCIONA EL PROVEEDOR",
                            modifier = Modifier.align(CenterVertically)
                        )
                    }
                }
                if (list_proveedor.isNotEmpty()) {
                    LazyColumn {
                        itemsIndexed(list_proveedor) { index, label ->
                            if(competencia&&label.id==24){
                            }else{
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        proveedor_info[0] = label.nombre.toString()
                                        proveedor_info[1] = label.id.toString()
                                        proveedor_info[2] = label.tipo.toString()
                                        onclick.invoke()
                                        alert_proveedor.value = false
                                    }
                                    .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                    Text(text = label.nombre.toString())
                                }
                            }
                        }
                    }
                } else {
                    Text(
                        text = "No has seleccionado proveedores",
                        modifier = Modifier.padding(10.dp)
                    )
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






















