package com.example.zitrocrm.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zitrocrm.R
import com.example.zitrocrm.screens.login.components.*
import com.example.zitrocrm.ui.theme.blacktransp
import com.example.zitrocrm.repository.SharedPrefence
import com.example.zitrocrm.screens.login.LoginViewModel
import com.example.zitrocrm.ui.theme.white

@Composable
fun LoginScreen(
    onclickLogin: (user: String, pwd: String, context: Context) -> Unit,
    imageError: Boolean,
    loginViewModel: LoginViewModel
) {
    val context = LocalContext.current
    val datastore = SharedPrefence(LocalContext.current)
    val user_shp = datastore.getUserLogin().toString()
    val psw_shp = datastore.getPassLogin().toString()
    var pwd by rememberSaveable { mutableStateOf(value = "") }
    var user by rememberSaveable { mutableStateOf(value = "") }
    if (datastore.getInicioLogin().toString() == "true") {
        loginViewModel.checkInicio.value = true
        loginViewModel.login(user_shp, psw_shp, context)
    }
    BoxWithConstraints {
        val padding =
            if (maxWidth < 360.dp) {
                0.dp
            } else if (maxWidth < 600.dp) {
                20.dp
            } else {
                60.dp
            }

        Scaffold {
            Image(
                painter = painterResource(R.drawable.backgroud_crmm),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column() {
                Image(
                    painter = painterResource(R.drawable.crm_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(.35f)
                        .align(CenterHorizontally),
                    contentScale = ContentScale.Fit
                )
                Box(modifier = Modifier)
                {
                    Spacer(
                        Modifier
                            .matchParentSize()
                            .background(color = blacktransp)
                    )
                    var usuariox by rememberSaveable { mutableStateOf(value = "") }
                    var passx by rememberSaveable { mutableStateOf(value = "") }
                    val isValidate by derivedStateOf { usuariox.isNotBlank() && passx.isNotBlank() }
                    val focusManager = LocalFocusManager.current

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 30.dp, vertical = 10.dp)
                            .background(color = blacktransp),
                    ) {
                        Spacer(Modifier.fillMaxHeight(.1f))
                        Text(
                            text = "Iniciar Sesión",
                            color = Color.White,
                            fontSize = 25.sp,
                            modifier = Modifier.align(CenterHorizontally),
                            style = MaterialTheme.typography.subtitle2,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(Modifier.fillMaxHeight(.1f))

                        EmailOutTextField(
                            textValue = usuariox,
                            onValueChange = { usuariox = it },
                            onClickButton = { usuariox = "" },
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        )

                        Spacer(Modifier.fillMaxHeight(.1f))

                        PasswordOutTextField(
                            textValue = passx,
                            onValueChange = { passx = it },
                            onDone = {
                                focusManager.clearFocus()
                            }
                        )
                        Spacer(Modifier.fillMaxHeight(.02f))
                        val ctxt = LocalContext.current
                        Row(
                            Modifier
                                .padding(vertical = 0.dp)
                                .align(Alignment.End)
                        ) {
                            Text(
                                text = "Guardar Inicio de Sesión",
                                modifier =
                                Modifier
                                    .padding(
                                        horizontal = 3.dp
                                    )
                                    .align(Alignment.CenterVertically),
                                fontSize = 13.sp,
                                textAlign = TextAlign.Start,
                            )
                            Checkbox(
                                checked = loginViewModel.checkInicio.value,
                                onCheckedChange = {
                                    loginViewModel.checkInicio.value = it
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = colorResource(R.color.reds),
                                    uncheckedColor = colorResource(R.color.blackdark),
                                    checkmarkColor = colorResource(R.color.white)
                                )
                            )
                        }
                        Spacer(Modifier.fillMaxHeight(.02f))

                        ButtonLogin(
                            onclick = { onclickLogin(usuariox, passx, ctxt) },
                            enabled = isValidate
                        )
                        Spacer(Modifier.fillMaxHeight(.1f))
                    }
                }
                ErrorImageAuth(isImageValidate = imageError)
            }
        }
    }
}
