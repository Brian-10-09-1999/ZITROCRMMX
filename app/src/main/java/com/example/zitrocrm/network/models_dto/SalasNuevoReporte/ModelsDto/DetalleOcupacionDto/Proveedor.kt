package com.example.zitrocrm.network.models_dto.DetalleOcupacionDto

import com.google.gson.annotations.SerializedName


data class Proveedor (

  @SerializedName("id"     ) var id     : Int?    = 0,
  @SerializedName("nombre" ) var nombre : String? = null

)