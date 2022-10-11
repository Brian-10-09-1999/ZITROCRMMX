package com.example.zitrocrm.screens.login.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.zitrocrm.R

val alert = mutableStateOf(0)
val progressBarString = mutableStateOf("")
fun alertdialog(alrt:Int,string: String){
    alert.value = alrt
    progressBarString.value = string
}
@Composable
fun ProgressBarLoading(
    modifier: Modifier = Modifier,
    isLoading: Int
) {
    if (isLoading==1) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 6.dp,
                modifier = modifier.size(60.dp)
            )
        }
    }
    /*if (progressBar.value) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 6.dp,
                modifier = modifier.size(60.dp)
            )
        }
    }*/
    val animationSpec = infiniteRepeatable<Float>(
        animation = tween(
            durationMillis = 2500,
            easing = LinearEasing,
        )
    )
    val xRotation by animateValues(
        values = listOf(0f, 180f, 180f, 0f, 0f),
        animationSpec = animationSpec
    )
    val yRotation by animateValues(
        values = listOf(0f, 0f, 180f, 180f, 0f),
        animationSpec = animationSpec
    )
    if (alert.value==1) {
        AlertDialog(
            onDismissRequest = {
            },
            confirmButton = {
                Column(modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Image(painter = painterResource(R.drawable.crm_logo), contentDescription = "",
                        modifier = Modifier.graphicsLayer {
                            rotationX = xRotation
                            rotationY = yRotation
                        }
                            .size(100.dp),
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = "Cargando..",
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = progressBarString.value,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.size(25.dp))
                }
            }, backgroundColor = Color.Transparent
        )
    }
    if (alert.value==2) {
        AlertDialog(
            onDismissRequest = {
            },
            confirmButton = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.85f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.size(25.dp))
                    Icon(
                        Icons.Filled.Cancel, contentDescription = "",
                        Modifier
                            .size(60.dp)
                            .align(Alignment.CenterHorizontally))
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = "Error..",
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = progressBarString.value,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 15.dp)
                    )
                    Spacer(modifier = Modifier.size(25.dp))
                }
            }
        )
    }
}
@Composable
fun TriangleSkewSpinIndicator() {

    /*Triangle(
        modifier = Modifier.graphicsLayer(
            rotationX = xRotation,
            rotationY = yRotation,
        )
    )*/
}
@Composable
fun animateValues(
    values: List<Float>,
    animationSpec: AnimationSpec<Float> = spring(),
): State<Float> {
    // 1. Create the groups zipping with next entry
    val groups by rememberUpdatedState(newValue = values.zipWithNext())
    // 2. Start the state with the first value
    val state = remember { mutableStateOf(values.first()) }
    LaunchedEffect(key1 = groups) {
        val (_, setValue) = state
        // Start the animation from 0 to groups quantity
        animate(
            initialValue = 0f,
            targetValue = groups.size.toFloat(),
            animationSpec = animationSpec,
        ) { frame, _ ->
            // Get which group is being evaluated
            val integerPart = frame.toInt()
            val (initialValue, finalValue) = groups[frame.toInt()]
            // Get the current "position" from the group animation
            val decimalPart = frame - integerPart
            // Calculate the progress between the initial and final value
            setValue(
                initialValue + (finalValue - initialValue) * decimalPart
            )
        }
    }
    return state
}