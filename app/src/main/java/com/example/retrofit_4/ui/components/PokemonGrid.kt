package com.example.retrofit_4.ui.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.retrofit_4.data.models.Pokemon


@Composable
fun PokemonGrid(pokemons: List<Pokemon>, modifier: Modifier = Modifier, onClick: () -> Unit){
    LazyVerticalGrid(columns = GridCells.Fixed(2),modifier = modifier) {
        items(pokemons){
            PokemonCard(pokemon = it, onClick = onClick)
        }
    }
}