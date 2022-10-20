package com.example.zitrocrm.repository.Models.models_nueva_visita

import android.net.Uri
import com.google.gson.annotations.SerializedName
import java.io.File

data class TipoFoto (
    @SerializedName("categoria"        ) var categoria        : Int?        = null,
    @SerializedName("idTipoFoto"       ) var idTipoFoto       : Int?        = null,
    @SerializedName("TipoFoto"         ) var TipoFoto         : String?     = null,
)

data class ArrayFoto(
    @SerializedName("Uri"              ) var Uri     : Uri?                = null,
    @SerializedName("TipoFoto"         ) var TipoFoto: TipoFoto?           = null,
)
data class ArrayFoto2(
    @SerializedName("Uri"              ) var Uri        : File? = null,
    @SerializedName("TipoFoto"         ) var TipoFoto   : TipoFoto?           = null,
)


