package com.example.zitrocrm.network.models_dto.DetalleOcupacionDto

import com.google.gson.annotations.SerializedName

data class Ocupacion (

  @SerializedName("horario"             ) var horario              : Int?                = 0,
  @SerializedName("juegoidfk"           ) var juegoidfk            : Int?                = 0,
  @SerializedName("maquinas1"           ) var maquinas1            : Int?                = 0,
  @SerializedName("maquinaslt1"         ) var maquinasLt1          : Int?                = 0,
  @SerializedName("ocupacionMaquinas1"  ) var ocupacionMaquinas1   : Int?                ,
  @SerializedName("ocupacionMaquinaslt1") var ocupacionMaquinaslt1 : Int?                ,
  @SerializedName("proveedoridfk"       ) var proveedoridfk        : Int?                = 0,
  @SerializedName("subjuegoidfk"        ) var subjuegoidfk         : Int?                = 0,
  @SerializedName("proveedor"           ) var proveedor            : String?,
  @SerializedName("juego"               ) var juego                : String?,
  @SerializedName("subjuego"            ) var subjuego             : String?,
)

data class OcupacionSlots (

  @SerializedName("horario"             ) var horario               : Int?                = 0,
  @SerializedName("juegoidfk"           ) var juegoidfk             : Int?                = 0,
  @SerializedName("maquinas"            ) var maquinas1             : Int?                = 0,
  @SerializedName("ocupacionMaquinas"   ) var ocupacionMaquinas1    : Int?                = 0,
  @SerializedName("proveedoridfk"       ) var proveedoridfk         : Int?                = 0,
  @SerializedName("subjuegoidfk"        ) var subjuegoidfk          : Int?                = 0,
  @SerializedName("proveedor"           ) var proveedor             : String?,
  @SerializedName("juego"               ) var juego                 : String?,
  @SerializedName("subjuego"            ) var subjuego              : String?,

)