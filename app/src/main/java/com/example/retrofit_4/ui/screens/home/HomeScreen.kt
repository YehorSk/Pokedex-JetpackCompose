package com.example.retrofit_4.ui.screens.home


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.retrofit_4.ui.components.PokemonGrid
import com.example.retrofit_4.ui.screens.ErrorScreen
import com.example.retrofit_4.ui.screens.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Red
        ),
        title = {
            Text(
                text = "Pokedex",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        }
    )
}

@Composable
fun HomeScreen(viewModel: HomeViewModel,onPokemonSelected: (String) -> Unit,homeUiState: HomeUiState,modifier: Modifier = Modifier){

    Scaffold(
        topBar = {
            TopBar()
        }
    ) { innerPadding ->
        when(homeUiState){
            is HomeUiState.Success -> PokemonGrid(pokemons = (homeUiState as HomeUiState.Success).pokemons, loadMore = {viewModel.getNextPagePokemons()}, onPokemonSelected = onPokemonSelected, modifier = modifier.padding(innerPadding))
            is HomeUiState.Loading -> LoadingScreen(modifier = modifier.padding(innerPadding))
            is HomeUiState.Error -> ErrorScreen(retryAction = viewModel::getPokemons,modifier = modifier.padding(innerPadding))
        }
    }

}