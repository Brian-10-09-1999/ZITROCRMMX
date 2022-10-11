package com.example.zitrocrm.network.models_dto.Filter.SalasFilter

import com.google.gson.annotations.SerializedName

data class Salas(
    @SerializedName("id"          ) var id:           Int?    = null,
    @SerializedName("nombre"      ) var nombre:       String? = null,
    @SerializedName("clienteidfk" ) var clienteidfk:  String? = null,
    @SerializedName("OfficeID"    ) var OfficeID:     String? = null
)
