package com.example.zitrocrm.screens.salas.PromotorNuevaVisita

import android.content.Context
import android.util.Log
import android.widget.Toast
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
        listHorarios.forEach {item -> text = "${text}${item.horario},"}
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
        proveedorInfo: MutableState<Rows>,
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
                    id = proveedorInfo.value.id!!.toInt(),
                    nombre = proveedorInfo.value.nombre.toString()
                ),
                inicio = inicio.value.toInt(),
                fin = fin.value.toInt(),
                evento = event,
                horaInicio = horainicio,
                horaFin = horafin,
                premio = premi
            )
        )
        proveedorInfo.value.id = 0
        proveedorInfo.value.nombre = ""
        proveedorInfo.value.tipo = 0
        inicio.value = ""
        fin.value = ""
        evento.value = ""
        hora_inicio.value = ""
        hora_fin.value = ""
        premio.value = ""
        diferencia.value = ""
        a()
    }
    fun removeAcumulados(item: Acumulados) {
        visitaPromotor.value!!.acumulados.remove(item)
        a()
    }

    //--------------------------------------LO MAS JUGADO ZITRO Y COMPETENCIA----------------------------------------//






    val sap2 = mutableStateOf(false)
    val lap2 = mutableStateOf(false)
    val fumar2 = mutableStateOf(false)
    val nofumar2 = mutableStateOf(false)
    var positivo = mutableStateOf(true)
    var negativo = mutableStateOf(false)
    var positivo2 = mutableStateOf(true)
    var negativo2 = mutableStateOf(false)
    var tipo = mutableStateOf(false)
    val objetivoSemanal = mutableStateListOf<Message>()
    val juegosFilter = mutableStateListOf<Juegos>()
    val foliostecnicossalas: MutableList<rows> = arrayListOf()

    val unidadOcupacion = mutableStateOf("")
    var dataProvedorOcupacion = mutableStateListOf<Ocupacion>()
    val dataProvedorOcupacionSlots = mutableStateListOf<OcupacionSlots>()
    val proveedores_selections: MutableList<Rows> = arrayListOf()
    val proveedorObservacionesCompetencia: MutableList<Rows> = arrayListOf()
    val proveedorService = mutableStateListOf<Rows>()

    /**ACUMULADOS BINGO**/
    val dataAcumuladosBingo: MutableList<Acumulados> = arrayListOf()

    /**LO MAS JUGADO ZITRO Y COMPETENCIA**/
    var id_proveedor_lo_mas_jugado = mutableStateOf(0)
    var proveedor_lo_mas_jugado = mutableStateOf("")
    var producto_mas_jugado = mutableStateOf("")
    var id_producto_mas_jugado = mutableStateOf(0)
    var tiro_minimo = mutableStateOf("")
    var tiro_maximo = mutableStateOf("")
    var tiro_promedio = mutableStateOf("")
    var apuestas_promedio = mutableStateOf("")
    var promedio_ocupacion = mutableStateOf("")
    val dataLoMasJugadoZitroZomp: MutableList<MasJugado> = arrayListOf()
    val Zonaid: MutableList<Zona> = arrayListOf()
    val Progresivos: MutableList<Progresivos> = arrayListOf()

    /**COMENTARIOS GENERALES JUGADORES**/
    var calificacion_comentarios = mutableStateOf(true)
    var juego_comentarios = mutableStateOf("")
    var id_juego_comentarios = mutableStateOf(0)
    var perfil_comentarios = mutableStateOf("")
    var id_perfil = mutableStateOf(0)
    var procedencia_comentarios = mutableStateOf("")
    var ingresos_comentarios = mutableStateOf("")
    var comentarios_jugadores = mutableStateOf("")
    val dataComentariosGeneralesJugadores: MutableList<Comentarios> = arrayListOf()

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
    var cards =
        mutableListOf<Boolean>(false, false, false, false, false, false, false, false, false, false)

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
        dataAcumuladosBingo.clear()
        dataLoMasJugadoZitroZomp.clear()
        dataComentariosGeneralesJugadores.clear()
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
    fun getLibreriaCompetencia(token: String, tipo: Int, proveedorid: Int, clasificacion: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val authService = RetrofitHelper.getAuthService()
            try {
                alertdialog(1, "")
                if (proveedorid == 24) {
                    val responseService = authService.getJuegosZitro(
                        token = token,
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
                } else {
                    val responseService = authService.getSalasLibrerias(
                        token = token,
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

    /**ACUMULADOS BINGO**/
    fun getResta(a: String, b: String): Int {
        var A = ""
        var B = ""
        if (getValidationSum(a)) A = a
        if (getValidationSum(b)) B = b
        if (A == "") A = "0"
        if (B == "") B = "0"
        return B.toInt() - A.toInt()
    }
    /**LO MAS JUGADO ZITRO COMPETENCIA**/
    fun checksZonaId() {
        if (fumar2.value) {
            addZonaId(1, "Fumar")
        }
        if (nofumar2.value) {
            addZonaId(2, "No fumar")
        }
        if (sap2.value) {
            addSapLap(1, "Sap")
        }
        if (lap2.value) {
            addSapLap(2, "Lap")
        }
    }

    fun addZonaId(zonaid: Int, zona: String) {
        Zonaid.filter { it.id == zonaid.toString() }.forEach {
            Zonaid.remove(Zona(id = zonaid.toString(), zona = zona))
        }
        Zonaid.add(Zona(zonaid.toString(), zona))
    }

    fun addSapLap(id: Int, progresivos: String) {
        Progresivos.filter { it.id == id.toString() }.forEach {
            Progresivos.remove(Progresivos(id = id.toString(), progresivos = progresivos))
        }
        Progresivos.add(Progresivos(id.toString(), progresivos))
    }

    fun selectProveedorLoMasJugado(id: Int, nombre: String) {
        alertProveedorLoMasJugado.value = false
        proveedor_lo_mas_jugado.value = nombre
        id_proveedor_lo_mas_jugado.value = id
    }

    fun selectJuegoMasJugado(id: Int, nombre: String) {
        alertJuegosFilter.value = false
        producto_mas_jugado.value = nombre
        id_producto_mas_jugado.value = id
    }

    fun addLoMasJugadoZitroComp() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (unidadOcupacion.value == "Hr") {
                    val sum = mutableStateOf(0)
                    sum.value = promedio_ocupacion.value.toInt() * 60
                    promedio_ocupacion.value = sum.value.toString()
                }
                /*dataLoMasJugadoZitroZomp.add(
                    MasJugado(
                        proveedor = ID(
                            id_proveedor_lo_mas_jugado.value,
                            proveedor_lo_mas_jugado.value
                        ),
                        tiroMinimo = tiro_minimo.value.toInt(),
                        tiroMaximo = tiro_maximo.value.toInt(),
                        tiroPromedio = tiro_promedio.value.toInt(),
                        apuestasPromedio = apuestas_promedio.value,
                        promedioOcupacion = promedio_ocupacion.value.toInt(),
                        zona = ArrayList<Zona>(Zonaid),
                        progresivos = ArrayList<Progresivos>(Progresivos),
                        juego = ID(id_producto_mas_jugado.value, producto_mas_jugado.value),
                        unidadOcupacion = unidadOcupacion.value
                    )
                )*/
                Log.d("SUCCESS", "LO MAS JUGADO ARRAY" + dataLoMasJugadoZitroZomp)
                id_proveedor_lo_mas_jugado.value = 0
                proveedor_lo_mas_jugado.value = ""
                tiro_minimo.value = ""
                tiro_maximo.value = ""
                tiro_promedio.value = ""
                apuestas_promedio.value = ""
                promedio_ocupacion.value = ""
                Zonaid.clear()
                Progresivos.clear()
                proveedor_lo_mas_jugado.value = ""
                producto_mas_jugado.value = ""
                id_producto_mas_jugado.value = 0
                fumar2.value = false
                nofumar2.value = false
                sap2.value = false
                lap2.value = false
            } catch (e: Exception) {
                Log.d("Exception", "LO MAS JUGADO", e)
            }
        }
    }

    /**COMENTARIOS GENERALES JUGADORES**/
    fun selectJuegoComentGeneralesJugadores(id: Int, nombre: String) {
        alertJuegosComentariosJugadores.value = false
        juego_comentarios.value = nombre
        id_juego_comentarios.value = id
    }

    fun addComentGeneralsJugadores() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dataComentariosGeneralesJugadores.add(
                    Comentarios(
                        juego = ID(id_juego_comentarios.value, juego_comentarios.value),
                        procedencia = procedencia_comentarios.value,
                        ingresos = ingresos_comentarios.value.toInt(),
                        comentario = comentarios_jugadores.value,
                        perfil = ID(id_perfil.value, perfil_comentarios.value),
                        tipo = calificacion_comentarios.value
                    )
                )
                positivo.value = true
                negativo.value = false
                calificacion_comentarios.value = true

                id_juego_comentarios.value = 0
                juego_comentarios.value = ""
                perfil_comentarios.value = ""
                id_perfil.value = 0
                procedencia_comentarios.value = ""
                ingresos_comentarios.value = ""
                comentarios_jugadores.value = ""

                Log.d("Success", "COMENTARIOS GENERALES ARRAY" + dataComentariosGeneralesJugadores)


            } catch (e: Exception) {
                Log.d("Exception", "COMENTARIOS GENERALES", e)
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
                //-------------------------------------------JUEGOS ZITRO---------------------------------------------------//
                val responseService2 = authService.getJuegosZitro(token, tipoId, tipoId)
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