package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.ObservacionesCompetencia
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.ui.theme.blackdark
import com.example.zitrocrm.ui.theme.graydark


@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ObservacionesCompetencia(
    card8: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    viewModelNV: PromotorNuevaVisitaViewModel,
    observ_competencia: ArrayList<ObservacionesCompetencia>
) {
    val alert_proveedor = remember { mutableStateOf(false) }
    val proveedor_info = remember { mutableStateListOf("", "0", "0") }
    val observaciones_competencia = remember{ mutableStateOf("") }
    val isValidate by derivedStateOf {
        proveedor_info[0].isNotBlank()
                && proveedor_info[1].toInt()>0
                && observaciones_competencia.value.isNotBlank()
    }
    val isRotated = rememberSaveable { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isRotated.value) 360F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
    )
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
                            painter = painterResource(id = R.drawable.observar),
                            contentDescription = "IconList",
                            modifier = Modifier.padding(start = 10.dp)
                        )
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
                expanded = expanded,
                viewModel = viewModelNV,
                observ_competencia = observ_competencia,
                alert_proveedor = alert_proveedor,
                proveedor_info = proveedor_info,
                observaciones_competencia = observaciones_competencia,
                isValidate = isValidate,
                rotationAngle = rotationAngle,
                isRotated = isRotated
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ObservacionesCompetenciaExpand(
    expanded: Boolean = true,
    viewModel: PromotorNuevaVisitaViewModel,
    observ_competencia: ArrayList<ObservacionesCompetencia>,
    alert_proveedor: MutableState<Boolean>,
    proveedor_info: SnapshotStateList<String>,
    observaciones_competencia: MutableState<String>,
    isValidate: Boolean,
    rotationAngle: Float,
    isRotated: MutableState<Boolean>
) {
    //---------------------------ALERT SELECCIONAR PROVEEDOR------------------------------------//
    AlertProveedorSeleccionado(
        list_proveedor = viewModel.proveedores_selections,
        alert_proveedor = alert_proveedor,
        proveedor_info = proveedor_info,
        onclick = {},
        competencia = true
    )
    //------------------------------------------------------------------------------------------//
    AnimatedVisibility(
        visible = expanded,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(start = 10.dp, top = 10.dp, end = 10.dp)
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
                    //------------------------INPUT SELECCIONAR PROVEEDOR------------------------//
                    OutlinedTextField(
                        enabled = false,
                        value = proveedor_info[0],
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                alert_proveedor.value = true
                            },
                        label = { Text("Seleccionar Provedor") },
                        trailingIcon = {
                            val icon = if (alert_proveedor.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
                            Icon(icon, "contentDescription")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    //-----------------------------INPUT OBSERVACIONES---------------------------//
                    OutlinedTextField(
                        value = observaciones_competencia.value,
                        onValueChange = {
                            observaciones_competencia.value = it
                        },
                        label = { Text("Observaciones") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
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
                    Spacer(Modifier.height(10.dp))
                    //--------------------------BTN AGREGAR OBSERVACIONES------------------------//
                    Button(
                        enabled = isValidate,
                        onClick = {
                            viewModel.addObservacionesCompetencia(
                                observ_competencia = observ_competencia,
                                observaciones_competencia = observaciones_competencia,
                                proveedor_info = proveedor_info,
                                isRotated = isRotated
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
                    //---------------------------------------------------------------------------//
                }
            }
        }
    }
}

@Composable
fun ItemObsevaciones(
    item: ObservacionesCompetencia,
    viewModelNV: PromotorNuevaVisitaViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 20.dp)
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
                        Icon(Icons.Filled.SnippetFolder, "Diferencia", modifier = Modifier.align(CenterVertically))
                        Column {
                            Text(
                                text = "Proveedor: ${item.proveedor!!.nombre}",
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
                        Text(
                            text = "Observaciones: ${item.observaciones}",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )

                    }
                }
            }
            IconButton(onClick = {
                viewModelNV.RemoveObservacionesComp(item)
            }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "delete"
                )
            }
        }
    }
}
