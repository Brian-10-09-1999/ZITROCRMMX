package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.Competencia

import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.JuegosFilter.SubJuegosArray
import com.google.gson.annotations.SerializedName

data class CompetenciaLibs(
    @SerializedName("ok"      ) var ok        : Boolean?           = null,
    @SerializedName("rows"    ) var librerias :  ArrayList<CompetenciaArray> = arrayListOf()
)
