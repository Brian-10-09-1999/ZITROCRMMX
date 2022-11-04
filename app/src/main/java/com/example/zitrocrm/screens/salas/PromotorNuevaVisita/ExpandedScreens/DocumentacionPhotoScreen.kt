package com.example.zitrocrm.screens.salas.PromotorNuevaVisita.ExpandedScreens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.zitrocrm.R
import com.example.zitrocrm.repository.Models.models_nueva_visita.ArrayFoto
import com.example.zitrocrm.repository.Models.models_nueva_visita.TipoFoto
import com.example.zitrocrm.screens.salas.*
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.PromotorNuevaVisitaViewModel
import com.example.zitrocrm.ui.theme.blackdark
import kotlinx.coroutines.runBlocking
import java.io.File


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DcumentacionPhotoScreen(
    card10: String,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    context: Context,
    arrayfotos: SnapshotStateList<ArrayFoto?>,
    viewModelNV: PromotorNuevaVisitaViewModel

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
    )
    val tipo_seleccionado = remember { mutableStateOf<TipoFoto?>(null) }
    val tipo_list = remember { mutableStateOf(false) }
    val tip = if (tipo_seleccionado.value == null) "Selecciona tipo de fotografia"
    else tipo_seleccionado.value!!.TipoFoto.toString()
    val icon = if (tipo_list.value) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown
    //val imageUri = remember { mutableStateOf<Bitmap?>(null) }
    val viewImage = remember { mutableStateOf(false) }
    ///
    var hasImage by remember { mutableStateOf(false) }
    var imageUri = remember { mutableStateOf<Uri?>(null) }
    val text = if (imageUri.value == null) "Tomar fotografia"
    else "Visualiza la imagen seleccionada"

    var grantCameraState by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val cameraPermissionlauncher: ManagedActivityResultLauncher<String, Boolean> =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
            grantCameraState = it
        }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            Log.i("UriContent@Snapshot", imageUri.value.toString())
            hasImage = success
            if (success == false) {
                imageUri.value = null
            }
        }


    /* val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
         imageUri.value = it

     }*/
    /////---------------------------------------------------------------------------------////


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
                arrayfotos = arrayfotos,
                tipo_foto = tipo_foto,
                tipo_seleccionado = tipo_seleccionado,
                tipo_list = tipo_list,
                tip = tip,
                icon = icon,
                image = imageUri,
                viewImage = viewImage,
                text = text,
                addButton = {
                    tipo_list.value = false
                    viewImage.value = false
                    arrayfotos.add(
                        ArrayFoto(
                            Uri = imageUri.value,
                            TipoFoto = tipo_seleccionado.value
                        )
                    )
                    tipo_seleccionado.value = null
                    imageUri.value = null
                    Toast.makeText(
                        context,
                        "Agregaste correctamente la imagen.",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                takePhoto = {
                    if (grantCameraState) {
                        //launcher.launch()
                        val uri = viewModelNV.getCamImageUri(context)
                                cameraLauncher.launch(uri)
                        imageUri.value = uri
                        // Set it to false here
                        hasImage = false
                    } else {
                        cameraPermissionlauncher.launch(Manifest.permission.CAMERA)
                    }
                },
                viewModelNV = viewModelNV
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalAnimationApi
@Composable
fun DocumentExpand(
    expanded: Boolean = true,
    arrayfotos: SnapshotStateList<ArrayFoto?>,
    tipo_foto: List<TipoFoto>,
    tipo_seleccionado: MutableState<TipoFoto?>,
    tipo_list: MutableState<Boolean>,
    tip: String,
    icon: ImageVector,
    image: MutableState<Uri?>,
    viewImage: MutableState<Boolean>,
    text: String,
    addButton: () -> Unit,
    takePhoto: () -> Unit,
    viewModelNV: PromotorNuevaVisitaViewModel,
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
                            viewImage.value = !viewImage.value
                        }) {
                            val color =
                                if (viewImage.value) colorResource(id = R.color.reds) else Color.Gray
                            Icon(Icons.Filled.Visibility, "contentDescription", tint = color)
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White
                    ),
                    leadingIcon = {
                        IconButton(onClick = {
                            takePhoto.invoke()
                            viewImage.value = false
                        }) {
                            val color =
                                if (image.value != null) colorResource(id = R.color.reds) else Color.Gray
                            Icon(Icons.Filled.PhotoCamera, "contentDescription", tint = color)
                        }
                    }
                )
                AnimatedVisibility(visible = image.value != null && viewImage.value) {
                    Card(Modifier.fillMaxWidth()) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(image.value)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .clip(RoundedCornerShape(10))
                                .height(400.dp)
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
                            tipo_list.value = !tipo_list.value
                            viewImage.value = false
                        },
                    trailingIcon = {
                        Icon(icon, "contentDescription")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White
                    )
                )
                AnimatedVisibility(tipo_list.value) {
                    Column {
                        tipo_foto.forEach { item ->
                            Card(modifier = Modifier
                                .padding(vertical = 2.dp)
                                .fillMaxWidth()
                                .clickable {
                                    tipo_list.value = false
                                    tipo_seleccionado.value = item
                                }
                            ) {
                                Text(
                                    text = item.TipoFoto.toString(),
                                    modifier = Modifier.padding(vertical = 3.dp)
                                )
                            }
                        }
                    }
                }
                Button(
                    enabled =
                    if (tipo_seleccionado.value != null && image.value != null) true else false,
                    onClick = {
                        addButton.invoke()
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
                    Text(text = "AGREGAR IMAGEN", modifier = Modifier.padding(5.dp))
                }
                if (arrayfotos.isNotEmpty()) {
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
                                            .padding(10.dp)
                                            .align(Alignment.Start),
                                        fontSize = 14.sp
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
                                            .size(200.dp)
                                            .padding(8.dp)
                                            .align(Alignment.CenterHorizontally),
                                    )
                                    IconButton(
                                        onClick = {
                                            arrayfotos.remove(it)
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