package com.example.zitrocrm.network.models_dto.DetalleOcupacionDto

import com.google.gson.annotations.SerializedName


data class Sonido (
  @SerializedName("clasificacionComentario"  ) var clasificacionComentario   : ID?  = null,
  @SerializedName("observaciones"            ) var observaciones             : String?  = null,
  @SerializedName("tipo"                     ) var tipo                      : Boolean? = null
)