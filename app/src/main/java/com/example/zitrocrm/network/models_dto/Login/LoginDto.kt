package com.example.zitrocrm.network.models_dto.Login

import com.google.gson.annotations.SerializedName

data class LoginDto (
    @SerializedName("usuario") val usuario:String,
    @SerializedName("pwd") val pwd:String
)