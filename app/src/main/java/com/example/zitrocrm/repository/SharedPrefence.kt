package com.example.zitrocrm.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.zitrocrm.network.models_dto.Login.UsuarioDto

class SharedPrefence(val context : Context?) {
    
    private val PREFSH = "CrmZitro"
    val sharedPref : SharedPreferences = context!!.getSharedPreferences(PREFSH, Context.MODE_PRIVATE)
    val editor : SharedPreferences.Editor = sharedPref.edit()

    val PREF_LOGIN_INICIO = "login_inicio_share"
    val PREF_LOGIN_USER = "login_user_share"
    val PREF_LOGIN_PASS = "login_pass_share"
    /**USER INFO LOGIN**/
    val PREF_USER =         "user"
    val PREF_USERID =       "user_id"
    val PREF_NUM_EMPLEADO = "num_empleado"
    val PREF_NAME =         "name_user"
    val PREF_LASTNAME =     "last_name"
    val PREF_PASS =         "pass_user"
    val PREF_AREA =         "area_user"
    val PREF_STATUS_ID =    "status_idfk"
    val PREF_EMAIL =        "email_user"
    val PREF_DEPARTAMENT =  "departament_user"
    val PREF_LANGUAGE =     "lenguage_user"
    val PREF_PHONE =        "phone_user"
    val PREF_COM_REGION =   "com_region_use"
    val PREF_CRM_ROLL_IDFK = "crm_rol_idfk"
    val PREF_TOKEN =        "token"

    /**FILTERS**/
    val PREF_FILT_IDCLIENTE = "filter_id_cliente"
    val PREF_FILT_CLIENTE = "filter_cliente"

    val PREF_ID_REGION = "filter_id_region"
    val PREF_REGION = "filter_region"

    val PREF_FILT_IDSALAS = "filter_id_sala"
    val PREF_FILT_SALAS_NAME = "filter_salas_name"
    val PREF_OFFICE_ID_SALA = "office_id_sala"

    fun saveCLiente(idCliente: Int,Cliente : String){
        editor.putInt(PREF_FILT_IDCLIENTE, idCliente)
        editor.putString(PREF_FILT_CLIENTE, Cliente)
        editor.commit()
    }
    fun saveRegionInf(idRegion: Int, Region: String){
        editor.putInt(PREF_ID_REGION, idRegion)
        editor.putString(PREF_REGION, Region)
        editor.commit()
    }
    fun saveSala(idSala: Int?, name: String, office_id: Int?){
        editor.putInt(PREF_FILT_IDSALAS, idSala!!)
        editor.putString(PREF_FILT_SALAS_NAME, name)
        editor.putInt(PREF_OFFICE_ID_SALA, office_id!!)
        editor.commit()
    }
    fun saveinicio(inicio:Boolean?){
        if (inicio!!){
            editor.putString(PREF_LOGIN_INICIO, "true")
        }else{
            editor.putString(PREF_LOGIN_INICIO, "false")
        }
        editor.commit()
    }

    fun saveUser(user: String, pass: String, value: UsuarioDto, token: String){
        editor.putString(PREF_LOGIN_USER, user)
        editor.putString(PREF_LOGIN_PASS, pass)
        editor.putString(PREF_USER, value.user)
        value.userId?.let { editor.putInt(PREF_USERID, it) }
        value.numberEmpleado?.let { editor.putInt(PREF_NUM_EMPLEADO, it) }
        editor.putString(PREF_NAME, value.name)
        editor.putString(PREF_LASTNAME, value.lastName)
        editor.putString(PREF_PASS, value.pass)
        editor.putString(PREF_AREA, value.area)
        value.statuId?.let { editor.putInt(PREF_STATUS_ID, it) }
        editor.putString(PREF_EMAIL, value.email)
        editor.putString(PREF_DEPARTAMENT, value.departament)
        value.language?.let { editor.putInt(PREF_LANGUAGE, it) }
        editor.putString(PREF_PHONE, value.phone)
        value.comRegion?.let { editor.putInt(PREF_COM_REGION, it) }
        value.crmRolIdFk?.let { editor.putInt(PREF_CRM_ROLL_IDFK, it) }
        editor.putString(PREF_TOKEN, token)
        editor.commit()

    }

    fun clearSharedPreference(){
        editor.clear()
        editor.commit()
    }

    fun getInicioLogin(): String? {return sharedPref.getString(PREF_LOGIN_INICIO,"false")}

    fun getUserLogin() : String?{ return sharedPref.getString(PREF_LOGIN_USER, "")}

    fun getPassLogin() : String?{ return sharedPref.getString(PREF_LOGIN_PASS, null)}

    fun getToken(): String?{ return sharedPref.getString(PREF_TOKEN, null)}

    fun getUserId(): String?{ return sharedPref.getString(PREF_USERID, null)}

    fun getUser(): String?{ return sharedPref.getString(PREF_USER, null)}

    fun getName() : String?{ return sharedPref.getString(PREF_NAME, null)}

    fun getLastName() : String?{ return sharedPref.getString(PREF_LASTNAME, null)}

    fun getPass() : String?{ return sharedPref.getString(PREF_PASS, null)}

    fun getArea() : String?{ return sharedPref.getString(PREF_AREA, null)}

    fun getStatusId() : String?{ return sharedPref.getString(PREF_STATUS_ID, null)}

    fun getEmail() : String?{ return sharedPref.getString(PREF_EMAIL, null)}

    fun getDepartament() : String?{ return sharedPref.getString(PREF_DEPARTAMENT, null)}

    fun getLanguage() : String?{ return sharedPref.getString(PREF_LANGUAGE, null)}

    fun getTelefono() : String?{ return sharedPref.getString(PREF_PHONE, null)}

    fun getNumEmpleado() : String?{ return sharedPref.getString(PREF_NUM_EMPLEADO, null)}

    fun getRegionComercial() : String?{ return sharedPref.getString(PREF_COM_REGION, "")}

    fun getCliente() : String?{ return sharedPref.getString(PREF_FILT_CLIENTE, "seleccionar cliente")}

    fun getIDCliente() : Int{return sharedPref.getInt(PREF_FILT_IDCLIENTE, 0)}

    fun getSala() : String?{ return sharedPref.getString(PREF_FILT_SALAS_NAME, "seleccionar sala")}

    fun getRegion() : String?{ return sharedPref.getString(PREF_REGION, "seleccionar region")}

    fun getRegionId() : Int{ return sharedPref.getInt(PREF_ID_REGION, 0)}

    fun getSalaId() : Int { return sharedPref.getInt(PREF_FILT_IDSALAS, 0) }

    fun getRollIdfk() : Int {return sharedPref.getInt(PREF_CRM_ROLL_IDFK, 0)}

}