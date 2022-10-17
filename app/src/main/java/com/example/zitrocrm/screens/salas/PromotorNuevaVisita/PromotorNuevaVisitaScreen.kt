package com.example.zitrocrm.screens.salas

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import androidx.navigation.NavController
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.*
import com.example.zitrocrm.network.models_dto.Filter.FilterViewModel
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.components.alertDetalleSave
import com.example.zitrocrm.ui.theme.reds
import com.example.zitrocrm.repository.SharedPrefence
import com.example.zitrocrm.ui.theme.blacktransp
import com.example.zitrocrm.utils.Val_Constants.CollapseAnimation
import com.example.zitrocrm.utils.Val_Constants.ExpandAnimation
import com.example.zitrocrm.utils.Val_Constants.FadeInAnimation
import com.example.zitrocrm.utils.Val_Constants.FadeOutAnimation

@Composable
fun PromotorNewScreenn(
    navController: NavController,
    viewModelNV: PromotorNuevaVisitaViewModel,
    viewModelFilter: FilterViewModel,
) {
    val context = LocalContext.current
    val datastore = SharedPrefence(context)
    val cliente = "" + datastore.getCliente().toString()
    val region = "" + datastore.getRegion().toString()
    val sala = "" + datastore.getSala().toString()
    val salaid = "" + datastore.getSalaId().toString()
    val token = datastore.getToken().toString()
    val visita = viewModelNV.visitaPromotor.value!!.visita!!
    val acumulados = viewModelNV.visitaPromotor.value!!.acumulados
    val mas_jugado = viewModelNV.visitaPromotor.value!!.masJugado
    val coment_generales = viewModelNV.visitaPromotor.value!!.comentarios
    val coment_sonido = viewModelNV.visitaPromotor.value!!.comentariosSonido
    val observ_competencia = viewModelNV.visitaPromotor.value!!.observacionesCompetencia
    val tipo = viewModelNV.tipo
    val act = viewModelNV.a.value
    val colorbingo by animateColorAsState(when (tipo.value) {true -> Color.White false -> Color.Green })
    val colorslots by animateColorAsState(when (tipo.value) {true -> Color.Green false -> Color.White })
    Scaffold(
        topBar = {
            TopAppBarNuevaVisita(
                cliente = cliente,
                region = region,
                sala = sala,
                viewModelNV = viewModelNV,
                navController = navController,
                tipo = tipo,
                token = token,
                colorbingo = colorbingo,
                colorslots = colorslots
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModelFilter.getFilters(token, context = context)
                }
            ) {
                Image(
                    painter = painterResource(R.drawable.button_filter_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(57.dp)
                        .clip(CircleShape)
                )
            }
        },
    )
    {
        if (act) {
            ContentNuevaVisita(
                viewModelNV = viewModelNV,
                navController = navController,
                context = context,
                tipo = tipo,
                cards = viewModelNV.cards,
                salaid = salaid,
                token = token,
                visita = visita,
                acumulados = acumulados,
                mas_jugado = mas_jugado,
                coment_generales = coment_generales,
                coment_sonido = coment_sonido,
                observ_competencia = observ_competencia
            )
        }
    }
}

