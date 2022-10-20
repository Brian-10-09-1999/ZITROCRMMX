package com.example.zitrocrm

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zitrocrm.navigation.Alert_State
import com.example.zitrocrm.navigation.NavigationScreen
import com.example.zitrocrm.screens.login.components.ProgressBarLoading
import com.example.zitrocrm.ui.theme.ZITROCRMTheme
import com.example.zitrocrm.utils.AlertState
import java.time.Instant
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    private val exampleLiveData = MutableLiveData("")

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZITROCRMTheme() {
                val alertState: AlertState = viewModel()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationScreen()
                    //Alert_State(alertState)
                    ProgressBarLoading(isLoading = 0)
                }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        exampleLiveData.value = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
    }
}







