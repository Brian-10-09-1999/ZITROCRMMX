package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toFile
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.zitrocrm.R
import com.example.zitrocrm.repository.Models.models_nueva_visita.ArrayFoto
import com.example.zitrocrm.repository.Models.models_nueva_visita.TipoFoto
import com.example.zitrocrm.screens.padding
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.ui.theme.blackdark

@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DcumentacionPhotoScreen(
    card10: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    viewModelNV: PromotorNuevaVisitaViewModel,
    context: Context,
    arrayfotos: SnapshotStateList<ArrayFoto?>

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
                        Icon(
                            Icons.Filled.Camera,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                        /*Image(
                            painter = painterResource(id = R.drawable.nota),
                            contentDescription = "IconList",
                            modifier = Modifier.padding(start = 10.dp)
                        )*/
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = card10,
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
            DocumentExpand(
                expanded = expanded,
                viewModelNV = viewModelNV,
                context = context,
                arrayfotos = arrayfotos
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalAnimationApi
@Composable
fun DocumentExpand(
    expanded: Boolean = true,
    viewModelNV: PromotorNuevaVisitaViewModel,
    context: Context,
    arrayfotos: SnapshotStateList<ArrayFoto?>
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

            val tipo_foto = listOf<TipoFoto>(
                TipoFoto(categoria = 1, idTipoFoto = 1, TipoFoto = "Alineación de isla: Vista Frontal"),
                TipoFoto(categoria = 1, idTipoFoto = 3, TipoFoto = "Alineación de isla: Vista Lateral"),
                TipoFoto(categoria = 1, idTipoFoto = 4, TipoFoto = "Alineación de reposapiés"),
                TipoFoto(categoria = 1, idTipoFoto = 5, TipoFoto = "Espacio entre máquinas"),
                TipoFoto(categoria = 1, idTipoFoto = 6, TipoFoto = "Peinado general de isla"),
                TipoFoto(categoria = 2, idTipoFoto = 7, TipoFoto = "Vista frontal (Derecha/Izquierda)"),
                TipoFoto(categoria = 2, idTipoFoto = 8, TipoFoto = "Vista lateral (Derecha/Izquierda)"),
                TipoFoto(categoria = 3, idTipoFoto = 9, TipoFoto = "Alineación de sing respecto a Isla"),
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
            val tip = if (tipo_seleccionado.value == null) "Selecciona tipo de imagen"
            else tipo_seleccionado.value!!.TipoFoto.toString()
            val icon = if (tipo_list)
                Icons.Filled.KeyboardArrowUp
            else
                Icons.Filled.KeyboardArrowDown

            val image = remember { mutableStateOf<Uri?>(null) }
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

            ///////////////////////////////////////////////////////////////////////////////////////
            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
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
                        }                    ,
                    trailingIcon = {
                        Icon(icon, "contentDescription")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White
                    )
                )
                AnimatedVisibility (tipo_list) {
                    Column {
                        tipo_foto.forEach { item ->
                            Card(modifier = Modifier
                                .padding(vertical = 2.dp)
                                .fillMaxWidth()
                                .clickable {
                                    tipo_list = false
                                    tipo_seleccionado.value = item
                                }
                            ) {
                                Text(text = item.TipoFoto.toString(),modifier = Modifier.padding(vertical = 3.dp))
                            }
                        }
                    }
                }
                Button(
                    enabled =
                    if (tipo_seleccionado.value != null && image.value != null) true else false,
                    onClick = {
                        tipo_list = false
                        viewImage = false
                        arrayfotos.add(
                            ArrayFoto(
                                Uri = image.value,
                                TipoFoto = tipo_seleccionado.value
                            )
                        )
                        tipo_seleccionado.value = null
                        image.value = null
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .height(60.dp),
                    elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.reds)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                    Text(text = " AGREGAR IMAGEN", modifier = Modifier.padding(5.dp))
                }
                if (arrayfotos.isNotEmpty()) {
                    LazyRow {
                        itemsIndexed(arrayfotos) { index, it ->
                            Log.d("arrayfotosarrayfotos", it!!.Uri!!.isAbsolute.toString())
                            Log.d("arrayfotosarrayfotos", it!!.Uri!!.path.toString())
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
                                            .align(Alignment.Start)
                                    )
                                    Text(
                                        text = it.Uri.toString(),
                                        modifier = Modifier
                                            .padding(end = 20.dp)
                                            .align(Alignment.Start)
                                    )
                                    Text(
                                        text = it.Uri!!.path.toString(),
                                        modifier = Modifier
                                            .padding(end = 20.dp)
                                            .align(Alignment.Start)
                                    )
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(it.Uri!!)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10))
                                            .height(250.dp)
                                            .padding(8.dp)
                                            .align(Alignment.CenterHorizontally),
                                    )
                                    IconButton(
                                        onClick = {
                                            viewModelNV.postfoto(arrayfotos,context)
                                            //arrayfotos.remove(it)
                                        }, modifier = Modifier
                                            .align(Alignment.End)
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
    }
}
