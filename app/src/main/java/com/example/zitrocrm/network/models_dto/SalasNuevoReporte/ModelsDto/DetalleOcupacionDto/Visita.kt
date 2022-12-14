package com.example.zitrocrm.network.models_dto.DetalleOcupacionDto

import com.google.gson.annotations.SerializedName


data class Visita (

  @SerializedName("conclusion"            ) var conclusion             : String?         = "",
  @SerializedName("fecha"                  ) var fecha                 : Fecha?          = Fecha(),
  @SerializedName("horaEntrada"            ) var horaEntrada           : Int?            = 0,
  @SerializedName("horaSalida"             ) var horaSalida            : Int?            = 0,
  @SerializedName("objetivoSemanal"        ) var objetivoSemanal       : String?         = "",
  @SerializedName("observacionesGenerales") var observacionesGenerales : String?         = "",
  @SerializedName("propuestas"            ) var propuestas             : String?         = "",
  @SerializedName("queHacer"              ) var queHacer               : String?         = "",
  @SerializedName("salaid"                ) var salaid                 : Int?            = 0,
  @SerializedName("tipo"                  ) var tipo                   : Int?            = 0,
  @SerializedName("elabLayout"            ) var elabLayout             : Int?            = 0,
  @SerializedName("ocupacionHorario"      ) var ocupacionHorario       : ArrayList<String>  = arrayListOf()

  )

data class Fecha (
  @SerializedName("day" ) var day        : Int?  = 0,
  @SerializedName("month" ) var month    : Int?  = 0,
  @SerializedName("year" ) var year      : Int?  = 0,
  )

