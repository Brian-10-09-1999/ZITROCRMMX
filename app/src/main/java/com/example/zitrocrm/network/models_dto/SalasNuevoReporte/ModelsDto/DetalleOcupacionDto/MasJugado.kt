package com.example.zitrocrm.network.models_dto.DetalleOcupacionDto

import com.example.zitrocrm.network.models_dto.SalasNuevoReporte.ProveedorFilter.Rows
import com.google.gson.annotations.SerializedName


data class MasJugado (
  @SerializedName("apuestasPromedio"  ) var apuestasPromedio    : String?    = null,
  @SerializedName("juego"             ) var juego               : ID?     = null,
  @SerializedName("progresivos"       ) var progresivos         : ArrayList<Progresivos>   = arrayListOf(),
  @SerializedName("promedioOcupacion" ) var promedioOcupacion   : Int?    = null,
  @SerializedName("proveedor"         ) var proveedor           : Rows?   = null,
  @SerializedName("tiroMinimo"        ) var tiroMinimo          : Int?    = null,
  @SerializedName("tiroMaximo"        ) var tiroMaximo          : Int?    = null,
  @SerializedName("tiroPromedio"      ) var tiroPromedio        : Int?    = null,
  @SerializedName("zona"              ) var zona                : ArrayList<Zona>          = arrayListOf(),
  @SerializedName("unidadOcupacion"   ) var unidadOcupacion     : String?    = null,


  )
data class Zona(
  @SerializedName("id"    ) var id: String? = null,
  @SerializedName("zona"  ) var zona: String? = null
)

data class Progresivos(
  @SerializedName("id"           ) var id: String? = null,
  @SerializedName("progresivos"  ) var progresivos: String? = null
)