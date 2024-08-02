package com.example.retrofit_4.ui.screens.details

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
import com.example.retrofit_4.data.models.PokemonStats
import com.example.retrofit_4.data.repository.PokemonRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DetailsUiState{
    data class Success(val pokemon: PokemonStats) : DetailsUiState
    object Error : DetailsUiState
    object Loading : DetailsUiState
}

class DetailsViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    var detailsUiState: DetailsUiState by mutableStateOf(DetailsUiState.Loading)
    var pokemonName: String by mutableStateOf("")

    fun choosePokemon(name: String){
        detailsUiState = DetailsUiState.Loading
        pokemonName = name
        getPokemonDetails()
    }

    fun getPokemonDetails() {
        viewModelScope.launch {
            detailsUiState = try{
                DetailsUiState.Success(pokemonRepository.getPokemonStats(pokemonName))
            }catch (e: IOException){
                DetailsUiState.Error
            }
        }
    }

    companion object{
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ApplicationContainer)
                DetailsViewModel(pokemonRepository = application.container.pokemonRepository)
            }
        }
    }


}