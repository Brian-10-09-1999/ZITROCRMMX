package com.example.zitrocrm.network.models_dto.CompetenciaJuegosBingo

import com.google.gson.annotations.SerializedName

data class TipoBonusJuego (
    @SerializedName("id"                 ) var id                      : Int?                   = null,
    @SerializedName("otro"               ) var otro                    : String?                = null,
    @SerializedName("value"              ) var value                   : Boolean?               = null,
    )
