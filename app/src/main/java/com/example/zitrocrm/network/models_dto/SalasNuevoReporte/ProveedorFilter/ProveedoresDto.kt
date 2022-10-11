package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ProveedorFilter

import com.google.gson.annotations.SerializedName

data class ProveedoresDto(

    @SerializedName("ok"   ) var ok   : Boolean?        = null,
    @SerializedName("rows" ) var rows : ArrayList<Rows> = arrayListOf()

)