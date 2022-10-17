package com.example.zitrocrm.network.models_dto.DetalleOcupacionDto

import com.google.gson.annotations.SerializedName


data class Comentarios (

  @SerializedName("juego"         ) var juego         : ID?      = null,
  @SerializedName("subjuego"      ) var subjuego      : ID?      = null,
  @SerializedName("perfil"        ) var perfil        : ID?      = null,
  @SerializedName("procedencia"   ) var procedencia   : String?  = null,
  @SerializedName("ingresos"      ) var ingresos      : Int?     = null,
  @SerializedName("comentario"    ) var comentario    : String?  = null,
  @SerializedName("tipo"          ) var tipo          : Boolean? = false

)
