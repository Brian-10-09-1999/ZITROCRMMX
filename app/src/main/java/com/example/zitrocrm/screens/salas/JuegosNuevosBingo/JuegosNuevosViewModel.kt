package com.example.zitrocrm.screens.salas.JuegosNuevosBingo

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zitrocrm.network.models_dto.CompetenciaJuegosBingo.Prioridades
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ProveedorFilter.Rows
import com.example.zitrocrm.network.repository.RetrofitHelper
import com.example.zitrocrm.screens.login.components.alert
import com.example.zitrocrm.screens.login.components.alertdialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JuegosNuevosViewModel  @Inject constructor(

) : ViewModel() {
    val proveedorService : MutableList<Rows> = arrayListOf()
    val tipoCartonesService : MutableList<Rows> = arrayListOf()
    val familiaBingoJuegosService : MutableList<Rows> = arrayListOf()
    val ordenesCambioService : MutableList<Prioridades> = arrayListOf()

    val proveedor = mutableStateOf("")
    val id_proveedor = mutableStateOf(0)
    val nombre_juego = mutableStateOf("")
    val no_de_cartones = mutableStateOf("")
    val tipo_cartones = mutableStateOf("")
    val id_tipo_cartones = mutableStateOf(0)
    val figura_tabla_premios = mutableStateOf("")
    val bolas_sorteadas = mutableStateOf("")
    val bolas_extras = mutableStateOf("")
    val acceso_a_bonus = mutableStateOf("")
    var positivo = mutableStateOf(true)
    var negativo = mutableStateOf(false)
    var si_no =  mutableStateOf(true)


    /**TIPO-2 ES BINGO**/
    fun getProveedoresJuegosNuevosBingo(token:String){
        viewModelScope.launch(Dispatchers.IO) {
            proveedorService.clear()
            tipoCartonesService.clear()
            familiaBingoJuegosService.clear()
            ordenesCambioService.clear()
            val authService = RetrofitHelper.getAuthService()
            try {
                alertdialog(1,"")
                val responseService = authService.getProveedores(token, 2)
                responseService.body()!!.rows.forEach { Items ->
                    proveedorService.add(Rows(Items.id,Items.nombre))
                }
                alertdialog(0,"")
            } catch (e: Exception) {
                alertdialog(0,"")
                Log.d("Exception", "PROVEED FAIL", e)
            }
            try {
                alertdialog(1,"")
                val responseService = authService.getTipoCartones(token)
                responseService.forEach { Items ->
                    tipoCartonesService.add(Rows(Items.id,Items.nombre))
                }
                alertdialog(0,"")
            } catch (e: Exception) {
                alertdialog(0,"")
                Log.d("Exception", "FILTER SALAS FAIL", e)
            }
            try {
                alertdialog(1,"")
                val responseService = authService.getFamiliaBingoJuegos(token)
                responseService.forEach { Items ->
                    familiaBingoJuegosService.add(Rows(Items.id,Items.nombre))
                }
                alertdialog(0,"")
            } catch (e: Exception) {
                alertdialog(0,"")
                Log.d("Exception", "FILTER SALAS FAIL", e)
            }
            try {
                alertdialog(1,"")
                val responseService = authService.getOrdenesCambio(token)
                responseService.prioridades.forEach { Items ->
                    ordenesCambioService.add(Prioridades(Items.id,Items.prioridad))
                }
                alertdialog(0,"")
            } catch (e: Exception) {
                alertdialog(0,"")
                Log.d("Exception", "FILTER SALAS FAIL", e)
            }
        }
    }


}