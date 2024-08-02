package com.example.retrofit_4.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val count: Int,
    val next: String?=null,
    val previous: String?=null,
    val results: List<Pokemon>
)

@Serializable
data class Pokemon(
    val name: String,
    val url: String
){
    val id: Int
        get() {
            val regex = """/(\d+)/$""".toRegex()
            return regex.find(url)?.groupValues?.get(1)?.toInt() ?: 0
        }
    val imgLink: String
        get(){
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${extractNumberFromUrl(url)}.png"
        }
}

fun extractNumberFromUrl(url: String): Int? {
    val regex = """/(\d+)/$""".toRegex()
    val matchResult = regex.find(url)
    return matchResult?.groups?.get(1)?.value?.toInt()
}

@Serializable
data class PokemonStats(
    val abilities: List<abilities>,
    val height: Int,
    val weight: Int,
    val id: Int,
    val name: String,
    val stats: List<Stats>
){
    val imgLink: String
        get(){
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
        }
}

@Serializable
data class abilities(
    val ability: Ability,
    val is_hidden: Boolean,
    val slot: Int
)

@Serializable
data class Ability(
    val name: String,
    val url: String
)

@Serializable
data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
)

@Serializable
data class Stat(
    val name: String,
    val url: String
)