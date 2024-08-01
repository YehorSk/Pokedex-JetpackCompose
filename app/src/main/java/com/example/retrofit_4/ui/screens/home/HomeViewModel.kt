package com.example.retrofit_4.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.retrofit_4.ApplicationContainer
import com.example.retrofit_4.data.models.Pokemon
import com.example.retrofit_4.data.repository.PokemonRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface HomeUiState{
    data class Success(val pokemons: List<Pokemon>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

class HomeViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    var homeUiState : HomeUiState by mutableStateOf(HomeUiState.Loading)

    init{
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch {
            homeUiState = try {
                HomeUiState.Success(pokemonRepository.getPokemons(25).results)
            }catch (e : IOException){
                HomeUiState.Error
            }
        }
    }

    companion object{
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ApplicationContainer)
                HomeViewModel(pokemonRepository = application.container.pokemonRepository)
            }
        }
    }

}