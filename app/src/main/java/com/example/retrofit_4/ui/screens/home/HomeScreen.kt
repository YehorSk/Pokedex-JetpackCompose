package com.example.retrofit_4.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofit_4.ui.components.PokemonGrid
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Pokedex",
                style = MaterialTheme.typography.titleLarge
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val viewModel : HomeViewModel = viewModel(factory = HomeViewModel.Factory)
    val homeUiState = viewModel.homeUiState
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopBar() }
    ) { innerPadding ->
        when(homeUiState){
            is HomeUiState.Success -> PokemonGrid(pokemons = homeUiState.pokemons, modifier = Modifier.padding(innerPadding)) {
                if(!showBottomSheet){
                    showBottomSheet = true
                }
            }

            is HomeUiState.Loading -> Text(text = "Loading", modifier = Modifier.padding(innerPadding))
            is HomeUiState.Error -> Text(text = "Error", m3333333333333odifier = Modifier.padding(innerPadding))
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                // Sheet content
                Button(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }) {
                    Text("Hide bottom sheet")
                }
            }
        }
    }
}