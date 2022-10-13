package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.JuegosFilter

import com.google.gson.annotations.SerializedName

data class SubJuegos(
    @SerializedName("ok"      ) var ok      : Boolean?           = null,
    @SerializedName("rows"    ) var rows :  ArrayList<SubJuegosArray> = arrayListOf()
)
