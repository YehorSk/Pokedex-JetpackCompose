package com.example.retrofit_4.ui.screens.home

import androidx.compose.foundation.shape.CutCornerShape
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    var homeUiState: StateFlow<HomeUiState> = _homeUiState
    private val itemsPerPage = 24
    private var currentOffset: Int = 0

    init{
        getPokemons()
    }

    fun getPokemons() {
        viewModelScope.launch {
            _homeUiState.value = try {
                HomeUiState.Success(pokemonRepository.getPokemons(itemsPerPage).results)
            }catch (e : IOException){
                HomeUiState.Error
            }
        }
    }

    fun getNextPagePokemons(){
        viewModelScope.launch {
            val currentState = _homeUiState.value
            if(currentState is HomeUiState.Success){
                _homeUiState.value = try {
                    currentOffset+=itemsPerPage
                    val response = pokemonRepository.getNextPagePokemons(offset = currentOffset, limit = itemsPerPage)
                    HomeUiState.Success(currentState.pokemons + response.results)
                }catch (e : IOException){
                    HomeUiState.Error
                }
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