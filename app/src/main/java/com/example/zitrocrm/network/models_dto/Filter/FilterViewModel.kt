package com.example.zitrocrm.network.models_dto.Filter

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zitrocrm.network.models_dto.Filter.ClienteFilter.Clientes
import com.example.zitrocrm.network.models_dto.Filter.RegionesFilter.Regiones
import com.example.zitrocrm.network.models_dto.Filter.SalasFilter.Salas
import com.example.zitrocrm.network.repository.RetrofitHelper
import com.example.zitrocrm.repository.SharedPrefence
import com.example.zitrocrm.screens.login.components.alertdialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor() : ViewModel() {
    var NavFilter = mutableStateOf(false)
    var clientesFilter = mutableStateListOf<Clientes>()
    var regionesFilter = mutableStateListOf<Regiones>()
    var salasFilter = mutableStateListOf<Salas>()

    fun getFilters(token:String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val authService = RetrofitHelper.getAuthService()
            val dataStore = SharedPrefence(context)
            try {
                alertdialog(1,"")
                if(clientesFilter.isEmpty()&&regionesFilter.isEmpty()){
                    val responseService1 = authService.getCliente(token)
                    val responseService2 = authService.getRegiones(token)
                    if (responseService1.ok!!&&responseService2.ok!!){
                        clientesFilter += responseService1.clientes
                        regionesFilter += responseService2.regiones
                        NavFilter.value = true
                    }
                }else{
                    NavFilter.value = true
                }
                if(dataStore.getRegionId()!!.toInt()>0){
                    getSalasFilter(
                        token = token,
                        cliente = dataStore.getIDCliente()!!.toInt(),
                        region = dataStore.getRegionId()!!.toInt()
                    )
                }
                alertdialog(0,"")
            } catch (e: Exception) {
                alertdialog(0,"")
                Log.d("Exception", "FILTER CLIENTE FAIL", e)
            }
            alertdialog(0,"")
        }
    }
    fun getSalasFilter(token: String, cliente: Int, region:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val authService = RetrofitHelper.getAuthService()
            try {
                alertdialog(1,"")
                salasFilter.clear()
                val responseService = authService.getSala(token,cliente,region)
                if (responseService.ok!!) {
                    salasFilter += responseService.salas
                }
                alertdialog(0,"")
            } catch (e: Exception) {
                alertdialog(0,"")
                Log.d("Exception", "FILTER SALAS FAIL", e)
            }
        }
    }
}