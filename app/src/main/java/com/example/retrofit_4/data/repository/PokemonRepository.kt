package com.example.retrofit_4.data.repository

import com.example.retrofit_4.data.models.PokemonResponse

interface PokemonRepository {

    suspend fun getPokemons(limit : Int) : PokemonResponse

}