package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.annotation.SuppressLint
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Comentarios
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Proveedor
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.ui.theme.blackdark
import com.example.zitrocrm.ui.theme.graydark

@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ComentariosGeneralesJugadores(
    card6: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    viewModel: PromotorNuevaVisitaViewModel,
    coment_generales: ArrayList<Comentarios>
) {
    val tipo = remember { mutableStateOf(true) }
    val paqueteria_familia = remember { mutableStateListOf("", "0") }
    val sub_juego = remember { mutableStateListOf("", "0") }
    val perfil_selec = remember { mutableStateListOf("", "0") }
    val procedencia_comentarios = remember { mutableStateOf("") }
    val ingresos_comentarios = remember { mutableStateOf("") }
    val comentarios_jugadores = remember { mutableStateOf("") }
    val alert = remember { mutableStateOf(0) }
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
                            painter = painterResource(id = R.drawable.comentarios),
                            contentDescription = "IconList",
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = card6,
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
            ComentariosGeneralesJugadoresExpand(
                expanded = expanded,
                viewModel = viewModel,
                coment_generales = coment_generales,
                tipo = tipo,
                paqueteria_familia = paqueteria_familia,
                perfil_selec = perfil_selec,
                procedencia = procedencia_comentarios,
                ingresos = ingresos_comentarios,
                comentarios_jugadores = comentarios_jugadores,
                alert = alert,
                sub_juego = sub_juego
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ComentariosGeneralesJugadoresExpand(
    expanded: Boolean = true,
    viewModel: PromotorNuevaVisitaViewModel,
    coment_generales: ArrayList<Comentarios>,
    tipo: MutableState<Boolean>,
    paqueteria_familia: SnapshotStateList<String>,
    perfil_selec: SnapshotStateList<String>,
    procedencia: MutableState<String>,
    ingresos: MutableState<String>,
    comentarios_jugadores: MutableState<String>,
    alert: MutableState<Int>,
    sub_juego: SnapshotStateList<String>
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
                    val perfilexpanded = remember { mutableStateOf(false) }
                    val perfil = listOf(
                        Proveedor(id = 1, nombre = "Alto"),
                        Proveedor(id = 2, nombre = "Medio"),
                        Proveedor(id = 3, nombre = "Bajo")
                    )
                    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
                    val focusManager = LocalFocusManager.current
                    //-----------------------------------------------------------------------------//
                    AlertJuegosComent(
                        viewModel = viewModel,
                        paqueteria_familia = paqueteria_familia,
                        alert = alert,
                        sub_juego = sub_juego
                    )
                    //-----------------------------------------------------------------------------//
                    Text(
                        text = "Calificacion",
                        fontStyle = FontStyle.Normal,
                        modifier = Modifier.align(Alignment.Start),
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 16.sp
                    )
                    Spacer(Modifier.height(10.dp))
                    //-----------------CHECKS POSITIVO Y NEGATIVO------------------------------------//
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
                            Icon(Icons.Filled.CheckCircle, "Diferencia")
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Positivo",
                                fontStyle = FontStyle.Normal,
                                style = MaterialTheme.typography.subtitle2,
                                fontSize = 16.sp
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .align(Alignment.CenterVertically),
                                checked = tipo.value,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.graydark),
                                    checkmarkColor = colorResource(R.color.white)
                                ),
                                onCheckedChange = {
                                    if (it) tipo.value = true
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
                            Icon(Icons.Filled.CheckCircleOutline, "Diferencia")
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Negativo",
                                fontStyle = FontStyle.Normal,
                                style = MaterialTheme.typography.subtitle2,
                                fontSize = 16.sp
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .align(Alignment.CenterVertically),
                                checked = tipo.value == false,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.graydark),
                                    checkmarkColor = colorResource(R.color.white)
                                ),
                                onCheckedChange = {
                                    if (it) tipo.value = false
                                }
                            )
                        }
                    }
                    //-----------------------------SELECT JUEGO----------------------------------//
                    OutlinedTextField(
                        enabled = false,
                        value = paqueteria_familia[0],
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                alert.value = 1
                            },
                        label = { Text("Juegos") },
                        trailingIcon = {
                            Icon(icon, "contentDescription")
                        }, colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    //----------------------------SELECT SUB JUEGO------------------------------//
                    OutlinedTextField(
                        enabled = false,
                        value = sub_juego[0],
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                alert.value = 2
                            },
                        label = { Text("Sub Juegos") },
                        trailingIcon = {
                            Icon(icon, "contentDescription")
                        }, colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    //----------------------------SELECT PERFIL----------------------------------//
                    OutlinedTextField(
                        enabled = false,
                        value = perfil_selec[0],
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                perfilexpanded.value = true
                            },
                        label = { Text("Perfil") },
                        trailingIcon = {
                            Icon(icon, "contentDescription",
                                Modifier.clickable { perfilexpanded.value = true })
                        }, colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    //---------------------------ALERT SELECT PERFIL -----------------------------//
                    AlertJuegosPerfil(
                        alert = perfilexpanded,
                        perfil = perfil,
                        perfil_selec = perfil_selec
                    )
                    //---------------------------PROCENDENCIA INPUT-------------------------------//
                    OutlinedTextField(
                        value = procedencia.value,
                        onValueChange = {
                            procedencia.value = it
                        },
                        label = { Text("Procedencia") },
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
                                imageVector = Icons.Filled.FilePresent,
                                contentDescription = "Procedencia"
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    OutlinedTextField(
                        value = ingresos.value,
                        onValueChange = {
                            if (viewModel.getValidationSum(it)) ingresos.value = it
                            else ingresos.value = ""
                        },
                        label = { Text("Ingresos") },
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
                    OutlinedTextField(
                        value = comentarios_jugadores.value,
                        onValueChange = {
                            comentarios_jugadores.value = it
                        },
                        label = { Text("Comentarios") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Comment,
                                contentDescription = "Comentarios"
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    Spacer(Modifier.height(10.dp))
                    val isValidate by derivedStateOf {
                        paqueteria_familia[1].toInt() > 0
                                && paqueteria_familia[0].isNotBlank()
                                && perfil_selec[1].toInt() > 0
                                && perfil_selec[0].isNotBlank()
                                && sub_juego[0].isNotBlank()
                                && sub_juego[1].toInt() > 0
                                && procedencia.value.isNotBlank()
                                && ingresos.value.isNotBlank()
                                && comentarios_jugadores.value.isNotBlank()
                    }
                    val isRotated = rememberSaveable { mutableStateOf(false) }
                    val rotationAngle by animateFloatAsState(
                        targetValue = if (isRotated.value) 360F else 0F,
                        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)

                    )
                    Button(
                        enabled = isValidate,
                        onClick = {
                            viewModel.AddComentariosGenerales(
                                paqueteria_familia = paqueteria_familia,
                                perfil_selec = perfil_selec,
                                procedencia = procedencia,
                                ingresos = ingresos,
                                comentarios_jugadores = comentarios_jugadores,
                                tipo = tipo,
                                isRotated = isRotated,
                                coment_generales = coment_generales,
                                sub_juego = sub_juego
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
                    Spacer(Modifier.width(15.dp))
                }
            }
        }
    }
}

@Composable
fun ItemComentariosG(
    item: Comentarios,
    viewModelNV: PromotorNuevaVisitaViewModel
) {
    var expandcards by remember { mutableStateOf(false) }
    val icon = if (item.tipo!!) Icons.Filled.ThumbUp else Icons.Filled.ThumbDown
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
                        Icon(
                            Icons.Filled.SnippetFolder, "Diferencia", modifier = Modifier.align(
                                Alignment.CenterVertically
                            )
                        )
                        Column {
                            Text(
                                text = "Juego: ${item.juego!!.nombre}",
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Text(
                                text = "Perfil: ${item.perfil!!.nombre}",
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                        }
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
                                text = "Ingresos: $${item.ingresos}.00",
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Text(
                                text = "Procedencia: ${item.procedencia}",
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Text(
                                text = "Comentarios: ${item.comentario}",
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }
                    }
                }
            }
            Column() {
                Icon(
                    imageVector = icon,
                    contentDescription = "icon",
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(vertical = 10.dp)
                )
                IconButton(onClick = {
                    viewModelNV.RemoveComentGenerales(item)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "delete"
                    )
                }
            }
        }
    }
}

