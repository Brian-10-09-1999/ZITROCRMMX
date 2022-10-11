package com.example.zitrocrm.network.models_dto.Login

import com.google.gson.annotations.SerializedName

data class UsuarioDto (
    @SerializedName("usuarioid")         val userId         : Int?,
    @SerializedName("usuario")           val user           : String?,
    @SerializedName("nombre")            val name           : String?,
    @SerializedName("apellido_paterno")  val lastName       : String?,
    @SerializedName("contrasena")        val pass           : String?,
    @SerializedName("area")              val area           : String?,
    @SerializedName("estatusidfk")       val statuId        : Int?,
    @SerializedName("correo")            val email          : String?,
    @SerializedName("departamento")      val departament    : String?,
    @SerializedName("Idioma")            val language       : Int?,
    @SerializedName("telefono")          val phone          : String?,
    @SerializedName("no_empleado")       val numberEmpleado : Int?,
    @SerializedName("regionComercial")   val comRegion      : Int?,
    @SerializedName("crmRolIdFk")        val crmRolIdFk     : Int?
)