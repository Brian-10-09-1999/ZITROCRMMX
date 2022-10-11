package com.example.zitrocrm.network.models_dto.CompetenciaJuegosBingo

import com.google.gson.annotations.SerializedName

data class Competencia(
    @SerializedName("observaciones"     ) var observaciones          : String?               = null,
    @SerializedName("tipo"              ) var tipo                   : Boolean?              = null,
)
