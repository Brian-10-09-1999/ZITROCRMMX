package com.example.zitrocrm.screens.salas

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.zitrocrm.R
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.*
import com.example.zitrocrm.network.models_dto.Filter.FilterViewModel
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ObjSemanalFilter.Message
import com.example.zitrocrm.repository.Models.models_nueva_visita.ArrayFoto
import com.example.zitrocrm.repository.Models.models_nueva_visita.TipoFoto
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.ui.theme.reds
import com.example.zitrocrm.repository.SharedPrefence
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.alertDetalleSave
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
    val objetivoSemJuego = viewModelNV.juegosObjetivo
    val tipo = viewModelNV.tipo
    val act = viewModelNV.a.value
    val colorbingo by animateColorAsState(
        when (tipo.value) {
            true -> Color.White
            false -> Color.Green
        }
    )
    val colorslots by animateColorAsState(
        when (tipo.value) {
            true -> Color.Green
            false -> Color.White
        }
    )
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
                colorslots = colorslots,
                visita = visita
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
                observ_competencia = observ_competencia,
                objetivoSemJuego = objetivoSemJuego
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
    colorslots: Color,
    visita: Visita
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
                        checked = visita.tipo == 1,
                        onCheckedChange = {
                            if (it) visita.tipo = 1 else visita.tipo = 2
                            viewModelNV.getNuevaVisitaFilters(
                                token = token,
                                tipo = visita.tipo!!.toInt(),
                                clear = true
                            )
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
    objetivoSemJuego: SnapshotStateList<Message>,
) {
    Scaffold {
        AlertEnvio(viewModelNV)
        LazyColumn(
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(blacktransp)
        ) {

//----------------------------------VISITA PROMOTORES-----------------------------------------------

            item {
                VisitaPromotoresCard(
                    card = "Visita Promotores",
                    onCardArrowClick = {
                        viewModelNV.cardsexp(0)
                    },
                    expanded = cards[0],
                    viewModelNV = viewModelNV,
                    visita = visita,
                    context = context,
                    objetivoSemJuego = objetivoSemJuego
                )
            }

//--------------------------------OBJETIVO DE LA VISITA---------------------------------------------

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

//------------------------------DETALLE OCUPACION BINGO---------------------------------------------

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

//------------------------------------ACUMULADOS BINGO----------------------------------------------

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

//--------------------------LO MAS JUGADO ZITRO Y COMPETENCIA---------------------------------------

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
            if (cards[4] && mas_jugado.isNotEmpty()) {
                item {
                    ItemsTittle("Lo Más Jugado Generados")
                }
                //-----ARRAY MAS JUGADO-----//
                itemsIndexed(mas_jugado) { index, item ->
                    DataItemMasjugado(
                        item = item,
                        viewModelNV = viewModelNV
                    )
                }
            }
//------------------------COMENTARIOS GENERALES JUGADORES-------------------------------------------

            item {
                ComentariosGeneralesJugadores(
                    card6 = "Comentarios Generales Jugadores",
                    onCardArrowClick = { viewModelNV.cardsexp(5) },
                    expanded = cards[5],
                    viewModel = viewModelNV,
                    coment_generales = coment_generales
                )
            }
            if (cards[5] && coment_generales.isNotEmpty()) {
                item {
                    ItemsTittle("Comentarios Generados")
                }
                itemsIndexed(coment_generales) { index, item ->
                    ItemComentariosG(
                        item = item,
                        viewModelNV = viewModelNV
                    )
                }
            }

//---------------------COMENTARIOS DE SONIDO MAQUINAS Y PROVEEDORES CERCANOS------------------------

            item {
                ComentariosSonidoZitroComp(
                    card7 = "Comentarios Sonido Nuestras Máquinas y Proveedores Cercanos",
                    onCardArrowClick = { viewModelNV.cardsexp(6) },
                    expanded = cards[6],
                    viewModel = viewModelNV,
                    coment_sonido = coment_sonido
                )
            }
            if (cards[6] && coment_sonido.isNotEmpty()) {
                item {
                    ItemsTittle("Comentarios Sonido Generados")
                }
                itemsIndexed(coment_sonido) { index, item ->
                    ItemComentSonidoG(
                        item = item,
                        viewModelNV = viewModelNV
                    )
                }
            }
//----------------------------------OBSERVACIONES GENERALES-----------------------------------------

            item {
                ObservacionesCompetencia(
                    card8 = "Observaciones Competencia",
                    onCardArrowClick = { viewModelNV.cardsexp(7) },
                    expanded = cards[7],
                    viewModelNV = viewModelNV,
                    observ_competencia = observ_competencia
                )
            }
            if (cards[7]) {
                if (observ_competencia.isNotEmpty()) {
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
//------------------------------------------FOLIOS TECNICOS-----------------------------------------

            item {
                FoliosTecnicos(
                    card9 = "Folios Técnicos",
                    onCardArrowClick = { viewModelNV.cardsexp(8) },
                    expanded = cards[8],
                    viewModelNV = viewModelNV
                )
            }

//----------------------------------BTN ENVIAR && BTN GUARDAR---------------------------------------

            item {
                val isValidate by derivedStateOf {
                    visita.fecha!!.day!! > 0
                            && visita.fecha!!.month!! > 0
                            && visita.fecha!!.year!! > 0
                            /*&& viewModelNV.visitaPromotor.value!!.ocupacion.isNotEmpty()
                            && viewModelNV.visitaPromotor.value!!.ocupacionSlots.isNotEmpty()
                            && viewModelNV.visitaPromotor.value!!.acumulados.isNotEmpty()
                            && viewModelNV.visitaPromotor.value!!.masJugado.isNotEmpty()
                            && viewModelNV.visitaPromotor.value!!.comentarios.isNotEmpty()
                            && viewModelNV.visitaPromotor.value!!.comentariosSonido.isNotEmpty()
                            && viewModelNV.visitaPromotor.value!!.observacionesCompetencia.isNotEmpty()
                            && viewModelNV.visitaPromotor.value!!.objetivos.isNotEmpty()
                            && viewModelNV.visitaPromotor.value!!.librerias.isNotEmpty()*/
                            && viewModelNV.listdetalleOcu.isNotEmpty()
                            && viewModelNV.fecha.value.isNotBlank()
                            && viewModelNV.hora_entrada.value.isNotBlank()
                            && viewModelNV.hora_salida.value.isNotBlank()
                            && viewModelNV.juegosObjetivo.filter { it.check == true }.isNotEmpty()
                            && viewModelNV.objetivoSemanal.filter { it.check == true }.isNotEmpty()
                }
                val isRotated = rememberSaveable { mutableStateOf(false) }
                val rotationAngle by animateFloatAsState(
                    targetValue = if (isRotated.value) 360F else 0F,
                    animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
                )
                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    //--------------------------BTN ENVIAR-----------------------------//
                    Button(
                        enabled = isValidate,
                        onClick = {
                            viewModelNV.postVisitaPromotoresSala(
                                token = token,
                                salaid = salaid,
                                b = false
                            )
                            isRotated.value = !isRotated.value
                        },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
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
                            tint = Color.White,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                        Text(text = "Enviar")
                    }
                    Spacer(Modifier.width(2.dp))

                    //------------------------BTN GUARDAR-----------------------------//
                    Button(
                        enabled = isValidate,
                        onClick = {
                            viewModelNV.postVisitaPromotoresSala(
                                token = token,
                                salaid = salaid,
                                b = true
                            )
                            isRotated.value = !isRotated.value
                        },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
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
                            imageVector = Icons.Filled.Save,
                            contentDescription = "Precio Inicio",
                            tint = Color.White,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                        Text(text = "Guardar")
                    }
                }
                AlertFotos(
                    context = context,
                    viewModelNV = viewModelNV
                )
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun AlertFotos(
    context: Context,
    viewModelNV: PromotorNuevaVisitaViewModel
) {
    AlertDialog(
        onDismissRequest = {},
        title = null,
        buttons = {
            val image = remember { mutableStateOf<Uri?>(null) }
            val arrayfotos = remember {
                mutableStateListOf<ArrayFoto?>()
            }
            val pickPictureLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.GetContent()
            ) { imageUri ->
                if (imageUri != null) {
                    image.value = imageUri
                } else {
                    Toast.makeText(
                        context,
                        "No seleccionaste una foto",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            var viewImage by remember {
                mutableStateOf(false)
            }
            val text = if (image.value == null) "Selecciona la imagen"
            else "Visualiza la imagen seleccionada"

            val tipo_foto = listOf<TipoFoto>(
                TipoFoto(
                    categoria = 1,
                    idTipoFoto = 1,
                    TipoFoto = "Alineación de isla: Vista Frontal"
                ),
                TipoFoto(
                    categoria = 1,
                    idTipoFoto = 3,
                    TipoFoto = "Alineación de isla: Vista Lateral"
                ),
                TipoFoto(categoria = 1, idTipoFoto = 4, TipoFoto = "Alineación de reposapiés"),
                TipoFoto(categoria = 1, idTipoFoto = 5, TipoFoto = "Espacio entre máquinas"),
                TipoFoto(categoria = 1, idTipoFoto = 6, TipoFoto = "Peinado general de isla"),
                TipoFoto(
                    categoria = 2,
                    idTipoFoto = 7,
                    TipoFoto = "Vista frontal (Derecha/Izquierda)"
                ),
                TipoFoto(
                    categoria = 2,
                    idTipoFoto = 8,
                    TipoFoto = "Vista lateral (Derecha/Izquierda)"
                ),
                TipoFoto(
                    categoria = 3,
                    idTipoFoto = 9,
                    TipoFoto = "Alineación de sing respecto a Isla"
                ),
                TipoFoto(categoria = 3, idTipoFoto = 10, TipoFoto = "Alineación de pantallas"),
                TipoFoto(categoria = 3, idTipoFoto = 11, TipoFoto = "Estabilizadores"),
                TipoFoto(categoria = 4, idTipoFoto = 12, TipoFoto = "Peinado Rack"),
                TipoFoto(categoria = 4, idTipoFoto = 13, TipoFoto = "Peinado APC")
            )
            val tipo_seleccionado = remember {
                mutableStateOf<TipoFoto?>(null)
            }
            var tipo_list by remember {
                mutableStateOf(false)
            }
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
                                /* openclose.value = 0
                                viewModel.a()*/
                            }
                    )
                    Text(
                        text = "DOCUMENTACION FOTOGRAFICA",
                        modifier = Modifier.align(CenterVertically)
                    )
                }
            }
            LazyColumn(modifier = Modifier.padding(horizontal = 10.dp)) {
                item {
                    OutlinedTextField(
                        enabled = false,
                        value = text,
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.5.dp),
                        trailingIcon = {
                            IconButton(onClick = {
                                viewImage = !viewImage
                            }) {
                                Icon(Icons.Filled.Visibility, "contentDescription")
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        ),
                        leadingIcon = {
                            IconButton(onClick = {
                                pickPictureLauncher.launch("image/*")
                            }) {
                                Icon(Icons.Filled.AddAPhoto, "contentDescription")
                            }
                        }
                    )
                }
                item {
                    AnimatedVisibility(visible = image.value != null && viewImage) {
                        Card(Modifier.fillMaxWidth()) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(image.value)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "barcode image",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10))
                                    .height(250.dp)
                                    .padding(8.dp),
                            )
                        }
                    }
                }
                item {
                    val tip = if (tipo_seleccionado.value == null) "Selecciona tipo de imagen"
                    else tipo_seleccionado.value!!.TipoFoto.toString()
                    OutlinedTextField(
                        enabled = false,
                        value = tip,
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.5.dp)
                            .clickable {
                                tipo_list = !tipo_list
                                viewImage = false
                            },
                        trailingIcon = {
                            Icon(Icons.Filled.Image, "contentDescription")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White
                        )
                    )
                }
                if (tipo_list) {
                    itemsIndexed(tipo_foto) { index, item ->
                        Card(modifier = Modifier
                            .padding(vertical = 2.dp)
                            .fillMaxWidth()
                            .clickable {
                                tipo_list = false
                                tipo_seleccionado.value = item
                            }
                        ) {
                            Text(text = item.TipoFoto.toString())
                        }
                    }
                }
                item {
                    Button(
                        enabled =
                        if (tipo_seleccionado.value != null && image.value != null) true
                        else false,
                        onClick = {
                            tipo_list = false
                            viewImage = false
                            arrayfotos.add(
                                ArrayFoto(
                                    Uri = image.value!!,
                                    TipoFoto = tipo_seleccionado.value
                                )
                            )
                            tipo_seleccionado.value = null
                            image.value = null
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)
                            .height(60.dp),
                        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
                        shape = RoundedCornerShape(10),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.reds)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Precio Inicio",
                            tint = Color.White,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                        Text(text = " AGREGAR IMAGEN")
                    }
                }
                if (arrayfotos.isNotEmpty()) {
                    item {
                        LazyRow {
                            itemsIndexed(arrayfotos) { index, it ->
                                Card(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .fillMaxWidth()
                                ) {
                                    Column {
                                        Text(
                                            text = it!!.TipoFoto!!.TipoFoto.toString(),
                                            modifier = Modifier
                                                .padding(end = 20.dp)
                                                .align(Start)
                                        )
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(it!!.Uri!!)
                                                .crossfade(true)
                                                .build(),
                                            contentDescription = "barcode image",
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(10))
                                                .height(250.dp)
                                                .padding(8.dp)
                                                .align(CenterHorizontally),
                                        )
                                        IconButton(
                                            onClick = {
                                                viewModelNV.postfoto(arrayfotos)
                                                //arrayfotos.remove(it)
                                            }, modifier = Modifier
                                                .align(End)
                                                .size(25.dp)
                                        ) {
                                            Icon(Icons.Filled.Delete, contentDescription = null)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        ),
        modifier = Modifier.height(550.dp),
        shape = RoundedCornerShape(18.dp)
    )
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
                                .align(CenterVertically)
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