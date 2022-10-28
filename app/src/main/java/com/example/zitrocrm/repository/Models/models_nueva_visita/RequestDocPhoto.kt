package com.example.zitrocrm.repository.Models.models_nueva_visita

import com.google.gson.annotations.SerializedName

data class RequestDocPhoto (
    @SerializedName("fileType"   ) var fileType   : ArrayList<Int>?        = null,
    @SerializedName("id"         ) var id         : Int?                   = null,
)