package com.example.zitrocrm.network.models_dto.CompetenciaJuegosBingo

import com.google.gson.annotations.SerializedName

data class Ficha (
    @SerializedName("accesoBonus"         ) var accesoBonus              : Int?               = null,
    @SerializedName("bolasExtra "         ) var bolasExtra               : Int?               = null,
    @SerializedName("bolasSorteadas "     ) var bolasSorteadas           : Int?               = null,
    @SerializedName("comodin"             ) var comodin                  : Boolean?           = null,
    @SerializedName("denominacionMax"     ) var denominacionMax          : String?            = null,
    @SerializedName("denominacionMin"     ) var denominacionMin          : String?            = null,
    @SerializedName("descripcion"         ) var descripcion              : String?            = null,
    @SerializedName("familia"             ) var familia                  : Int?               = null,
    @SerializedName("noCartones"          ) var noCartones               : Int?               = null,
    @SerializedName("noFiguras"           ) var noFiguras                : Int?               = null,
    @SerializedName("nombre"              ) var nombre                   : String?            = null,
    @SerializedName("otro"                ) var otro                     : String?            = null,
    @SerializedName("perfil"              ) var perfil                   : Int?               = null,
    @SerializedName("progresivos"         ) var progresivos              : Int?               = null,
    @SerializedName("proveedor"           ) var proveedor                : String?            = null,
    @SerializedName("tipoBonusGroup"      ) var tipoBonusGroup           : TipoBonusGroup?    = TipoBonusGroup(),
    @SerializedName("tipoCartones"        ) var tipoCartones             : Int?               = null,
    )

