package com.example.zitrocrm.network.models_dto.CompetenciaJuegosBingo

import com.google.gson.annotations.SerializedName

data class TipoBonusGroup (
    @SerializedName("mejorDe3"           ) var mejorDe3             : Boolean?            = null,
    @SerializedName("otro"               ) var otro                 : Boolean?            = null,
    @SerializedName("partidaGratis"      ) var partidaGratis        : Boolean?            = null,
    @SerializedName("seleccion"          ) var seleccion            : Boolean?            = null,
    @SerializedName("slots"              ) var slots                : Boolean?            = null,
    )
