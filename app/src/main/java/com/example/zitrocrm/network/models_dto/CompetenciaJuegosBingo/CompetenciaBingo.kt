package com.example.zitrocrm.network.models_dto.CompetenciaJuegosBingo

import com.google.gson.annotations.SerializedName

data class CompetenciaBingo(
    @SerializedName("competencia"           ) var competencia              : ArrayList<Competencia>    = arrayListOf(),
    @SerializedName("ficha"                 ) var ficha                    : Ficha?                    = Ficha(),
    @SerializedName("tipoBonusJuego"        ) var tipoBonusJuego           : ArrayList<TipoBonusJuego> = arrayListOf()
)
