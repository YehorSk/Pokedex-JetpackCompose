package com.example.retrofit_4.data.repository

import com.example.retrofit_4.data.models.PokemonResponse
import com.example.retrofit_4.data.models.PokemonStats
import com.example.retrofit_4.network.PokemonApiService

class PokemonRepositoryImpl(
    private val pokemonApiService: PokemonApiService
) : PokemonRepository {

    override suspend fun getPokemons(limit: Int): PokemonResponse = pokemonApiService.getPokemons(limit)

    override suspend fun getNextPagePokemons(offset: Int, limit: Int): PokemonResponse = pokemonApiService.getNextPagePokemons(offset,limit)

    override suspend fun getPokemonStats(name: String): PokemonStats = pokemonApiService.getStats(name)

}