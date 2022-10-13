package com.example.zitrocrm.screens.salas.PromotorNuevaVisita

import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.*
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.Competencia.CompetenciaArray
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.FoliosTecnicos.rows
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.GetVisita.RowsDO2
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.JuegosFilter.Juegos
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ObjSemanalFilter.Message
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ProveedorFilter.Rows
import com.example.zitrocrm.network.repository.RetrofitHelper
import com.example.zitrocrm.repository.SharedPrefence
import com.example.zitrocrm.screens.login.components.alertdialog
import com.example.zitrocrm.screens.salas.PromotorNuevaVisita.components.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromotorNuevaVisitaViewModel @Inject constructor(
) : ViewModel() {
    //-----------------------------------------BINGO 2 - SLOTS 1-----------------------------------------------//
    //----------------------------------------ARRAY DE LA VISITA-----------------------------------------------//
    var visitaPromotor: MutableState<PromotorNuevaVisita?> = mutableStateOf(PromotorNuevaVisita())
    //-----------------------------------------VISITA PROMOTORES-----------------------------------------------//
    val fecha = mutableStateOf("")
    val hora_entrada = mutableStateOf("")
    val hora_salida = mutableStateOf("")
    val objetivoSemanaal = mutableStateOf("")
    var listHorarios = mutableStateListOf<Horarios>()
    fun texthours(): String {
        var text = ""
        listHorarios.forEach { item -> text = "${text}${item.horario}," }
        return text
    }

    //---------------------------------------DETALLE DE OCUPACION---------------------------------------------//
    var list_familia_paqueteria = mutableStateListOf<CompetenciaArray>()
    var listdetalleOcu = mutableStateListOf<RowsDO2>()
    fun addDetalleOcupacion(
        proveedor_info: SnapshotStateList<String>,
        paqueteria_familia: SnapshotStateList<String>,
        isRotated: MutableState<Boolean>
    ) {
        val juego = if (paqueteria_familia[0].isBlank()) null else paqueteria_familia[0]
        val juegoidfk = if (paqueteria_familia[1].isBlank()) null else paqueteria_familia[1]
        listdetalleOcu.add(
            RowsDO2(
                proveedor = proveedor_info[0],
                proveedoridfk = proveedor_info[1].toInt(),
                tipo = proveedor_info[2].toInt(),
                juego = juego,
                juegoidfk = juegoidfk!!.toInt(),
                maquinas1 = 0,
                maquinasLt1 = 0,
                total = 0,
                horarios = ArrayList<Horarios>(/*viewModel_PNV.listHorarios*/),
            )
        )
        proveedores_selections.add(
            Rows(
                nombre = proveedor_info[0],
                id = proveedor_info[1].toInt(),
                tipo = proveedor_info[2].toInt()
            )
        )
        proveedor_info[0] = ""
        proveedor_info[1] = "0"
        proveedor_info[2] = "0"
        paqueteria_familia[0] = ""
        paqueteria_familia[1] = "0"
        isRotated.value = !isRotated.value
    }
    //---------------------------------------ACUMULADOS BINGO--------------------------------------------------//
    fun addAcumulados(
        proveedor_info: SnapshotStateList<String>,
        inicio: MutableState<String>,
        fin: MutableState<String>,
        evento: MutableState<String>,
        hora_inicio: MutableState<String>,
        hora_fin: MutableState<String>,
        premio: MutableState<String>,
        acumulados: ArrayList<Acumulados>,
        isRotated: MutableState<Boolean>,
        diferencia: MutableState<String>,
    ) {
        //if (acumulados.isNotEmpty() && acumulados.find { it.proveedor!!.nombre == proveedorInfo[0] }?.proveedor!!.nombre.toString() == proveedorInfo[0]) { } else { }
        val event = if (evento.value.isBlank()) null else evento.value
        val horainicio = if (hora_inicio.value.isBlank()) null else hora_inicio.value
        val horafin = if (hora_fin.value.isBlank()) null else hora_fin.value
        val premi = if (premio.value.isBlank()) null else premio.value
        isRotated.value = !isRotated.value
        acumulados.add(
            Acumulados(
                proveedor = ID(
                    id = proveedor_info[1].toInt(),
                    nombre = proveedor_info[0]
                ),
                inicio = inicio.value.toInt(),
                fin = fin.value.toInt(),
                evento = event,
                horaInicio = horainicio,
                horaFin = horafin,
                premio = premi
            )
        )
        proveedor_info[1] = "0"
        proveedor_info[0] = ""
        inicio.value = ""
        fin.value = ""
        evento.value = ""
        hora_inicio.value = ""
        hora_fin.value = ""
        premio.value = ""
        diferencia.value = "";a()
    }
    fun removeAcumulados(item: Acumulados) {
        visitaPromotor.value!!.acumulados.remove(item);a()
    }
    //--------------------------------------LO MAS JUGADO ZITRO Y COMPETENCIA----------------------------------------//
    fun addZitroyComp(
        unidadOcupacion: MutableState<String>,
        apuestas_promedio: MutableState<String>,
        producto_paqueteria: SnapshotStateList<String>,
        proveedor_info: SnapshotStateList<String>,
        tiro_minimo: MutableState<String>,
        tiro_maximo: MutableState<String>,
        tiro_promedio: MutableState<String>,
        array_zona: SnapshotStateList<Zona>,
        isRotated: MutableState<Boolean>,
        array_mas_jugado: ArrayList<MasJugado>,
        progresivos: MutableList<Progresivos>,
        promedio_ocupacion: MutableState<String>,
        zona_nofumar: MutableState<Boolean>,
        zona_fumar: MutableState<Boolean>,
        sap2: MutableState<Boolean>,
        lap2: MutableState<Boolean>,
    ) {
        array_mas_jugado.add(
            MasJugado(
                apuestasPromedio = apuestas_promedio.value,
                juego = ID(id = producto_paqueteria[1].toInt(), nombre = producto_paqueteria[0]),
                progresivos = ArrayList(progresivos),
                promedioOcupacion = if (unidadOcupacion.value == "Hr") promedio_ocupacion.value.toInt() * 60 else promedio_ocupacion.value.toInt(),
                proveedor = Rows(
                    id = proveedor_info[1].toInt(),
                    nombre = proveedor_info[0],
                    tipo = proveedor_info[2].toInt()
                ),
                tiroMinimo = tiro_minimo.value.toInt(),
                tiroMaximo = tiro_maximo.value.toInt(),
                tiroPromedio = tiro_promedio.value.toInt(),
                zona = ArrayList(array_zona),
                unidadOcupacion = unidadOcupacion.value
            )
        )
        apuestas_promedio.value = ""
        producto_paqueteria[1] = "0"
        producto_paqueteria[0] = ""
        progresivos.clear()
        promedio_ocupacion.value = ""
        proveedor_info[0] = ""
        proveedor_info[1] = "0"
        proveedor_info[2] = "0"
        tiro_minimo.value = ""
        tiro_maximo.value = ""
        tiro_promedio.value = ""
        array_zona.clear()
        unidadOcupacion.value = ""
        lap2.value = false
        sap2.value = false
        zona_nofumar.value = false
        zona_fumar.value = false
        isRotated.value = !isRotated.value;a()
    }
    fun removeMasJugados(item: MasJugado) {
        visitaPromotor.value!!.masJugado.remove(item);a()
    }
    //---------------------------------------------------------------------------------------------//

    var positivo2 = mutableStateOf(true)
    var negativo2 = mutableStateOf(false)
    var tipo = mutableStateOf(false)
    val objetivoSemanal = mutableStateListOf<Message>()
    val juegosFilter = mutableStateListOf<Juegos>()
    val juegosZt = mutableStateListOf<Juegos>()
    val foliostecnicossalas: MutableList<rows> = arrayListOf()

    var dataProvedorOcupacion = mutableStateListOf<Ocupacion>()
    val dataProvedorOcupacionSlots = mutableStateListOf<OcupacionSlots>()
    val proveedores_selections: MutableList<Rows> = arrayListOf()
    val proveedorObservacionesCompetencia: MutableList<Rows> = arrayListOf()
    val proveedorService = mutableStateListOf<Rows>()

    /**COMENTARIOS SONIDOS NUESTRAS MAQUINAS, ZITRO COMPETENCIA**/
    var calificacion_sonido = mutableStateOf(true)
    var provedor_sonido_comentarios = mutableStateOf("")
    var id_provedor_sonido_comentarios = mutableStateOf(0)
    var observaciones_sonido = mutableStateOf("")

    /**OBSERVACIONES COMPETENCIA**/
    var provedor_competencia = mutableStateOf("")
    var id_provedor_competencia = mutableStateOf(0)
    var observaciones_competencia = mutableStateOf("")
    val dataObservacionesCompetencia: MutableList<ObservacionesCompetencia> = arrayListOf()
    val addSonido: MutableList<Sonido> = arrayListOf()

    /**VISITA PROMOTORES**/
    val networkstate = mutableStateOf("")
    val networkstate_ID = mutableStateOf(0)
    var cards = mutableListOf<Boolean>(false, false, false, false, false, false, false, false, false, false)

    fun cardsexp(ind: Int) {
        cards.forEachIndexed { index, it ->
            if (index == ind) {
                if (it == true) {
                    cards.set(index, false)
                    cards[index] = false
                } else {
                    cards.set(index, true)
                }
            } else {
                cards.set(index, false)
            }
        }
        a()
    }

    fun cleanReport() {
        visitaPromotor.value!!.visita!!.tipo = 0
        //visita.value!!.tipo = 0
        dataProvedorOcupacion.clear()
        dataProvedorOcupacionSlots.clear()
        addSonido.clear()
        dataObservacionesCompetencia.clear()
        alertDetalleSave.value = true
        proveedores_selections.clear()
    }

    fun a() {
        a.value = false
        a.value = true
    }

    fun postVisitaPromotoresSala(token: String, salaid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val authService = RetrofitHelper.getAuthService()
            try {
                /*alertdialog(1,"")
                val responseService = authService.postSalaVisitaPromotores(
                    token = token,
                    PromotorNuevaVisita(Visita(
                        conclusion = conclucion_visita.value,
                        fecha = Fecha(day = fecha_completa[2], month = fecha_completa[1], year = fecha_completa[0]),
                        horaEntrada = fecha_inicio_fin[1].toInt(),
                        horaSalida = fecha_inicio_fin[2].toInt(),
                        objetivo = objetivoIdSelecc.value,
                        objetivoSemanal = objetivoGeneralidfk_visita.value,
                        observacionesGenerales = observaciones_visita.value,
                        propuestas = propuestas_visita.value,
                        queHacer = queHacer_visita.value,
                        tipo = visita.value!!.tipo,
                        salaid = salaid
                    ),
                        ocupacion = ArrayList<Ocupacion>(dataProvedorOcupacion),//ADD
                        ocupacionSlots = ArrayList<OcupacionSlots>(dataProvedorOcupacionSlots),
                        acumulados = ArrayList<Acumulados>(dataAcumuladosBingo),
                        masJugado = ArrayList<MasJugado>(dataLoMasJugadoZitroZomp),
                        comentarios = ArrayList<Comentarios>(dataComentariosGeneralesJugadores),
                        comentariosSonido = ArrayList<Sonido>(addSonido),
                        observacionesCompetencia = ArrayList<ObservacionesCompetencia>(dataObservacionesCompetencia),
                    )
                )
                if (responseService.isSuccessful){
                    networkstate_ID.value = responseService.body()!!.message!!.id!!.toInt()
                    networkstate.value = responseService.body()!!.msg.toString()
                    alertDetalleSave.value = true
                }else{
                    networkstate.value = responseService.body()!!.msg.toString()
                    alertDetalleSave.value = true
                }*/
                alertdialog(0, "")
            } catch (e: Exception) {
                if (e.toString() == "java.lang.NullPointerException") {
                    networkstate.value = "Comprueba que la sala y fecha de visita sean correctos."
                } else {
                    networkstate.value = "Verifica tu Conexion a Internet ${e}"
                }
                alertDetalleSave.value = true
                alertdialog(0, "")
                Log.d("Exception", "POST VISITA PROMOTORES", e)
            }
            if (networkstate_ID.value > 0) {
                cleanReport()
            }
        }
    }

    var a = mutableStateOf(true)
    fun getUltimoDO(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val authService = RetrofitHelper.getAuthService()
            try {
                alertdialog(1, "")
                val responseService =
                    authService.getUltimoDO(token, visitaPromotor.value!!.visita!!.tipo!!.toInt())
                if (responseService.ok!! && responseService.rows.isNotEmpty()) {
                    responseService.rows.forEach {
                        listdetalleOcu.add(
                            RowsDO2(
                                proveedor = it.proveedor!!.nombre,
                                proveedoridfk = it.proveedor!!.id,
                                juego = it.juego!!.nombre,
                                juegoidfk = it.juego!!.id,
                                subjuego = it.subjuego!!.nombre,
                                subjuegoidfk = it.subjuego!!.id,
                                maquinas1 = it.maquinas1,
                                maquinasLt1 = it.maquinasLt1,
                                total = it.total,
                            )
                        )
                    }
                }
                a.value = false
                a.value = true
                delay(5000)
                alertdialog(0, "")
            } catch (e: Exception) {
                alertdialog(0, "")
                Log.d("Exception", "FILTER OBJETIVO FAIL", e)
            }
        }
    }

    //valida que los campos no tengan simbolos y no esten vacios
    fun getValidationSum(string: String): Boolean {
        val component = "[0-9]{1,9}"
        return component.toRegex().matches(string)
    }

    //suma el total de maquinas
    fun getSum(a: String, b: String): Int {
        var A = ""
        var B = ""
        if (getValidationSum(a)) A = a
        if (getValidationSum(b)) B = b
        if (A == "") A = "0"
        if (B == "") B = "0"
        return A.toInt() + B.toInt()
    }

    //saca el porcentaje de la ocupacion
    fun getPorcentaje(a: String, b: String): Double {
        var A = ""
        var B = ""
        if (getValidationSum(a)) A = a
        if (getValidationSum(b)) B = b
        if (A == "") A = "0"
        if (B == "") B = "0"
        return A.toInt() * 100 / B.toDouble()
        a()
    }

    //Service api que nos devuelve las librerias
    fun getLibreriaCompetencia(
        juegos: Boolean,
        tipo: Int,
        proveedorid: Int,
        clasificacion: Int,
        context: Context
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val token = SharedPrefence(context).getToken()
            val authService = RetrofitHelper.getAuthService()
            try {
                alertdialog(1, "")
                if (proveedorid == 24 && juegos) {
                    val responseService = authService.getJuegosZitro(
                        token = token.toString(),
                        tipo = tipo,
                        clasificacion = clasificacion
                    )
                    if (responseService.body()!!.ok!!) {
                        list_familia_paqueteria.clear()
                        responseService.body()!!.juegos.forEach {
                            list_familia_paqueteria.add(
                                CompetenciaArray(
                                    id = it.id,
                                    nombre = it.nombre,
                                    tipo = tipo
                                )
                            )
                        }
                    }
                } else if (proveedorid == 24 && juegos == false) {
                    val responseService = authService.getSubJuegos(
                        token = token.toString(),
                        tipo = tipo
                    )
                    if (responseService.body()!!.ok!!) {
                        list_familia_paqueteria.clear()
                        responseService.body()!!.rows.forEach {
                            list_familia_paqueteria.add(
                                CompetenciaArray(
                                    id = it.id,
                                    nombre = it.nombre,
                                    tipo = tipo
                                )
                            )
                        }
                    }
                } else {
                    val responseService = authService.getSalasLibrerias(
                        token = token.toString(),
                        tipo = tipo,
                        proveedorid = proveedorid
                    )
                    if (responseService.ok!!) {
                        list_familia_paqueteria.clear()
                        responseService.librerias.forEach {
                            list_familia_paqueteria.add(
                                CompetenciaArray(
                                    id = it.id,
                                    nombre = it.nombre,
                                    tipo = it.tipo
                                )
                            )
                        }
                    }
                }
                alertdialog(0, "")
            } catch (e: Exception) {
                alertdialog(0, "")
                Log.d("Exception", "FILTER LIBRERIA FAIL", e)
            }
        }
    }

    fun iniciofin(inicio: Int, fin: Int) {
        listHorarios.clear()
        if (inicio > 0 && fin > 0) {
            for (i in inicio..fin) {
                listHorarios.add(Horarios(i.toString(), "", "", ""))
            }
        }
    }
    /**OBSERVACIONES DE LA COMPETENCIA**/
    fun selectProvedorObservaciones(id: Int, nombre: String) {
        alertObservacionesCompetecia.value = false
        provedor_competencia.value = nombre
        id_provedor_competencia.value = id
    }

    fun addObservacionesCompetencia() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dataObservacionesCompetencia.add(
                    ObservacionesCompetencia(
                        observaciones = observaciones_competencia.value,
                        proveedor = ID(id_provedor_competencia.value, provedor_competencia.value),
                    )
                )
                id_provedor_competencia.value = 0
                provedor_competencia.value = ""
                observaciones_competencia.value = ""
                Log.d("ADDD", "OBSERVACIONES COMPETENCIA" + dataObservacionesCompetencia)

            } catch (e: Exception) {
                Log.d("Exception", "OBSERVACIONES COMPETENCIA", e)
            }
        }
    }

    /**COMENTARIOS DE SONIDO ZITRO COMPETENCIA**/
    fun selectProveedorSonido(id: Int, nombre: String) {
        alertProveedorSonido.value = false
        provedor_sonido_comentarios.value = nombre
        id_provedor_sonido_comentarios.value = id
    }

    fun addComentSonido() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                addSonido.add(
                    Sonido(
                        clasificacionComentario = ID(
                            id_provedor_sonido_comentarios.value,
                            provedor_sonido_comentarios.value
                        ),
                        observaciones = observaciones_sonido.value,
                        tipo = calificacion_sonido.value
                    )
                )
                calificacion_sonido.value = true
                negativo2.value = false
                positivo2.value = true

                id_provedor_sonido_comentarios.value = 0
                provedor_sonido_comentarios.value = ""
                observaciones_sonido.value = ""

            } catch (e: Exception) {
                Log.d("Exception", "Detalle Ocupacion", e)
            }
        }
    }

    /**FOLIOS TECNICOS SALAS**/
    fun getFoliosTecnicos(token: String, sala: Int, cliente: Int) {
        foliostecnicossalas.clear()
        viewModelScope.launch(Dispatchers.IO) {
            val authService = RetrofitHelper.getAuthService()
            try {
                val responseService = authService.getFoliosTecnicosSalas(token, sala, cliente)
                if (responseService.isSuccessful) {
                    foliostecnicossalas += responseService.body()!!.rows
                }
            } catch (e: Exception) {
                Log.d("Exception", "GET FOLIOS TECNICOS", e)
            }
        }
    }

    //-----------------------------------------------------------FUN API GET FILTROS DE NUEVA VISITA--------------------------------------------------------//
    fun getNuevaVisitaFilters(token: String, tipoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            objetivoSemanal.clear()
            juegosFilter.clear()
            juegosZt.clear()
            proveedorService.clear()
            val authService = RetrofitHelper.getAuthService()
            try {
                alertdialog(1, "")
                //------------------------------------------OBJETIVO SEMANAL-------------------------------------------------//
                val responseService = authService.getObjSemanal(token, tipoId)
                if (responseService.ok!!) {
                    objetivoSemanal += responseService.message
                } else {
                    alertdialog(2, "No se obtuvo objetivo semanal")
                    delay(2000)
                }
                //-------------------------------------------JUEGOS ZITRO---------------------------------------------------//}
                val clasifi = if(tipoId==1) 2 else 1
                val responseService2 = authService.getJuegosZitro(token = token, tipo = tipoId, clasificacion = null)
                if (responseService2.isSuccessful) {
                    juegosFilter += responseService2.body()!!.juegos
                } else {
                    alertdialog(2, "No hay juegos")
                    delay(2000)
                }
                //--------------------------------------------PROVEEDORES---------------------------------------------------//
                val responseService5 = authService.getProveedores(token, tipoId)
                if (responseService5.body()!!.rows.isNotEmpty()) {
                    proveedorService += responseService5.body()!!.rows
                    proveedorService.forEach {
                        if (it.id == 97 && tipoId == 2) {
                            it.nombre = "SLOTS GENERAL"
                            it.tipo = 1
                        } else if (it.id == 97 && tipoId == 1) {
                            it.nombre = "BINGO GENERAL"
                            it.tipo = 2
                        } else {
                            it.tipo = tipoId
                        }
                    }
                    when (tipoId) {
                        1 -> proveedorService.add(
                            Rows(
                                id = 24,
                                nombre = "ZITRO BINGO",
                                tipo = 2
                            )
                        )
                        2 -> proveedorService.add(
                            Rows(
                                id = 24,
                                nombre = "ZITRO SLOTS",
                                tipo = 1
                            )
                        )
                    }
                } else {
                    alertdialog(2, "No hay proveedores")
                    delay(2000)
                }
                alertdialog(0, "")
            } catch (e: Exception) {
                alertdialog(0, "")
                Log.d("Exception", "FILTER OBJETIVO FAIL", e)
            }
            if (tipo.value) {
                visitaPromotor.value!!.visita!!.tipo = tipoId
            } else {
                visitaPromotor.value!!.visita!!.tipo = tipoId
            }
            a()
        }
    }

    fun aa() {
        a.value = false
        a.value = true
    }
}