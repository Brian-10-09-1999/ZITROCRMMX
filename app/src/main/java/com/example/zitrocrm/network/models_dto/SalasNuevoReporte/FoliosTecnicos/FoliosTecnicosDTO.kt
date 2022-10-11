package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.FoliosTecnicos

import com.google.gson.annotations.SerializedName

data class FoliosTecnicosDTO(

    @SerializedName("ok"   ) var ok   : Boolean?        = null,
    @SerializedName("rows" ) var rows : ArrayList<rows> = arrayListOf(),
    @SerializedName("count" ) var count : Int?            = null,
    @SerializedName("msg"   ) var msg   : String?         = null

)