package com.example.zitrocrm.screens.salas.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zitrocrm.R

@Composable
fun HorizontalNumberPicker(
    modifier: Modifier = Modifier,
    height: Dp = 45.dp,
    min: Int = 0,
    max: Int = 10,
    default: Int = min,
    onValueChange: (Int) -> Unit = {}
) {
    val number = remember { mutableStateOf(default) }

    Row {
        PickerButton(
            size = height,
            drawable = R.drawable.ic_arrow_left,
            enabled = number.value > min,
            onClick = {
                if (number.value > min) number.value--
                onValueChange(number.value)
            }
        )

        Text(
            text = number.value.toString(),
            fontSize = (height.value / 2).sp,
            modifier = Modifier
                .padding(10.dp)
                .height(IntrinsicSize.Max)
                .align(Alignment.CenterVertically)
        )

        PickerButton(
            size = height,
            drawable = R.drawable.ic_arrow_right,
            enabled = number.value < max,
            onClick = {
                if (number.value < max) number.value++
                onValueChange(number.value)
            }
        )
    }
}

@Composable
fun PickerButton(
    size: Dp = 45.dp,
    @DrawableRes drawable: Int = R.drawable.ic_arrow_left,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val contentDesc = LocalContext.current.resources.getResourceName(drawable)
    val backgroundColor = if (enabled) MaterialTheme.colors.secondary else Color.LightGray

    Image(
        painter = painterResource(id = drawable),
        contentDescription = contentDesc,
        modifier = Modifier
            .padding(8.dp)
            .background(backgroundColor, CircleShape)
            .clip(CircleShape)
            .size(size = size)
            .clickable(
                enabled = enabled,
                onClick = { onClick() }
            )
    )
}
