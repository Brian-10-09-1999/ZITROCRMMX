package com.example.zitrocrm.network.models_dto.SalasNuevoReporte

import com.example.zitrocrm.network.models_dto.DetalleOcupacionDto.ID
import com.google.gson.annotations.SerializedName

data class PostVisitaPromotor (
    @SerializedName("ok"       ) var ok           : Boolean?     = null,
    @SerializedName("msg"      ) var msg          : String?      = null,
    @SerializedName("message"  ) var message      : ID?          = null,
)