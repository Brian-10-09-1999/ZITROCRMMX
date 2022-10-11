package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ObjSemanalFilter

import com.google.gson.annotations.SerializedName

data class ObjSemanalDto(
    @SerializedName("ok"      ) var ok      : Boolean?           = null,
    @SerializedName("message" ) var message : ArrayList<Message> = arrayListOf()
)
