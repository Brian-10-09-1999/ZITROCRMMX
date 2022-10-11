package com.example.zitrocrm.network.models_dto.Filter.ClienteFilter

import com.google.gson.annotations.SerializedName

data class Clientes(
    @SerializedName("id"    ) val id: Int? = null,
    @SerializedName("nombre") val nombre: String? = null
)


