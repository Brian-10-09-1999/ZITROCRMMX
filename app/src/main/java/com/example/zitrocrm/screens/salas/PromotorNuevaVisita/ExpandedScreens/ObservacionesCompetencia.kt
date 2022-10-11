package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zitrocrm.R
import com.example.zitrocrm.navigation.Destination
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Visita
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.components.alertObservacionesCompetecia
import com.example.zitrocrm.ui.theme.blackdark


@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ObservacionesCompetencia(
    card8: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    viewModelNV: PromotorNuevaVisitaViewModel,
    navController: NavController,
    visita: Visita,
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
            Box {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.15f)
                            .align(CenterVertically)){
                        Image(painter = painterResource(id = R.drawable.observar),
                            contentDescription ="IconList",
                            modifier = Modifier.padding(start = 10.dp))
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = card8,
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
            ObservacionesCompetenciaExpand(
                expanded,
                viewModelNV,
                navController,
                visita
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ObservacionesCompetenciaExpand(
    expanded: Boolean = true,
    viewModelPromotorNuevaVisita: PromotorNuevaVisitaViewModel,
    navController: NavController,
    visita: Visita
) {
    val focusManager = LocalFocusManager.current
    val isValidate by derivedStateOf { viewModelPromotorNuevaVisita.provedor_competencia.value.isNotBlank() && viewModelPromotorNuevaVisita.observaciones_competencia.value.isNotBlank() }
    val isRotated = rememberSaveable { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(targetValue = if (isRotated.value) 360F else 0F, animationSpec = tween(durationMillis = 500,easing = FastOutLinearInEasing))
    var propuestas by remember{ mutableStateOf(visita.propuestas) }
    var conclusion  by remember { mutableStateOf(visita.conclusion) }
    var observacionesGenerales by remember { mutableStateOf(visita.observacionesGenerales) }
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

                    OutlinedTextField(
                        enabled = false,
                        value = viewModelPromotorNuevaVisita.provedor_competencia.value,
                        onValueChange = { },
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
                                alertObservacionesCompetecia.value = true
                                navController.navigate(route = Destination.Dialog.route)
                            },
                        label = {Text("Seleccionar Provedor")},
                        trailingIcon = {
                            val icon = if (expanded)
                                Icons.Filled.KeyboardArrowUp
                            else
                                Icons.Filled.KeyboardArrowDown
                            Icon(icon,"contentDescription",
                                Modifier.clickable {
                                    alertObservacionesCompetecia.value = true
                                    navController.navigate(route = Destination.Dialog.route)
                                })
                        }
                    )
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModelPromotorNuevaVisita.observaciones_competencia.value,
                        onValueChange = {
                            viewModelPromotorNuevaVisita.observaciones_competencia.value = it
                                        },
                        label = { Text("Observaciones") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Comment,
                                contentDescription = "observaciones"
                            )
                        }
                    )
                    Spacer(Modifier.height(10.dp))
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
                    Button(
                        enabled=isValidate,
                        onClick = {
                            viewModelPromotorNuevaVisita.addObservacionesCompetencia()
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
                    Spacer(Modifier.height(10.dp))
                     viewModelPromotorNuevaVisita.dataObservacionesCompetencia.forEach { item ->
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)) {
                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = CenterVertically
                                ) {
                                    Icon(Icons.Filled.SnippetFolder, "Diferencia")
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(horizontal = 20.dp)
                                    ) {
                                        Text(
                                            text = "Proveedor: ${item.proveedor!!.nombre} \nObservaciones: ${item.observaciones}",
                                            fontSize = 15.sp,
                                            textAlign = TextAlign.Start,
                                            modifier = Modifier.padding(vertical = 15.dp, horizontal = 5.dp)
                                        )
                                    }
                                }

                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value = propuestas.toString(),
                        onValueChange = {
                            visita.propuestas = it
                            propuestas = visita.propuestas
                                        },
                        label = { Text("Propuestas") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Comment,
                                contentDescription = "observaciones"
                            )
                        }
                    )
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value = conclusion.toString() ,
                        onValueChange = {
                            visita.conclusion = it
                            conclusion = visita.conclusion
                                        },
                        label = { Text("Conclusión") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Comment,
                                contentDescription = "observaciones"
                            )
                        }
                    )
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value = observacionesGenerales.toString(),
                        onValueChange = {
                            visita.observacionesGenerales = it
                            observacionesGenerales = visita.observacionesGenerales
                                        },
                        label = { Text("Observaciones Generales") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Comment,
                                contentDescription = "observaciones"
                            )
                        }
                    )
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}
