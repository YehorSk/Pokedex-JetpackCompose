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
)

@Serializable
data class PokemonStats(
    val abilities: List<abilities>,
    val height: Int,
    val weight: Int,
    val stats: List<Stats>
)

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