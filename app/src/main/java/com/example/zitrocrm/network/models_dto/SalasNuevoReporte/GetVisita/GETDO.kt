package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.GetVisita

import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Horarios
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Juego
import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.Proveedor
import com.google.gson.annotations.SerializedName

data class GETDO (

    @SerializedName("ok"   ) var ok   : Boolean?        = null,
    @SerializedName("rows" ) var rows : ArrayList<RowsDO> = arrayListOf(),
    @SerializedName("msg"   ) var msg   : String?        = null,

)

data class RowsDO (

    @SerializedName("proveedor"   ) var proveedor   : Proveedor?          = Proveedor(),
    @SerializedName("juego"       ) var juego       : Juego?              = Juego(),
    @SerializedName("subjuego"    ) var subjuego    : Juego?              = Juego(),
    @SerializedName("tipo"        ) var tipo        : Int?                = null,
    @SerializedName("maquinas1"   ) var maquinas1   : Int?                = null,
    @SerializedName("maquinasLt1" ) var maquinasLt1 : Int?                = null,
    @SerializedName("total"       ) var total       : Int?                = null,
    @SerializedName("horarios"    ) var horarios    : ArrayList<Horarios> = arrayListOf()

)

data class RowsDO2 (

    @SerializedName("proveedor"     ) var proveedor     : String?             = null,
    @SerializedName("proveedoridfk" ) var proveedoridfk : Int?                = null,
    @SerializedName("juego"         ) var juego         : String?             = null,
    @SerializedName("juegoidfk"     ) var juegoidfk     : Int?                = null,
    @SerializedName("subjuego"      ) var subjuego      : String?             = null,
    @SerializedName("subjuegoidfk"  ) var subjuegoidfk  : Int?                = null,
    @SerializedName("maquinas1"     ) var maquinas1     : Int?                = null,
    @SerializedName("maquinasLt1"   ) var maquinasLt1   : Int?                = null,
    @SerializedName("total"         ) var total         : Int?                = null,
    @SerializedName("tipo"          ) var tipo          : Int?                = 0,
    @SerializedName("horarios"      ) var horarios      : ArrayList<Horarios> = arrayListOf(),

)