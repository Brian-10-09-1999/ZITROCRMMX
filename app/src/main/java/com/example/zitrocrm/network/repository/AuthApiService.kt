package com.example.zitrocrm.network.repository

import com.example.zitrocrm.network.models_dto.CompetenciaJuegosBingo.PrioridadesDTO
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.*
import com.example.zitrocrm.network.models_dto.Filter.ClienteFilter.ClienteFilter
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ObjSemanalFilter.ObjSemanalDto
import com.example.zitrocrm.network.models_dto.Filter.RegionesFilter.RegionesFilter
import com.example.zitrocrm.network.models_dto.Filter.SalasFilter.SalasFilter
import com.example.zitrocrm.network.models_dto.Login.LoginDto
import com.example.zitrocrm.network.models_dto.Login.LoginrespDto
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.Competencia.CompetenciaLibs
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.JuegosFilter.JuegosFilterDto
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.FoliosTecnicos.FoliosTecnicosDTO
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.GetVisita.GETDO
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.JuegosFilter.SubJuegos
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.PostVisitaPromotor
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ProveedorFilter.ProveedoresDto
import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ProveedorFilter.Rows
import com.example.zitrocrm.utils.Val_Constants
import retrofit2.Response
import retrofit2.http.*

interface AuthApiService {

    @Headers("Content-Type: application/json")
    @POST(Val_Constants.API_LOGIN)
    suspend fun getLogin(
        @Body loginDto: LoginDto
    ) : LoginrespDto

    @GET(Val_Constants.API_FILTER_CLIENTES)
    suspend fun getCliente(
        @Header("x-token") token:String)
    : ClienteFilter

    @GET(Val_Constants.API_FILTER_REGIONES)
    suspend fun getRegiones(
        @Header("x-token") token:String)
    : RegionesFilter

    @GET(Val_Constants.API_FILTER_SALAS)
    suspend fun getSala(
        @Header("x-token") token:String,
        @Query("cliente") cliente:Int,
        @Query("region") region : Int)
    : SalasFilter

    @GET(Val_Constants.API_FILTER_OBJETIVOS_GENERALES)
        suspend fun getObjSemanal(
        @Header("x-token") token:String,
        @Query("tipoId") tipoId:Int)
    : ObjSemanalDto

    @GET(Val_Constants.API_FILTER_PROVEEDORES)
    suspend fun getProveedores(
        @Header("x-token") token:String,
        @Query("tipo") tipo: Int)
    : Response<ProveedoresDto>

    @GET(Val_Constants.API_CATALOGOS_JUEGOS)
    suspend fun getJuegosZitro(
        @Header("x-token") token: String?,
        @Query("tipo") tipo:Int?,
        @Query("clasificacion") clasificacion :Int?
    )
    : Response<JuegosFilterDto>

    @GET(Val_Constants.API_SALAS_FOLIO_TECNICO)
    suspend fun getFoliosTecnicosSalas(
        @Header("x-token") token : String,
        @Query("sala") salaid :Int,
        @Query("cliente") clienteid : Int )
    : Response<FoliosTecnicosDTO>

    @POST(Val_Constants.API_SALAS_VISITAS_PROMOTORES_VISITA)
    suspend fun postSalaVisitaPromotores(
        @Header("x-token") token :String,
        @Body PromotorNuevaVisita: PromotorNuevaVisita)
    : Response<PostVisitaPromotor>

    @GET(Val_Constants.API_SALAS_COMPETENCIA_LIBRERIAS)
    suspend fun getSalasLibrerias(
        @Header("x-token")token : String,
        @Query("tipo") tipo : Int,
        @Query("proveedor") proveedorid : Int,
    ): CompetenciaLibs

    @GET(Val_Constants.API_SALAS_SUBJUEGOS)
    suspend fun getSubJuegos (
        @Header("x-token") token :String,
        @Query("tipo") tipo:Int)
    : Response<SubJuegos>

    @GET(Val_Constants.API_JUEGOS_TIPO_CARTONES)
    suspend fun getTipoCartones (
        @Header("x-token") token : String )
    : ArrayList<Rows>

    @GET(Val_Constants.API_FAMILIA_BINGO_JUEGOS)
    suspend fun getFamiliaBingoJuegos (
        @Header("x-token") token: String )
    : ArrayList<Rows>

    @GET(Val_Constants.API_ORDENES_CAMBIO)
    suspend fun getOrdenesCambio (
        @Header("x-token") token: String )
    : PrioridadesDTO

    @GET(Val_Constants.API_ULTIMO_DO)
    suspend fun getUltimoDO (
        @Header("x-token") token: String,
        @Query("tipo") tipo:Int)
    : GETDO
}