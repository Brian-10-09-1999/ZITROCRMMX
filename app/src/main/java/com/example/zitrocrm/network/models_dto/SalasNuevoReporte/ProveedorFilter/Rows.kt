package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ProveedorFilter

import com.google.gson.annotations.SerializedName

data class Rows (
    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("nombre" ) var nombre : String? = null,
    @SerializedName("tipo"   ) var tipo   : Int?    = null,
)

