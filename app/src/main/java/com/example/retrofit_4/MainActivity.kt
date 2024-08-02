package com.example.retrofit_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit_4.navigation.PokemonScreen
import com.example.retrofit_4.ui.components.PokemonGrid
import com.example.retrofit_4.ui.screens.home.HomeScreen
import com.example.retrofit_4.ui.screens.home.HomeUiState
import com.example.retrofit_4.ui.screens.home.HomeViewModel
import com.example.retrofit_4.ui.theme.Retrofit_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            Retrofit_4Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PokemonScreen()
                }
            }
        }
    }
}

