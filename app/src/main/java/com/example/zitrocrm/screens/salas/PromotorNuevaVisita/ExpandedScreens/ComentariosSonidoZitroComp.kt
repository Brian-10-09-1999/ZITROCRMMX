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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Sonido
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.ui.theme.blackdark
import com.example.zitrocrm.ui.theme.graydark


@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ComentariosSonidoZitroComp(
    card7: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    viewModel: PromotorNuevaVisitaViewModel,
    coment_sonido: ArrayList<Sonido>,
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
                            .align(Alignment.CenterVertically)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.comentarios__1_),
                            contentDescription = "IconList",
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = card7,
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
            ComentariosSonidoZitroCompExpand(
                expanded = expanded,
                viewModel = viewModel,
                coment_sonido = coment_sonido
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ComentariosSonidoZitroCompExpand(
    expanded: Boolean = true,
    viewModel: PromotorNuevaVisitaViewModel,
    coment_sonido: ArrayList<Sonido>,
) {
    val tipo = remember { mutableStateOf(true) }
    val alert_proveedor = remember { mutableStateOf(false) }
    val proveedor_info = remember { mutableStateListOf("", "0", "0") }
    val observaciones_sonido = remember { mutableStateOf("") }

    val icon =
        if (alert_proveedor.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    val focusManager = LocalFocusManager.current

    AlertProveedorSeleccionado(
        list_proveedor = viewModel.proveedores_selections,
        alert_proveedor = alert_proveedor,
        proveedor_info = proveedor_info,
        onclick = {},
        competencia = false
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
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "Calificacion",
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
                    OutlinedTextField(
                        enabled = false,
                        value = proveedor_info[0],
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                alert_proveedor.value = true
                            },
                        label = { Text("Seleccionar Proveedor") },
                        trailingIcon = {
                            Icon(icon, "contentDescription")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    OutlinedTextField(
                        value = observaciones_sonido.value,
                        onValueChange = { observaciones_sonido.value = it },
                        label = { Text("Observaciones") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        leadingIcon = {
                            Icon(Icons.Filled.Comment, "")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                    val isValidate by derivedStateOf {
                        proveedor_info[0].isNotBlank()
                                && proveedor_info[1].toInt() > 0
                                && observaciones_sonido.value.isNotBlank()
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
                            viewModel.addComentSonido(
                                proveedor_info = proveedor_info,
                                observaciones_sonido = observaciones_sonido,
                                tipo = tipo,
                                isRotated = isRotated,
                                coment_sonido = coment_sonido
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
                }
            }
        }
    }
}

@Composable
fun ItemComentSonidoG(
    item: Sonido,
    viewModelNV: PromotorNuevaVisitaViewModel
) {
    val icon = if (item.tipo!!) Icons.Filled.ThumbUp else Icons.Filled.ThumbDown
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp, horizontal = 15.dp)
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
                                text = "Proveedor: ${item.clasificacionComentario!!.nombre}",
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
            Column() {
                Icon(
                    imageVector = icon,
                    contentDescription = "icon",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 10.dp)
                )
                IconButton(onClick = {
                    viewModelNV.RemoveComentSonido(item)
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