@Composable
fun TopAppBarNuevaVisita(
    cliente: String,
    region: String,
    sala: String,
    viewModelNV: PromotorNuevaVisitaViewModel,
    navController: NavController,
    tipo: MutableState<Boolean>,
    token: String,
    colorbingo: Color,
    colorslots: Color
) {
    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.height(70.dp),
        title = {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .padding(start = 35.dp)
                ) {
                    Text(
                        text = "FILTROS DE BUSQUEDA ",
                        fontSize = 9.sp,
                        color = Color.White
                    )
                    Text(text = "Cliente: " + cliente, fontSize = 7.sp, color = Color.White)
                    Text(text = "Region: " + region, fontSize = 7.sp, color = Color.White)
                    Text(text = "Sala: " + sala, fontSize = 7.sp, color = Color.White)
                }
                Image(
                    painter = painterResource(R.drawable.back_button),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .align(Alignment.CenterStart)
                        .size(29.dp),
                )
                Image(
                    painter = painterResource(R.drawable.crm_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(5.dp)
                )
                Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                    Text(
                        text = "Bingo",
                        modifier = Modifier.align(CenterVertically),
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 10.sp,
                        color = colorbingo
                    )
                    Switch(
                        checked = tipo.value,
                        onCheckedChange = {
                            tipo.value = it
                            when (it) {
                                true -> {
                                    viewModelNV.getNuevaVisitaFilters(token, 1)
                                }
                                false -> {
                                    viewModelNV.getNuevaVisitaFilters(token, 2)
                                }
                            }
                        },
                        modifier = Modifier.align(CenterVertically),
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            uncheckedThumbColor = Color.White,
                            checkedTrackColor = Color.LightGray,
                            uncheckedTrackColor = Color.DarkGray,
                            checkedTrackAlpha = 1.0f,
                            uncheckedTrackAlpha = 1.0f,
                        )
                    )
                    Text(
                        text = "Slots",
                        modifier = Modifier.align(CenterVertically),
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 10.sp,
                        color = colorslots
                    )
                }
            }
        },
        backgroundColor = reds,
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContentNuevaVisita(
    viewModelNV: PromotorNuevaVisitaViewModel,
    navController: NavController,
    context: Context,
    tipo: MutableState<Boolean>,
    cards: MutableList<Boolean>,
    salaid: String,
    token: String,
    visita: Visita,
    acumulados: ArrayList<Acumulados>,
    mas_jugado: ArrayList<MasJugado>,
    coment_generales: ArrayList<Comentarios>,
    coment_sonido: ArrayList<Sonido>,
    observ_competencia: ArrayList<ObservacionesCompetencia>,
) {
    Scaffold {
        AlertEnvio(viewModelNV)
        LazyColumn(
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(blacktransp)
        ) {

//----------------------------------VISITA PROMOTORES-------------------------------------------
            item {
                VisitaPromotoresCard(
                    card = "Visita Promotores",
                    onCardArrowClick = {
                        viewModelNV.cardsexp(0)
                    },
                    expanded = cards[0],
                    viewModelPromotorNuevaVisita = viewModelNV,
                    visita = visita,
                    objetivoSemanaal = viewModelNV.objetivoSemanaal,
                    context = context
                )
            }

//--------------------------------OBJETIVO DE LA VISITA----------------------------------------
            item {
                ActividadVisitaCard(
                    card3 = "Actividad de la Visita",
                    onCardArrowClick = {
                        viewModelNV.cardsexp(2)
                    },
                    expanded = cards[2],
                    visita = visita,
                )
            }

//------------------------------DETALLE OCUPACION BINGO---------------------------------------
            item {
                DetalleOcupacionCard(
                    card2 = "Detalle Ocupación Bingo",
                    onCardArrowClick = {
                        viewModelNV.cardsexp(1)
                    },
                    expand = cards[1],
                    viewModelNV = viewModelNV,
                    horario = viewModelNV.texthours(),
                    context = context,
                    tipo = tipo
                )
            }
            if (cards[1] && viewModelNV.listdetalleOcu.isNotEmpty()) {
                item {
                    ItemsTittle("Proveedores seleccionados.")
                }
                //-----ARRAY OCUPACION-----//
                itemsIndexed(viewModelNV.listdetalleOcu) { index, item ->
                    DetalleOcupacion(
                        index = index,
                        item = item,
                        viewModelNV = viewModelNV
                    )
                }
            }

//------------------------------------ACUMULADOS BINGO-------------------------------------------
            if (viewModelNV.tipo.value == false) {
                item {
                    AcumuladosBingoCard(
                        card4 = "Acumulados Bingo",
                        onCardArrowClick = { viewModelNV.cardsexp(3) },
                        expanded = cards[3],
                        viewModel = viewModelNV,
                        acumulados = acumulados
                    )
                }
                if (cards[3] && acumulados.isNotEmpty()) {
                    item {
                        ItemsTittle("Acumulados Bingo Generados")
                    }
                    //-----ARRAY ACUMULADOS-----//
                    itemsIndexed(acumulados) { index, item ->
                        dataItemAcumulado(
                            item = item,
                            index = index,
                            viewModelNV = viewModelNV
                        )
                    }
                }
            }

//--------------------------LO MAS JUGADO ZITRO Y COMPETENCIA--------------------------------
            item {
                JugadoZitroCompetencia(
                    card5 = "Lo más jugado Zitro y competencia",
                    onCardArrowClick = { viewModelNV.cardsexp(4) },
                    expanded = cards[4],
                    viewModelNV = viewModelNV,
                    list_mas_jugado = mas_jugado,
                    tipo = tipo
                )
            }
            if(cards[4]&&mas_jugado.isNotEmpty()){
                item {
                    ItemsTittle("Lo Más Jugado Generados")
                }
                //-----ARRAY MAS JUGADO-----//
                itemsIndexed(mas_jugado){index,item->
                    DataItemMasjugado(
                        item = item,
                        viewModelNV = viewModelNV
                    )
                }
            }
//------------------------COMENTARIOS GENERALES JUGADORES-----------------------------------//
            item {
                ComentariosGeneralesJugadores(
                    card6 = "Comentarios Generales Jugadores",
                    onCardArrowClick = { viewModelNV.cardsexp(5) },
                    expanded = cards[5],
                    viewModel = viewModelNV,
                    coment_generales = coment_generales
                )
            }
            if(cards[5]&&coment_generales.isNotEmpty()){
                item {
                    ItemsTittle("Comentarios Generados")
                }
                itemsIndexed(coment_generales){index,item->
                    ItemComentariosG(
                        item = item,
                        viewModelNV = viewModelNV
                    )
                }
            }
//---------------------COMENTARIOS DE SONIDO MAQUINAS Y PROVEEDORES CERCANOS---------------------//
            item {
                ComentariosSonidoZitroComp(
                    card7 = "Comentarios Sonido Nuestras Máquinas y Proveedores Cercanos",
                    onCardArrowClick = { viewModelNV.cardsexp(6) },
                    expanded = cards[6],
                    viewModel = viewModelNV,
                    coment_sonido = coment_sonido
                )
            }
            if (cards[6]&&coment_sonido.isNotEmpty()){
                item {
                    ItemsTittle("Comentarios Sonido Generados")
                }
                itemsIndexed(coment_sonido){index,item->
                    ItemComentSonidoG(
                        item = item,
                        viewModelNV = viewModelNV
                    )
                }
            }
//----------------------------------OBSERVACIONES GENERALES----------------------------------//
            item {
                ObservacionesCompetencia(
                    card8 = "Observaciones Competencia",
                    onCardArrowClick = { viewModelNV.cardsexp(7) },
                    expanded = cards[7],
                    viewModelNV = viewModelNV,
                    observ_competencia = observ_competencia
                )
            }
            if(cards[7]){
                if(observ_competencia.isNotEmpty()){
                    itemsIndexed(observ_competencia) { index, item ->
                        ItemObsevaciones(
                            item = item,
                            viewModelNV = viewModelNV
                        )
                    }
                }
                item {
                    visita_observacion(visita)
                }
            }
            //FOLIOS TECNICOS
            item {
                FoliosTecnicos(
                    card9 = "Folios Técnicos",
                    onCardArrowClick = { viewModelNV.cardsexp(8) },
                    expanded = cards[8], viewModelNV
                )
            }
            val isValidate by derivedStateOf {
                visita.fecha!!.day!! > 0
                        && visita.fecha!!.month!! > 0
                        && visita.fecha!!.year!! > 0
            }
            item {
                Spacer(Modifier.height(10.dp))
                val isRotated = rememberSaveable { mutableStateOf(false) }
                val rotationAngle by animateFloatAsState(
                    targetValue = if (isRotated.value) 360F else 0F,
                    animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)

                )
                Button(
                    //enabled = isValidate,
                    onClick = {
                        viewModelNV.postVisitaPromotoresSala(
                            token,
                            salaid
                        )
                        isRotated.value = !isRotated.value
                    },
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
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
                Spacer(Modifier.height(10.dp))
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


@Composable
fun AlertEnvio(
    viewModelNV: PromotorNuevaVisitaViewModel
) {
    if (alertDetalleSave.value) {
        AlertDialog(
            onDismissRequest = {
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
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 10.dp)
                                .clickable {
                                    alertDetalleSave.value = false
                                    viewModelNV.networkstate_ID.value = 0
                                }
                        )
                        Text(
                            text = "DETALLE DE ENVIO DEL REPORTE",
                            modifier = Modifier.align(CenterVertically)
                        )
                    }
                }
                LazyColumn() {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                        ) {
                            if (viewModelNV.networkstate_ID.value > 0) {
                                Text(
                                    text = "Se Guardo Correctamente",
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                Text(
                                    text = "ID DEL REPORTE: " + viewModelNV.networkstate_ID.value.toString(),
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            } else if (viewModelNV.networkstate_ID.value == 0) {
                                Text(
                                    text = "! ERROR ¡ " + viewModelNV.networkstate.value,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }
                            Button(
                                onClick = {
                                    alertDetalleSave.value = false
                                    viewModelNV.networkstate_ID.value = 0
                                },
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxWidth()
                                    .height(60.dp),
                                elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
                                shape = RoundedCornerShape(10),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(id = com.example.zitrocrm.R.color.reds)
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

@Composable
fun ItemsTittle(string: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.Black)
            .padding(start = 15.dp, top = 0.dp, end = 15.dp, bottom = 5.dp)
            .clip(RoundedCornerShape(5.dp))
            .height(50.dp)
            .fillMaxWidth()
            .background(color = reds)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = CenterVertically
    ) {
        Icon(Icons.Filled.Save, "save")
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = string,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    vertical = 15.dp,
                    horizontal = 5.dp
                ),
            )
        }
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun CardArrow(
    onClick: () -> Unit,
    expanded: Boolean
) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(targetState = transitionState, label = "transition")
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = ExpandAnimation)
    }, label = "rotationDegreeTransition") {
        if (expanded) 0f else 180f
    }
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                Icons.Filled.ArrowDropDown, contentDescription = "", modifier = Modifier
                    .rotate(arrowRotationDegree)
                    .size(30.dp)
            )
        }
    )
}


val enterFadeIn = fadeIn(
    animationSpec = TweenSpec(
        durationMillis = FadeInAnimation,
        easing = FastOutLinearInEasing
    )
)
val enterExpand = expandVertically(
    animationSpec = tween(
        ExpandAnimation
    )
)
val exitFadeOut = fadeOut(
    animationSpec = TweenSpec(
        durationMillis = FadeOutAnimation,
        easing = LinearOutSlowInEasing
    )
)
val exitCollapse =
    shrinkVertically(animationSpec = tween(CollapseAnimation))