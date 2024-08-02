package com.example.retrofit_4.data.repository

import com.example.retrofit_4.data.models.PokemonResponse
import com.example.retrofit_4.data.models.PokemonStats

interface PokemonRepository {

    suspend fun getPokemons(limit : Int) : PokemonResponse

    suspend fun getNextPagePokemons(offset: Int, limit: Int) : PokemonResponse

    suspend fun getPokemonStats(name: String): PokemonStats

}