package com.example.zitrocrm.network.models_dto.Filter.RegionesFilter

import com.google.gson.annotations.SerializedName

data class RegionesFilter(
    @SerializedName("ok"       ) var ok       : Boolean?            = null,
    @SerializedName("regiones" ) var regiones : ArrayList<Regiones> = arrayListOf()
)
