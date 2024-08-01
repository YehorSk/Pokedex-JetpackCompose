package com.example.retrofit_4.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.retrofit_4.R
import com.example.retrofit_4.data.models.Pokemon
import org.jetbrains.annotations.Async
import java.util.Locale

@Composable
fun PokemonCard(pokemon: Pokemon, modifier: Modifier = Modifier, onClick: () -> Unit){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(10.dp),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Color.White)
        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${extractNumberFromUrl(pokemon.url)}.png",
                contentDescription = pokemon.name,
                placeholder = painterResource(id = R.drawable.loading_img),
                error = painterResource(id = R.drawable.ic_broken_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun PokemonCardPreview(){
    PokemonCard(
        pokemon = Pokemon("Bulbasaur",""), onClick = {}
    )
}

fun extractNumberFromUrl(url: String): Int? {
    val regex = """/(\d+)/$""".toRegex()
    val matchResult = regex.find(url)
    return matchResult?.groups?.get(1)?.value?.toInt()
}