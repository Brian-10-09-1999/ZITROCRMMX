package com.example.zitrocrm.screens.salas.JuegosNuevosBingo

import androidx.compose.foundation.*
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.zitrocrm.R
import com.example.zitrocrm.ui.theme.blackdark
import com.example.zitrocrm.ui.theme.reds
import com.example.zitrocrm.repository.SharedPrefence

@Composable
fun JuegosNuevosBingoScreens(
    viewModel : JuegosNuevosViewModel
    //navController: NavController,
){
    val datastore = SharedPrefence(LocalContext.current)
    val cliente = ""+datastore.getCliente().toString()
    val region = ""+datastore.getRegion().toString()
    val sala = ""+datastore.getSala().toString()
    val token = datastore.getToken().toString()
    viewModel.getProveedoresJuegosNuevosBingo(token)
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                modifier = Modifier.height(70.dp),
                title = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .padding(start = 35.dp)) {
                            Text(text = "FILTROS DE BUSQUEDA ", fontSize = 9.sp, color = Color.White)
                            Text(text = "Cliente: "+cliente, fontSize = 7.sp, color = Color.White)
                            Text(text = "Region: "+region, fontSize = 7.sp, color = Color.White)
                            Text(text = "Sala: "+sala, fontSize = 7.sp, color = Color.White)
                        }
                        Image(
                            painter = painterResource(R.drawable.back_button),
                            contentDescription = "",
                            modifier = Modifier
                                .clickable {
                                    //navController.popBackStack()
                                }
                                .align(Alignment.CenterStart)
                                .size(29.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.crm_logo),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(5.dp)
                        )
                    }
                },
                backgroundColor = reds,
            )
        },
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(state = ScrollState(1))
        ) {
            ContentJuegosNuevosBingo(viewModel)
        }
    }
}

