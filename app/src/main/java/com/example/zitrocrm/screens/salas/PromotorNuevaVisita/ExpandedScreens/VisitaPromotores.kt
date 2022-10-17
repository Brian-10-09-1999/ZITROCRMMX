package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Fecha
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Visita
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ObjSemanalFilter.Message
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.ui.theme.blackdark
import java.util.*


@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun VisitaPromotoresCard(
    card: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    viewModelPromotorNuevaVisita: PromotorNuevaVisitaViewModel,
    visita: Visita,
    context: Context,
    objetivoSemJuego: SnapshotStateList<Message>,
) {
    Column(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(blackdark)
            .fillMaxWidth()
            .size(80.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painter = painterResource(R.drawable.reporte_visitas_icon),
            contentDescription = "",
            modifier = Modifier
                .padding(0.dp)
                .size(50.dp)
        )
        Text(
            text = "Nueva Visita",
            style = MaterialTheme.typography.subtitle2,
            fontSize = 16.sp
        )
    }
    Card(
        backgroundColor = blackdark,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 4.dp
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
                            painter = painterResource(id = R.drawable.reporte_visitas_icon),
                            contentDescription = "IconList",
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = card,
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
            VisitaPromotoresExpand(
                expanded = expanded,
                viewModelNV = viewModelPromotorNuevaVisita,
                visita = visita,
                context = context,
                objetivoSemJuego = objetivoSemJuego
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun VisitaPromotoresExpand(
    expanded: Boolean = true,
    viewModelNV: PromotorNuevaVisitaViewModel,
    visita: Visita,
    context: Context,
    objetivoSemJuego: SnapshotStateList<Message>,
) {
    val expand = remember { mutableStateOf(0) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown
    val fecha = viewModelNV.fecha
    val horaEntrada = viewModelNV.hora_entrada
    val horasalida = viewModelNV.hora_salida
    //--------------------------DATE-TIMEPICKER-----------------------------//
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar.get(Calendar.HOUR_OF_DAY)
    val mMinute = mCalendar.get(Calendar.HOUR_OF_DAY)
    mCalendar.time = Date()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    val mDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            visita.fecha = Fecha(year = mYear, month = mMonth + 1, day = mDayOfMonth)
            fecha.value = "${mDayOfMonth}-${mMonth}-${mYear}"
        }, mYear, mMonth, mDay
    )
    val mTimePickerDialogEntrada = TimePickerDialog(
        context,
        { _, mHour: Int, mMinute: Int ->
            if (mHour== 0) visita.horaEntrada = 24
            else visita.horaEntrada = mHour
            horaEntrada.value = visita.horaEntrada.toString()

            if(horaEntrada.value.isNotBlank()&&horasalida.value.isNotBlank()){
                if(horasalida.value.toInt()<horaEntrada.value.toInt()){
                    Toast.makeText(context, "La hora seleccionada es incorrecta", Toast.LENGTH_SHORT).show()
                    horaEntrada.value = ""
                }else{
                    viewModelNV.iniciofin(
                        inicio = visita.horaEntrada!!.toInt(),
                        fin = visita.horaSalida!!.toInt(),
                    )
                }
            }

        }, mHour, mMinute, true
    )
    val mTimePickerDialogSalida = TimePickerDialog (
        context,
        { _, mHour: Int, mMinute: Int ->
            if (mHour == 0)  visita.horaSalida = 24
            else visita.horaSalida = mHour
            horasalida.value = visita.horaSalida.toString()
            if(horaEntrada.value.isNotBlank()&&horasalida.value.isNotBlank()){
                if(horasalida.value.toInt()<horaEntrada.value.toInt()){
                    Toast.makeText(context, "La hora seleccionada es incorrecta", Toast.LENGTH_SHORT).show()
                    horasalida.value = ""
                }else{
                    viewModelNV.iniciofin(
                        inicio = visita.horaEntrada!!.toInt(),
                        fin = visita.horaSalida!!.toInt(),
                    )
                }
            }
        }, mHour, mMinute, true
    )
    //---------------------------------------------------------------------//
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
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    OutlinedTextField(
                        enabled = false,
                        value = fecha.value,
                        onValueChange = {},
                        modifier = Modifier
                            .padding(vertical = 2.5.dp)
                            .fillMaxWidth()
                            .clickable {
                                mDatePickerDialog.show()
                            },
                        label = {
                            if (fecha.value.isBlank()) {
                                Text("Selecciona Fecha de Visita")
                            } else {
                                Text("Fecha de Visita")
                            }
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.DateRange,
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
                        ),
                    )
                    Row {
                        OutlinedTextField(
                            enabled = false,
                            value = horaEntrada.value,
                            onValueChange = {},
                            modifier = Modifier
                                .padding(vertical = 2.5.dp, horizontal = 1.dp)
                                .fillMaxWidth(.49f)
                                .clickable {
                                    mTimePickerDialogEntrada.show()
                                },
                            label = { Text("Hora de Entrada") },
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
                            ),
                            trailingIcon = {
                                if (horaEntrada.value.isNotBlank()) {
                                    Text("Hrs")
                                }
                            }
                        )
                        OutlinedTextField(
                            enabled = false,
                            value = horasalida.value,
                            onValueChange = {},
                            modifier = Modifier
                                .padding(vertical = 2.5.dp, horizontal = 1.dp)
                                .fillMaxWidth()
                                .clickable {
                                    mTimePickerDialogSalida.show()
                                },
                            label = { Text("Hora de Salida") },
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
                            ),
                            trailingIcon = {
                                if (horasalida.value.isNotBlank()) {
                                    Text("Hrs")
                                }
                            }
                        )
                    }
                    if(viewModelNV.tipo.value==false){
                        OutlinedTextField(
                            enabled = false,
                            value = viewModelNV.getObjetivoString(),//objetivoSemanaal.value,
                            onValueChange = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.5.dp)
                                .clickable {
                                    expand.value = 2
                                },
                            label = { Text("Libreria") },
                            trailingIcon = {
                                Icon(icon, "contentDescription")
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White
                            )
                        )
                    }

                    OutlinedTextField(
                        enabled = false,
                        value = viewModelNV.getObjetSelect(),//objetivoSemanaal.value,
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.5.dp)
                            .clickable {
                                expand.value = 1
                            },
                        label = { Text("Objetivo Semanal") },
                        trailingIcon = {
                            Icon(icon, "contentDescription")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    AlertObjetivoSemanalMenu(
                        viewModel = viewModelNV,
                        openclose = expand,
                        objetivoSemJuego = objetivoSemJuego
                    )
                }
            }
        }
    }
}

