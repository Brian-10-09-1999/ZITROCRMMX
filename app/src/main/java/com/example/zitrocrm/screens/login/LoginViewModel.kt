package com.example.zitrocrm.screens.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zitrocrm.network.models_dto.Login.LoginDto
import com.example.zitrocrm.network.repository.RetrofitHelper
import com.example.zitrocrm.repository.SharedPrefence
import com.example.zitrocrm.screens.login.components.alert
import com.example.zitrocrm.screens.login.components.alertdialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel (
) : ViewModel() {
    val isSuccessLoading = mutableStateOf(value = false)
    val imageErrorAuth = mutableStateOf(value = false)
    //val progressBar = mutableStateOf(value = false)
    val checkInicio = mutableStateOf(false)

    fun login(user: String, pwd: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                alertdialog(1,"")
                alert.value = 1
                delay(1500L)
                val authService = RetrofitHelper.getAuthService()
                val responseService = authService.getLogin(LoginDto(usuario = user, pwd = pwd))
                if (responseService.ok!!) {
                    val token = responseService.token
                    val dataStorePreferenceRepository = SharedPrefence(context)
                    isSuccessLoading.value = true
                    Log.d("Logging", "Sharedrrr" + dataStorePreferenceRepository.getEmail().toString())
                    dataStorePreferenceRepository.saveinicio(checkInicio.value)
                    dataStorePreferenceRepository.saveUser(user, pwd, responseService.usuario!!, token!!)
                } else if(responseService.ok==false) {
                    alertdialog(2,responseService.msg.toString())
                    responseService.msg.toString()
                    imageErrorAuth.value = true
                    delay(1500L)
                    imageErrorAuth.value = false
                }
                //loginRequestLiveData.postValue(responseService.isSuccessful)
                alertdialog(0,"")
            } catch (e: Exception) {
                if(e.toString()=="retrofit2.HttpException: HTTP 404 Not Found"){
                    alertdialog(2,"Comprueba el usuario y contraseña")
                }else{
                    alertdialog(2,"No se pudo conectar con el servidor")
                }
                Log.d("Logging", "Error Authentication"+e.toString())
                delay(4000)
                alertdialog(0,"")
            }
        }
    }
}
