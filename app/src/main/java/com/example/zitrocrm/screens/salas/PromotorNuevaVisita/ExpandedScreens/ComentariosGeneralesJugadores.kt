package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.zitrocrm.R
import com.example.zitrocrm.navigation.Destination
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Proveedor
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.SampleData
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.components.alertJuegosComentariosJugadores
import com.example.zitrocrm.ui.theme.blackdark
import com.example.zitrocrm.utils.Val_Constants

@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ComentariosGeneralesJugadores(
    card6: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    viewModelPromotorNuevaVisita: PromotorNuevaVisitaViewModel,
    navController : NavController
) {
    val transitionState = remember { MutableTransitionState(expanded).apply {
        targetState = !expanded
    }}
    val transition = updateTransition(targetState = transitionState, label = "transition")
    val cardElevation by transition.animateDp({
        tween(durationMillis = Val_Constants.ExpandAnimation)
    }, label = "elevationTransition") {
        if (expanded) 20.dp else 5.dp
    }

    Card(
        backgroundColor = blackdark,
        elevation = cardElevation,
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
                            .align(Alignment.CenterVertically)){
                        Image(painter = painterResource(id = R.drawable.comentarios),
                            contentDescription ="IconList",
                            modifier = Modifier.padding(start = 10.dp))
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
            ComentariosGeneralesJugadoresExpand(expanded, viewModelPromotorNuevaVisita, navController)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ComentariosGeneralesJugadoresExpand(
    expanded: Boolean = true,
    viewModelPromotorNuevaVisita : PromotorNuevaVisitaViewModel,
    navController : NavController
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
                    .fillMaxSize()) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)) {
                    var perfilexpanded by remember { mutableStateOf(false) }
                    val perfil = listOf(
                        Proveedor(id = 1, nombre = "Alto"),
                        Proveedor(id = 2, nombre = "Medio"),
                        Proveedor(id = 3, nombre = "Bajo")
                    )
                    var perfilselectedText by remember { mutableStateOf("") }
                    var textfieldSize by remember { mutableStateOf(Size.Zero) }
                    val icon =
                        if (expanded)
                            Icons.Filled.KeyboardArrowUp
                        else
                            Icons.Filled.KeyboardArrowDown
                    val focusManager = LocalFocusManager.current
                    Text(text = "Calificacion",
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
                                checked = viewModelPromotorNuevaVisita.positivo.value,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.graydark),
                                    checkmarkColor = colorResource(R.color.white)
                                ),
                                onCheckedChange = {
                                    viewModelPromotorNuevaVisita.positivo.value = it
                                    if (it) {
                                        viewModelPromotorNuevaVisita.negativo.value=false
                                        viewModelPromotorNuevaVisita.calificacion_comentarios.value = true
                                    }
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
                                checked = viewModelPromotorNuevaVisita.negativo.value,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.graydark),
                                    checkmarkColor = colorResource(R.color.white)
                                ),
                                onCheckedChange = {
                                    viewModelPromotorNuevaVisita.negativo.value = it
                                    if (it) {
                                        viewModelPromotorNuevaVisita.positivo.value=false
                                        viewModelPromotorNuevaVisita.calificacion_comentarios.value = false
                                    }
                                }
                            )
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        enabled = false,
                        value = viewModelPromotorNuevaVisita.juego_comentarios.value,
                        onValueChange = {},
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Ascii,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                alertJuegosComentariosJugadores.value = true
                                navController.navigate(route = Destination.Dialog.route)
                            }
                            .onGloballyPositioned { coordinates ->
                                //This value is used to assign to the DropDown the same width
                                textfieldSize = coordinates.size.toSize()
                            },
                        label = { Text("Juegos") },
                        trailingIcon = {
                            Icon(icon,"contentDescription",
                                Modifier.clickable {
                                    alertJuegosComentariosJugadores.value = true
                                    navController.navigate(route = Destination.Dialog.route) }
                            )
                        }
                    )

                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        enabled = false,
                        value = viewModelPromotorNuevaVisita.perfil_comentarios.value,
                        onValueChange = { perfilselectedText },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Ascii,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                perfilexpanded = !perfilexpanded
                            }
                            .onGloballyPositioned { coordinates ->
                                textfieldSize = coordinates.size.toSize()
                            },
                        label = { Text("Perfil") },
                        trailingIcon = {
                            Icon(icon,"contentDescription",
                                Modifier.clickable { perfilexpanded = !perfilexpanded })
                        }
                    )
                    DropdownMenu(
                        expanded = perfilexpanded,
                        onDismissRequest = { perfilexpanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current){textfieldSize.width.toDp()})
                    ) {
                        perfil.forEach { label ->
                            DropdownMenuItem(onClick = {
                                viewModelPromotorNuevaVisita.perfil_comentarios.value = label.nombre.toString()
                                viewModelPromotorNuevaVisita.id_perfil.value = label.id!!.toInt()
                                perfilexpanded = false
                            }) {
                                Text(text = label.nombre.toString())
                            }
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModelPromotorNuevaVisita.procedencia_comentarios.value,
                        onValueChange = {  viewModelPromotorNuevaVisita.procedencia_comentarios.value = it },
                        label = { Text("Procedencia") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.FilePresent,
                                contentDescription = "Procedencia"
                            )
                        }
                    )
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value =  viewModelPromotorNuevaVisita.ingresos_comentarios.value,
                        onValueChange = {  viewModelPromotorNuevaVisita.ingresos_comentarios.value = it },
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
                        }
                    )
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value =  viewModelPromotorNuevaVisita.comentarios_jugadores.value,
                        onValueChange = { viewModelPromotorNuevaVisita.comentarios_jugadores.value = it
                                        },
                        label = { Text("Comentarios") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Comment,
                                contentDescription = "Comentarios"
                            )
                        }
                    )
                    Spacer(Modifier.height(10.dp))
                    val isValidate by derivedStateOf {
                        viewModelPromotorNuevaVisita.juego_comentarios.value.isNotBlank()
                                && viewModelPromotorNuevaVisita.perfil_comentarios.value.isNotBlank()
                                && viewModelPromotorNuevaVisita.procedencia_comentarios.value.isNotBlank()
                                && viewModelPromotorNuevaVisita.ingresos_comentarios.value.isNotBlank()
                                && viewModelPromotorNuevaVisita.comentarios_jugadores.value.isNotBlank()
                    }
                    if(isValidate==false){
                        Text(
                            text = "¡ Hay campos vacíos !",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                            ,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    val isRotated = rememberSaveable { mutableStateOf(false) }
                    val rotationAngle by animateFloatAsState(
                        targetValue = if (isRotated.value) 360F else 0F,
                        animationSpec = tween(durationMillis = 500,easing = FastOutLinearInEasing)

                    )
                    Button(
                        enabled = isValidate,
                        onClick = {
                            viewModelPromotorNuevaVisita.addComentGeneralsJugadores()
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
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.reds)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Precio Inicio",
                            tint = Color.White
                        )
                    }
                    Spacer(Modifier.width(20.dp))
                    viewModelPromotorNuevaVisita.dataComentariosGeneralesJugadores.forEach { item ->
                        var expandcards by remember { mutableStateOf(false)}
                        val perfil = remember { mutableStateOf("") }
                        if(item.perfil!!.id==1){ perfil.value="Alto" }
                        if(item.perfil!!.id==2){ perfil.value="Medio" }
                        if(item.perfil!!.id==3){ perfil.value="Bajo" }
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .clickable{ expandcards = !expandcards }
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
                                    Column(){
                                        Text(
                                            text = "Perfil: ${item.perfil!!.nombre} \nJuego: ${item.juego!!.nombre} ",
                                            fontSize = 15.sp,
                                            textAlign = TextAlign.Start,
                                            modifier = Modifier.padding(vertical = 15.dp, horizontal = 5.dp)
                                        )
                                        if(expandcards){
                                            Text(
                                                text = "Ingresos $: ${item.ingresos!!} \nProcedencia: ${item.procedencia} \nComentario: ${item.comentario!!} ",
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
