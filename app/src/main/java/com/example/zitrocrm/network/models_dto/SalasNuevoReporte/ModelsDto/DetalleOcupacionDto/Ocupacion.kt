package com.example.zitrocrm.network.models_dto.DetalleOcupacionDto

import com.google.gson.annotations.SerializedName

data class Ocupacion (

    @SerializedName("horario"             ) var horario              : Int?                = null,
    @SerializedName("juegoidfk"           ) var juegoidfk            : Int?                = null,
    @SerializedName("maquinas1"           ) var maquinas1            : Int?                = null,
    @SerializedName("maquinaslt1"         ) var maquinasLt1          : Int?                = null,
    @SerializedName("ocupacionMaquinas1"  ) var ocupacionMaquinas1   : Int?                = null,
    @SerializedName("ocupacionMaquinaslt1") var ocupacionMaquinaslt1 : Int?                = null,
    @SerializedName("proveedoridfk"       ) var proveedoridfk        : Int?                = null,
    @SerializedName("subjuegoidfk"        ) var subjuegoidfk         : Int?                = null,
    @SerializedName("proveedor"           ) var proveedor            : String?             = null,
    @SerializedName("juego"               ) var juego                : String?             = null,
    @SerializedName("subjuego"            ) var subjuego             : String?             = null,
)

data class OcupacionSlots (

    @SerializedName("horario"             ) var horario               : Int?                = null,
    @SerializedName("juegoidfk"           ) var juegoidfk             : Int?                = null,
    @SerializedName("maquinas"            ) var maquinas1             : Int?                = null,
    @SerializedName("ocupacionMaquinas"   ) var ocupacionMaquinas1    : Int?                = null,
    @SerializedName("proveedoridfk"       ) var proveedoridfk         : Int?                = null,
    @SerializedName("subjuegoidfk"        ) var subjuegoidfk          : Int?                = null,
    @SerializedName("proveedor"           ) var proveedor             : String?             = null,
    @SerializedName("juego"               ) var juego                 : String?             = null,
    @SerializedName("subjuego"            ) var subjuego              : String?             = null,

)