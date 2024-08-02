package com.example.retrofit_4.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofit_4.data.models.Pokemon


@Composable
fun PokemonGrid(pokemons: List<Pokemon>, onPokemonSelected: (String) -> Unit, loadMore: () -> Unit, modifier: Modifier = Modifier){
    LazyVerticalGrid(columns = GridCells.Fixed(2),modifier = modifier) {
        items(pokemons, key = {item -> item.id}){
            PokemonCard(pokemon = it,onPokemonSelected)
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = loadMore) {
                    Text(text = "Load More")
                }
            }
        }
    }
}