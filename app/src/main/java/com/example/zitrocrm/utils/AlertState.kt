package com.example.zitrocrm.utils

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlertState : ViewModel() {
    val alert_type = mutableStateOf("")
    val tittle_alert = mutableStateOf("")
    val content_alert = mutableStateOf("")

    fun TypeAlert (type:Int,tittle:String,content:String,short:Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                tittle_alert.value = tittle
                content_alert.value = content
                when (type){
                    1-> {
                        alert_type.value = "LOADING"
                    }
                    2-> {
                        alert_type.value = "INFO"
                    }
                    3->{
                        alert_type.value = "SUCCESS"
                    }
                    4->{
                        alert_type.value = "ERROR"
                    }
                    5->{
                        alert_type.value = "WARNING"
                    }
                    0-> alert_type.value = ""
                }
                if(type!=1||type!=0){
                    if(short) {
                        delay(2500)
                        alert_type.value = ""
                    }
                    else {
                        delay(4000)
                        alert_type.value = ""
                    }
                }
            }catch (e:Exception){

            }

        }
    }
}