package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.JuegosFilter

import com.google.gson.annotations.SerializedName

data class Juegos (
    @SerializedName("id"       ) var id       : Int?    = null,
    @SerializedName("nombre"   ) var nombre   : String? = null
)