@Composable
fun ContentJuegosNuevosBingo (
    viewModel : JuegosNuevosViewModel
){
    val datastore = SharedPrefence(LocalContext.current)
    val name = ""+datastore.getName().toString()
    Column(
        modifier = Modifier
            .padding(start = 10.dp,end = 10.dp, bottom = 10.dp)

    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(blackdark)
                .fillMaxWidth()
                .size(80.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(R.drawable.nav_maquinas_icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 5.dp)
                    .size(50.dp)
            )
            Text(
                text = "Juegos Nuevos Bingo",
                style = MaterialTheme.typography.subtitle1,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 3.dp),
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                Spacer(modifier = Modifier.padding(5.dp))
                Row(modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { /*mDatePickerDialog.show()*/ }
                    .fillMaxWidth()
                    .background(color = blackdark)
                    .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Person, "User")
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = "Nombre: ${name}",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(vertical = 17.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                var proveedor by remember { mutableStateOf(false) }
                var tipo_cartones by remember { mutableStateOf(false) }

                var expanded by remember { mutableStateOf(true) }
                var textfieldSize by remember { mutableStateOf(Size.Zero) }
                val focusManager = LocalFocusManager.current
                val icon =
                    if (proveedor)
                        Icons.Filled.KeyboardArrowUp
                    else
                        Icons.Filled.KeyboardArrowDown
                var perfilselectedText by remember { mutableStateOf("") }

                OutlinedTextField(
                    enabled = false,
                    value = viewModel.proveedor.value,
                    onValueChange = { perfilselectedText },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Ascii,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            proveedor = !proveedor
                        }
                        .onGloballyPositioned { coordinates ->
                            textfieldSize = coordinates.size.toSize()
                        },
                    label = { Text("Proveedor") },
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { proveedor = !proveedor })
                    }
                )
                DropdownMenu(
                    expanded = proveedor,
                    onDismissRequest = { proveedor = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){textfieldSize.width.toDp()})
                ) {
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
                                        proveedor = false
                                    }
                            )
                            Text(
                                text = "SELECCIONA PROVEEDOR",
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                    viewModel.proveedorService.forEach {
                        DropdownMenuItem(onClick = {
                            viewModel.proveedor.value = it.nombre.toString()
                            viewModel.id_proveedor.value = it.id!!.toInt()
                            proveedor = false
                        }) {
                            Text(text = it.nombre.toString())
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = viewModel.nombre_juego.value,
                    onValueChange = { viewModel.nombre_juego.value = it },
                    label = { Text("Nombre del Juego") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Games,
                            contentDescription = "Bt"
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = viewModel.no_de_cartones.value,
                        onValueChange = { viewModel.no_de_cartones.value = it },
                        label = { Text("No. de Cartones") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                        ),
                        modifier = Modifier
                            .fillMaxWidth(.5f),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.BorderAll,
                                contentDescription = "Botón para elegir fecha"
                            )
                        }
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    OutlinedTextField(
                        enabled = false,
                        value = viewModel.tipo_cartones.value,
                        onValueChange = { perfilselectedText },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Ascii,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                tipo_cartones = !tipo_cartones
                            }
                            .onGloballyPositioned { coordinates ->
                                textfieldSize = coordinates.size.toSize()
                            },
                        label = { Text("Tipo de Cartones") },
                        trailingIcon = {
                            Icon(icon,"contentDescription",
                                Modifier.clickable { tipo_cartones = !tipo_cartones })
                        }
                    )
                    DropdownMenu(
                        expanded = tipo_cartones,
                        onDismissRequest = { tipo_cartones = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current){textfieldSize.width.toDp()})
                    ) {
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
                                            tipo_cartones = false
                                        }
                                )
                                Text(
                                    text = "TIPO DE CARTONES",
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                        }
                        viewModel.tipoCartonesService.forEach {
                            DropdownMenuItem(onClick = {
                                viewModel.tipo_cartones.value = it.nombre.toString()
                                viewModel.id_tipo_cartones.value = it.id!!.toInt()
                                tipo_cartones = false
                            }) {
                                Text(text = it.nombre.toString())
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = viewModel.figura_tabla_premios.value,
                        onValueChange = { viewModel.figura_tabla_premios.value = it },
                        label = { Text("Figura en la Tabla de Premios") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                        ),
                        modifier = Modifier
                            .fillMaxWidth(.5f),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Storage,
                                contentDescription = "Botón para elegir fecha"
                            )
                        }
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    OutlinedTextField(
                        value = viewModel.bolas_sorteadas.value,
                        onValueChange = { viewModel.bolas_sorteadas.value = it },
                        label = { Text("Bolas Sorteadas") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.ScatterPlot,
                                contentDescription = "Botó"
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = viewModel.bolas_extras.value,
                        onValueChange = { viewModel.bolas_extras.value = it },
                        label = { Text("Bolas Extras") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                        ),
                        modifier = Modifier
                            .fillMaxWidth(.5f),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.ScatterPlot,
                                contentDescription = "Botón para elegir fecha"
                            )
                        }
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    OutlinedTextField(
                        value = viewModel.acceso_a_bonus.value,
                        onValueChange = { viewModel.acceso_a_bonus.value = it },
                        label = { Text("Acceso a Bonus") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Paid,
                                contentDescription = "Botón para elegir fecha"
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "Comodín",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Row(modifier = Modifier.padding(5.dp)) {
                    Row(modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { /*mDatePickerDialog.show()*/ }
                        .fillMaxWidth(.5f)
                        .background(color = blackdark)
                        .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.CheckCircle , "contentDescription")
                        Checkbox(checked = viewModel.positivo.value,
                            colors = CheckboxDefaults.colors(
                                checkedColor = colorResource(R.color.reds),
                                uncheckedColor = colorResource(R.color.graydark),
                                checkmarkColor = colorResource(R.color.white)
                            ),
                            onCheckedChange = {
                            viewModel.positivo.value = it
                            when(it){
                                true -> viewModel.si_no.value = true
                                false -> viewModel.si_no.value = false
                            }
                        })
                        Box(




                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = "Si"/*viewModelPromotorNuevaVisita.fecha_visita.value*/,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 14.dp, horizontal = 5.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row(modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { /*mDatePickerDialog.show()*/ }
                        .fillMaxWidth(1f)
                        .background(color = blackdark)
                        .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Cancel , "contentDescription")
                        Checkbox(checked = true,
                            colors = CheckboxDefaults.colors(
                                checkedColor = colorResource(R.color.reds),
                                uncheckedColor = colorResource(R.color.graydark),
                                checkmarkColor = colorResource(R.color.white)
                            ),
                            onCheckedChange = {}
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = "No"/*viewModelPromotorNuevaVisita.fecha_visita.value*/,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(vertical = 14.dp, horizontal = 5.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                {
                    OutlinedTextField(
                        value = "1c" /*viewModelPromotorNuevaVisita.objetivoSemanal_visita.value*/,
                        onValueChange = { /*viewModelPromotorNuevaVisita.objetivoSemanal_visita.value */ },
                        modifier = Modifier
                            .fillMaxWidth(.5f),
                        label = { Text("Denominación Mín") },
                        trailingIcon = {
                            Icon(Icons.Filled.KeyboardArrowUp, "contentDescription",
                                Modifier.clickable {
                                    //alertObjetivoSemanal.value=true
                                    //navController.navigate(route = Destination.Dialog.route)
                                }
                            )
                        }
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    OutlinedTextField(
                        value = "$10" /*viewModelPromotorNuevaVisita.objetivoSemanal_visita.value*/,
                        onValueChange = { /*viewModelPromotorNuevaVisita.objetivoSemanal_visita.value */ },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = { Text("Denominación Máx") },
                        trailingIcon = {
                            Icon(Icons.Filled.KeyboardArrowUp, "contentDescription",
                                Modifier.clickable {
                                    //alertObjetivoSemanal.value=true
                                    //navController.navigate(route = Destination.Dialog.route)
                                }
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = "Alta" /*viewModelPromotorNuevaVisita.objetivoSemanal_visita.value*/,
                    onValueChange = { /*viewModelPromotorNuevaVisita.objetivoSemanal_visita.value */ },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Perfil") },
                    trailingIcon = {
                        Icon(Icons.Filled.KeyboardArrowUp, "contentDescription",
                            Modifier.clickable {
                                //alertObjetivoSemanal.value=true
                                //navController.navigate(route = Destination.Dialog.route)
                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = "Power" /*viewModelPromotorNuevaVisita.objetivoSemanal_visita.value*/,
                    onValueChange = { /*viewModelPromotorNuevaVisita.objetivoSemanal_visita.value */ },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Compite con Familia") },
                    trailingIcon = {
                        Icon(Icons.Filled.KeyboardArrowUp, "contentDescription",
                            Modifier.clickable {
                                //alertObjetivoSemanal.value=true
                                //navController.navigate(route = Destination.Dialog.route)
                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    enabled = false,
                    value = "Sap" /*viewModelPromotorNuevaVisita.objetivoSemanal_visita.value*/,
                    onValueChange = { /*viewModelPromotorNuevaVisita.objetivoSemanal_visita.value */ },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text("Progresivos") },
                    leadingIcon = {
                        Icon(Icons.Filled.Toll, "contentDescription",
                            Modifier.clickable {
                                //alertObjetivoSemanal.value=true
                                //navController.navigate(route = Destination.Dialog.route)
                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    Column(Modifier.padding(10.dp)) {
                        Text(
                            text = "Seleccion Simple",
                            Modifier
                                .clickable { }
                                .fillMaxWidth()
                                .padding(vertical = 5.dp, horizontal = 2.dp),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Slots",
                            Modifier
                                .clickable { }
                                .fillMaxWidth()
                                .padding(vertical = 5.dp, horizontal = 2.dp),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Al mejor de 3",
                            Modifier
                                .clickable { }
                                .fillMaxWidth()
                                .padding(vertical = 5.dp, horizontal = 2.dp),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Seleccion Simple",
                            Modifier
                                .clickable { }
                                .fillMaxWidth()
                                .padding(vertical = 5.dp, horizontal = 2.dp),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Partida gratis de niveles",
                            Modifier
                                .clickable { }
                                .fillMaxWidth()
                                .padding(vertical = 5.dp, horizontal = 2.dp),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Otro",
                            Modifier
                                .clickable { }
                                .fillMaxWidth()
                                .padding(vertical = 5.dp, horizontal = 2.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}