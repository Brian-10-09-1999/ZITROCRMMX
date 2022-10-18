package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
                visita = visita
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ObjetivoVisitaExpand(
    expanded: Boolean = true,
    visita: Visita,
) {
    var queHacer by remember { mutableStateOf(visita.queHacer.toString()) }
    var actividad by remember { mutableStateOf(visita.objetivoSemanal.toString()) }
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
                    OutlinedTextField(
                        value = actividad,
                        onValueChange = {
                            visita.objetivoSemanal = it
                            actividad = visita.objetivoSemanal.toString()
                        },
                        label = { Text("Actividad") },
                        modifier = Modifier
                            .padding(vertical = 2.5.dp)
                            .fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.TextFormat,
                                contentDescription = "Botón para elegir fecha"
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    OutlinedTextField(
                        value = queHacer,
                        onValueChange = {
                            visita.queHacer = it
                            queHacer = visita.queHacer.toString()
                        },
                        label = { Text("¿Qué haces para realizar la actividad?") },
                        modifier = Modifier
                            .padding(vertical = 2.5.dp)
                            .fillMaxWidth(),
                        leadingIcon = {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Filled.TextFormat,
                                    contentDescription = "Botón para elegir fecha"
                                )
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                }
            }
        }
    }
}
