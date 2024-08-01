package com.example.retrofit_4.data.repository

import com.example.retrofit_4.data.models.PokemonResponse
import com.example.retrofit_4.network.PokemonApiService

class PokemonRepositoryImpl(
    private val pokemonApiService: PokemonApiService
) : PokemonRepository {

    override suspend fun getPokemons(limit: Int): PokemonResponse = pokemonApiService.getPokemons(limit)

}