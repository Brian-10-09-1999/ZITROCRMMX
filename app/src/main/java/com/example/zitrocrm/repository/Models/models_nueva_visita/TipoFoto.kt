package com.example.zitrocrm.repository.Models.models_nueva_visita

import android.graphics.Bitmap
import android.net.Uri
import android.util.Base64
import com.google.gson.annotations.SerializedName
import java.io.File

data class TipoFoto (
    @SerializedName("categoria"        ) var categoria        : Int?        = null,
    @SerializedName("idTipoFoto"       ) var idTipoFoto       : Int?        = null,
    @SerializedName("TipoFoto"         ) var TipoFoto         : String?     = null,
)

data class ArrayFoto(
    @SerializedName("Uri"              ) var Uri: Bitmap? = null,
    @SerializedName("TipoFoto"         ) var TipoFoto: TipoFoto?           = null,
)
data class DocPhotoRequest(
    @SerializedName("file"             ) var file: File? = null,
    @SerializedName("fileType"         ) var fileType: Int? = null,
)

