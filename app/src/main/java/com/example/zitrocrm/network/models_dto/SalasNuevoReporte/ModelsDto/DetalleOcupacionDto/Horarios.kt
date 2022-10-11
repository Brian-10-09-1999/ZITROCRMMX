package com.example.zitrocrm.network.models_dto.DetalleOcupacionDto

import com.google.gson.annotations.SerializedName


data class Horarios (
  @SerializedName("horario"      ) var horario      : String? = "",
  @SerializedName("ocupacion1"   ) var ocupacion1   : String? = "",
  @SerializedName("ocupacionLt1" ) var ocupacionLt1 : String? = "",
  @SerializedName("porcentaje"   ) var porcentaje   : String? = "",
)
