package com.example.retrofit_4.network

import com.example.retrofit_4.data.models.PokemonResponse
import com.example.retrofit_4.data.models.PokemonStats
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon/")
    suspend fun getPokemons(@Query("limit") limit: Int) : PokemonResponse

    @GET("pokemon/")
    suspend fun getNextPagePokemons(@Query("offset") offset: Int,@Query("limit") limit: Int) : PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getStats(@Path("name") name: String) : PokemonStats

}