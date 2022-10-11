package com.example.zitrocrm.network.models_dto.CompetenciaJuegosBingo

import com.google.gson.annotations.SerializedName

data class PrioridadesDTO (

    @SerializedName("ok"             ) var ok            : Boolean?               = null,
    @SerializedName("prioridades"    ) var prioridades   : ArrayList<Prioridades> = arrayListOf(),

)