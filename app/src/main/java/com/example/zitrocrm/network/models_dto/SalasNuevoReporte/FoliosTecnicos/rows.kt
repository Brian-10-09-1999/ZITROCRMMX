package com.example.zitrocrm.network.models_dto.SalasNuevoReporte.FoliosTecnicos

import com.google.gson.annotations.SerializedName

data class rows (

    @SerializedName("nombre" )            var nombre           : String? = null,
    @SerializedName("Seq"               ) var Seq              : String? = null,
    @SerializedName("count"             ) var count            : Int?    = null,
    @SerializedName("descripcion_falla" ) var descripcionFalla : String? = "Sin detalles",
    @SerializedName("estatus"           ) var estatus          : String? = null,
    @SerializedName("fecha"             ) var fecha            : String? = null,
    @SerializedName("gabinete"          ) var gabinete         : String? = null,
    @SerializedName("juego"             ) var juego            : String? = null,
    @SerializedName("licencia"          ) var licencia         : String? = null,
    @SerializedName("medio"             ) var medio            : String? = null,
    @SerializedName("motivo"            ) var motivo           : String? = null,
    @SerializedName("numero_folio"      ) var numeroFolio      : Int?    = null,
    @SerializedName("origen"            ) var origen           : String? = null,
    @SerializedName("serie"             ) var serie            : String? = null

)