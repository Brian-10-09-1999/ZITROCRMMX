package com.example.zitrocrm.network.models_dto.Filter.RegionesFilter

import com.google.gson.annotations.SerializedName

data class Regiones(
    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("nombre" ) var nombre : String? = null
)
