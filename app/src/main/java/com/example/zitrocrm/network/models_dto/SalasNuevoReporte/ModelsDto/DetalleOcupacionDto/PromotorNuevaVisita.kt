package com.example.zitrocrm.network.models_dto.DetalleOcupacionDto

import com.google.gson.annotations.SerializedName

data class  PromotorNuevaVisita (

  @SerializedName("visita"                   ) var visita                   : Visita?                             = Visita(),
  @SerializedName("ocupacion"                ) var ocupacion                : ArrayList<Ocupacion>                = arrayListOf(),
  @SerializedName("ocupacionSlots"           ) var ocupacionSlots           : ArrayList<OcupacionSlots>           = arrayListOf(),
  @SerializedName("acumulados"               ) var acumulados               : ArrayList<Acumulados>               = arrayListOf(),
  @SerializedName("masJugado"                ) var masJugado                : ArrayList<MasJugado>                = arrayListOf(),
  @SerializedName("comentariosGenerales"     ) var comentarios              : ArrayList<Comentarios>              = arrayListOf(),
  @SerializedName("comentariosSonido"        ) var comentariosSonido        : ArrayList<Sonido>                   = arrayListOf(),
  @SerializedName("observacionesCompetencia" ) var observacionesCompetencia : ArrayList<ObservacionesCompetencia> = arrayListOf(),
  @SerializedName("objetivos"                ) var objetivos                : ArrayList<Int>                      = arrayListOf(),
  @SerializedName("librerias"                ) var librerias                : ArrayList<Int>                      = arrayListOf(),
  @SerializedName("send"                     ) var send                     : Boolean?                            = false

)
