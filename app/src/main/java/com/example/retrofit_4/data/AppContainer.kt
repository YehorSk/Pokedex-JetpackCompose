package com.example.retrofit_4.data

import com.example.retrofit_4.data.repository.PokemonRepository
import com.example.retrofit_4.data.repository.PokemonRepositoryImpl
import com.example.retrofit_4.network.PokemonApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val pokemonRepository: PokemonRepository
}

class DefaultAppContainer : AppContainer{

    private val baseUrl = "https://pokeapi.co/api/v2/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val retrofitService : PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
    override val pokemonRepository: PokemonRepository by lazy {
        PokemonRepositoryImpl(retrofitService)
    }

}