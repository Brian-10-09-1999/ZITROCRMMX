package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.JuegosFilter

import com.google.gson.annotations.SerializedName

data class JuegosFilterDto(
    @SerializedName("ok"      ) var ok      : Boolean?           = false,
    @SerializedName("juegos" ) var juegos :  ArrayList<Juegos> = arrayListOf()
)