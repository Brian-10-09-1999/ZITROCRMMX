package com.example.zitrocrm.network.models_dto.DetalleOcupacionDto

import com.google.gson.annotations.SerializedName


data class Acumulados (
  @SerializedName("evento"        ) var evento        : String?    = null,
  @SerializedName("fin"           ) var fin           : Int?       = null,
  @SerializedName("horaFin"       ) var horaFin       : String?    = null,
  @SerializedName("horaInicio"    ) var horaInicio    : String?    = null,
  @SerializedName("inicio"        ) var inicio        : Int?       = null,
  @SerializedName("premio"        ) var premio        : String?    = null,
  @SerializedName("proveedor"     ) var proveedor     : ID?        = null
)

data class ID (
  @SerializedName("id"            ) var id            : Int?     = null,
  @SerializedName("nombre"        ) var nombre        : String?  = null
)