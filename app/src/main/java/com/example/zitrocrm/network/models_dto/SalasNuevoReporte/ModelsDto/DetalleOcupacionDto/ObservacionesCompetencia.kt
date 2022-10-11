package com.example.zitrocrm.network.models_dto.DetalleOcupacionDto

import com.google.gson.annotations.SerializedName


data class ObservacionesCompetencia (

  @SerializedName("observaciones"    ) var observaciones    : String?  = null,
  @SerializedName("proveedor"        ) var proveedor        : ID?  = null,
  //@SerializedName("tipo"           ) var tipo             : Boolean? = null

)