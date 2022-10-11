package com.example.zitrocrm.network.models_dto.CompetenciaJuegosBingo

import com.google.gson.annotations.SerializedName

data class Prioridades (

    @SerializedName("id"       )   var id        : Int?       = null,
    @SerializedName("prioridad")   var prioridad : String ?   = null

)