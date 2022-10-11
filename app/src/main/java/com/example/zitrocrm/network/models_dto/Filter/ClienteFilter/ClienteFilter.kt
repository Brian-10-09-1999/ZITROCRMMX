package com.example.zitrocrm.network.models_dto.Filter.ClienteFilter

import com.google.gson.annotations.SerializedName

data class ClienteFilter(
    @SerializedName("ok"       ) var ok       : Boolean?            = null,
    @SerializedName("clientes" ) var clientes : ArrayList<Clientes> = arrayListOf()
)
