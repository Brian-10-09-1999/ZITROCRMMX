package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ObjSemanalFilter

import com.google.gson.annotations.SerializedName

data class Message (
    @SerializedName("id"       ) var id       : Int?    = null,
    @SerializedName("objetivo" ) var objetivo : String? = null
)