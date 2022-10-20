package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Visita
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.ui.theme.blackdark

@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ActividadVisitaCard(
    card3: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    visita: Visita,
) {
    val queHacer = remember { mutableStateOf(visita.queHacer.toString()) }
    val actividad = remember { mutableStateOf(visita.objetivoSemanal.toString()) }
    //------------------------------------------------------------------------------------------//
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
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.15f)
                            .align(Alignment.CenterVertically)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.lista_de_verificacion),
                            contentDescription = "IconList",
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = card3,
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
            ObjetivoVisitaExpand(
                expanded = expanded,
                visita = visita,
                queHacer = queHacer,
                actividad = actividad
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ObjetivoVisitaExpand(
    expanded: Boolean = true,
    visita: Visita,
    queHacer: MutableState<String>,
    actividad: MutableState<String>,
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
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    //------------------------------------INPUT ACTIVIDAD VISITA-------------------------------//
                    OutlinedTextField(
                        value = actividad.value,
                        onValueChange = {
                            visita.objetivoSemanal = it
                            actividad.value = visita.objetivoSemanal.toString()
                        },
                        label = { Text("Actividad") },
                        modifier = Modifier
                            .padding(vertical = 2.5.dp)
                            .fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.TextFormat,
                                contentDescription = null
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    //-------------------------------INPUT QUE HACES PARA REALIZAR ACT--------------------------//
                    OutlinedTextField(
                        value = queHacer.value,
                        onValueChange = {
                            visita.queHacer = it
                            queHacer.value = visita.queHacer.toString()
                        },
                        label = { Text("¿Qué haces para realizar la actividad?") },
                        modifier = Modifier
                            .padding(vertical = 2.5.dp)
                            .fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.TextFormat,
                                contentDescription = null
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    //--------------------------------------------------------------------------------------------//
                }
            }
        }
    }
}

@Composable
fun visita_observacion(visita: Visita) {
    Column(modifier = Modifier.padding(horizontal = 25.dp)) {
        var propuestas by remember { mutableStateOf(visita.propuestas) }
        var conclusion by remember { mutableStateOf(visita.conclusion) }
        var observacionesGenerales by remember { mutableStateOf(visita.observacionesGenerales) }
        val focusManager = LocalFocusManager.current
        //-----------------------------------PROPUESTA VISITA---------------------------------------
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
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Comment,
                    contentDescription = "observaciones"
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White
            )
        )
        OutlinedTextField(
            value = conclusion.toString(),
            onValueChange = {
                visita.conclusion = it
                conclusion = visita.conclusion
            },
            label = { Text("Conclusión") },
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
                    imageVector = Icons.Filled.Comment,
                    contentDescription = "observaciones"
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White
            )
        )
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
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Comment,
                    contentDescription = "observaciones"
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White
            )
        )
        Spacer(Modifier.height(15.dp))
    }
}