@Composable
fun AlertObjetivoSemanalMenu(
    viewModel: PromotorNuevaVisitaViewModel,
    openclose: MutableState<Int>,
    objetivoSemJuego: SnapshotStateList<Message>
) {
    val objetivoSemanal = if(viewModel.tipo.value)viewModel.objetivoSemanal else viewModel.objetivoSemanalFilter
    if (openclose.value==1) {
        AlertDialog(
            onDismissRequest = {
                openclose.value = 0
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
                                .align(CenterVertically)
                                .padding(horizontal = 10.dp)
                                .clickable {
                                    openclose.value = 0
                                }
                        )
                        Text(
                            text = "SELECCIONA OBJETIVO SEMANAL",
                            modifier = Modifier.align(CenterVertically)
                        )
                    }
                }
                if (objetivoSemanal.isNotEmpty()) {
                    LazyColumn {
                        itemsIndexed(objetivoSemanal) { index, items ->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Row{
                                    val checkbox = rememberSaveable{ mutableStateOf(items.check) }
                                    Checkbox(
                                        checked = checkbox.value!! ,
                                        onCheckedChange = {
                                            items.check = it
                                            checkbox.value = items.check
                                            //viewModel.objetivos_seleccionados(items)
                                            viewModel.a()
                                        }
                                    )
                                    Text(text = items.objetivo.toString(),modifier=Modifier.align(CenterVertically))
                                }
                            }
                        }
                    }
                } else {
                    Text(
                        text = "Comprueba la conexion a internet",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            ),
            modifier = Modifier,
            shape = RoundedCornerShape(18.dp)
        )
    }
    if (openclose.value==2) {
        AlertDialog(
            onDismissRequest = {
                openclose.value = 0
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
                                .align(CenterVertically)
                                .padding(horizontal = 10.dp)
                                .clickable {
                                    openclose.value = 0
                                }
                        )
                        Text(
                            text = "SELECCIONA LIBRERIA",
                            modifier = Modifier.align(CenterVertically)
                        )
                    }
                }
                if (objetivoSemJuego.isNotEmpty()) {
                    LazyColumn {
                        itemsIndexed(objetivoSemJuego) { index, items ->
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 10.dp)) {
                                Row{
                                    val checkbox = rememberSaveable {
                                        mutableStateOf(items.check)
                                    }
                                    Checkbox(
                                        checked = checkbox.value!! ,
                                        onCheckedChange = {
                                            items.check = it
                                            checkbox.value = items.check
                                            viewModel.check_bingo(
                                                items = items,
                                            )
                                        }
                                    )
                                    Text(text = items.objetivo.toString(),modifier=Modifier.align(CenterVertically))
                                }
                            }
                        }
                    }
                } else {
                    Text(
                        text = "Comprueba la conexion a internet",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false
            ),
            modifier = Modifier,
            shape = RoundedCornerShape(18.dp)
        )
    }
}
