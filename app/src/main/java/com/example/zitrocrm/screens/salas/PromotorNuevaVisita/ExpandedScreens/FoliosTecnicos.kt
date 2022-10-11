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
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.SnippetFolder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.FoliosTecnicos.rows
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.ui.theme.blackdark
import com.example.zitrocrm.utils.Val_Constants

@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun FoliosTecnicos(
    card9: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    viewModelPromotorNuevaVisita: PromotorNuevaVisitaViewModel

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
                            painter = painterResource(id = R.drawable.nota),
                            contentDescription = "IconList",
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = card9,
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
            FoliosTecnicosExpand(expanded, viewModelPromotorNuevaVisita)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun FoliosTecnicosExpand(
    expanded: Boolean = true,
    viewModelPromotorNuevaVisita: PromotorNuevaVisitaViewModel
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
                    if (viewModelPromotorNuevaVisita.foliostecnicossalas.isNotEmpty()) {
                        viewModelPromotorNuevaVisita.foliostecnicossalas.forEach { Item ->
                            Folios(Item)
                        }
                    } else {
                        Text(
                            text = "No hay folios tecnicos para esta sala.",
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Folios(
    Item: rows,
) {
    var expandcards by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .clickable { expandcards = !expandcards }
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
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
                    Text(
                        text = "Folio: " + Item.numeroFolio,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(vertical = 15.dp, horizontal = 5.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.DateRange, "Diferencia")
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Fecha de Apertura: " + Item.fecha,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(vertical = 15.dp, horizontal = 5.dp)
                    )
                }
            }
            if (expandcards) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Details, "Diferencia")
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 20.dp)
                    ) {
                        Column() {

                            Text(
                                text = "Detalles: " + Item.descripcionFalla,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 1.dp, horizontal = 5.dp)
                            )

                            Text(
                                text = "Estatus: " + Item.estatus,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 1.dp, horizontal = 5.dp)
                            )
                            Text(
                                text = "Nombre: " + Item.nombre,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 1.dp, horizontal = 5.dp)
                            )
                            Text(
                                text = "Seq: " + Item.Seq,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 1.dp, horizontal = 5.dp)
                            )
                            Text(
                                text = "Count: " + Item.count,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 1.dp, horizontal = 5.dp)
                            )
                            Text(
                                text = "Gabinete: " + Item.gabinete,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 1.dp, horizontal = 5.dp)
                            )
                            Text(
                                text = "Juego: " + Item.juego,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 1.dp, horizontal = 5.dp)
                            )
                            Text(
                                text = "Licencia: " + Item.licencia,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 1.dp, horizontal = 5.dp)
                            )
                            Text(
                                text = "Medio: " + Item.medio,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 1.dp, horizontal = 5.dp)
                            )
                            Text(
                                text = "Motivo: " + Item.motivo,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 1.dp, horizontal = 5.dp)
                            )
                            Text(
                                text = "Origen: " + Item.origen,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 1.dp, horizontal = 5.dp)
                            )
                            Text(
                                text = "Serie: " + Item.serie,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 1.dp, horizontal = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}