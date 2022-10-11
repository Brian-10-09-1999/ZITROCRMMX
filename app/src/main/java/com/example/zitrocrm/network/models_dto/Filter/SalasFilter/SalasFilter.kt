package com.example.zitrocrm.network.models_dto.Filter.SalasFilter

import com.google.gson.annotations.SerializedName

data class SalasFilter(
    @SerializedName("ok") var ok: Boolean?            = null,
    @SerializedName("salas") var salas:ArrayList<Salas> = arrayListOf()
)