@Composable
fun AlertJuegosComent(
    viewModel: PromotorNuevaVisitaViewModel,
    paqueteria_familia: SnapshotStateList<String>,
    alert: MutableState<Int>,
    sub_juego: SnapshotStateList<String>
) {
    val context = LocalContext.current
    if (alert.value==1) {
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
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 10.dp)
                                .clickable {
                                    alert.value = 0
                                }
                        )
                        Text(
                            text = "SELECCIONA EL PRODUCTO",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                LazyColumn {
                    itemsIndexed(viewModel.juegosFilter) { index, label ->
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                paqueteria_familia[0] = label.nombre.toString()
                                paqueteria_familia[1] = label.id.toString()
                                sub_juego[0] = ""
                                sub_juego[1] = "0"
                                alert.value = 0
                                viewModel.getSubjuegos(label.id!!.toInt(), context = context)
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
            shape = RoundedCornerShape(15.dp)
        )
    }
    if (alert.value==2) {
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
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 10.dp)
                                .clickable {
                                    alert.value = 0
                                }
                        )
                        Text(
                            text = "SELECCIONA EL SUB JUEGO",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                LazyColumn {
                    itemsIndexed(viewModel.subjuegos) { index, label ->
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                sub_juego[0] = label.nombre.toString()
                                sub_juego[1] = label.id.toString()
                                alert.value = 0
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
            shape = RoundedCornerShape(15.dp)
        )
    }
}

@Composable
fun AlertJuegosPerfil(
    alert: MutableState<Boolean>,
    perfil: List<Proveedor>,
    perfil_selec: SnapshotStateList<String>
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
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 10.dp)
                                .clickable {
                                    alert.value = false
                                }
                        )
                        Text(
                            text = "SELECCIONA EL PERFIL",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
                LazyColumn {
                    itemsIndexed(perfil) { index, label ->
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                perfil_selec[0] = label.nombre.toString()
                                perfil_selec[1] = label.id.toString()
                                alert.value = false
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
            shape = RoundedCornerShape(15.dp)
        )
    }
}