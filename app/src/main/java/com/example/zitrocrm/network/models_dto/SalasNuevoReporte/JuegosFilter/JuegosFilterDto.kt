package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.JuegosFilter

import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ObjSemanalFilter.Message
import com.google.gson.annotations.SerializedName

data class JuegosFilterDto(
    @SerializedName("ok"      ) var ok      : Boolean?           = false,
    @SerializedName("juegos" ) var juegos :  ArrayList<Juegos> = arrayListOf()
)