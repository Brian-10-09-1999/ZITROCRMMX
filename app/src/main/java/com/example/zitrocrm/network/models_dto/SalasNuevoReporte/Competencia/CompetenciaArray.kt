package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.Competencia

import com.google.gson.annotations.SerializedName

data class CompetenciaArray (
    @SerializedName("id"          ) var id          : Int?              = null,
    @SerializedName("nombre"      ) var nombre      : String?           = null,
    @SerializedName("tipo"        ) var tipo        : Int?              = null,
        )
