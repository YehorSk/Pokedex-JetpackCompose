package com.example.retrofit_4.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.retrofit_4.ui.screens.details.DetailsScreen
import com.example.retrofit_4.ui.screens.details.DetailsViewModel
import com.example.retrofit_4.ui.screens.home.HomeScreen
import com.example.retrofit_4.ui.screens.home.HomeViewModel

enum class PokemonScreen(){
    Start,
    Details
}



@Composable
fun PokemonScreen(modifier: Modifier = Modifier){
    val homeViewModel : HomeViewModel = viewModel(factory = HomeViewModel.Factory)
    val homeUiState by homeViewModel.homeUiState.collectAsState()
    val detailsViewModel: DetailsViewModel = viewModel(factory = DetailsViewModel.Factory)
    val detailsUiState = detailsViewModel.detailsUiState
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PokemonScreen.Start.name,
        modifier = modifier
    ){
        composable(route = PokemonScreen.Start.name){
            HomeScreen(viewModel = homeViewModel,
                onPokemonSelected = {
                    detailsViewModel.choosePokemon(it)
                    navController.navigate(PokemonScreen.Details.name)
                },
                homeUiState = homeUiState
            )
        }
        composable(route = PokemonScreen.Details.name){
            DetailsScreen(viewModel = detailsViewModel,detailsUiState = detailsUiState)
        }

    }


}