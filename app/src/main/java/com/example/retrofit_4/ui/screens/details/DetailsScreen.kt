package com.example.retrofit_4.ui.screens.details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.retrofit_4.R
import com.example.retrofit_4.data.models.Ability
import com.example.retrofit_4.data.models.PokemonStats
import com.example.retrofit_4.data.models.Stat
import com.example.retrofit_4.data.models.Stats
import com.example.retrofit_4.data.models.abilities
import com.example.retrofit_4.ui.components.PokemonProgressBar
import com.example.retrofit_4.ui.screens.ErrorScreen
import com.example.retrofit_4.ui.screens.LoadingScreen
import com.example.retrofit_4.ui.theme.Retrofit_4Theme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random

@Composable
fun DetailsScreen(navigateUp: () -> Unit = {},viewModel: DetailsViewModel,detailsUiState: DetailsUiState,modifier: Modifier = Modifier){
    when(detailsUiState){
        is DetailsUiState.Success -> DetailsFullScreen(pokemon = detailsUiState.pokemon,modifier = modifier, navigateUp = navigateUp)
        is DetailsUiState.Loading -> LoadingScreen(modifier = modifier)
        is DetailsUiState.Error -> ErrorScreen(retryAction = viewModel::getPokemonDetails,modifier)
    }
}

@Composable
fun DetailsFullScreen(navigateUp: () -> Unit = {},pokemon: PokemonStats,modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        DetailsTopBar(navigateUp = navigateUp,pokemon)
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold
                )
            Spacer(modifier = Modifier.height(20.dp))
            AbilityGrid(pokemon.abilities)
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Base Stats",
                style = MaterialTheme.typography.titleLarge
            )
            for(stat in pokemon.stats){
                PokemonStatsBar(stat)
            }
        }
    }
}

@Composable
fun DetailsTopBar(navigateUp: () -> Unit,pokemon: PokemonStats, modifier: Modifier = Modifier){
    val shape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 64.dp,
        bottomEnd = 64.dp,
    )

    Box(
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth()
            .shadow(elevation = 9.dp, shape = shape)
            .background(Color.White, shape = shape),
    ){
        Row(
            modifier = Modifier.padding(16.dp).statusBarsPadding()
        ) {
            IconButton(onClick = navigateUp) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
        AsyncImage(
            model = pokemon.imgLink,
            contentDescription = pokemon.name,
            placeholder = painterResource(id = R.drawable.test_pockemon),
            error = painterResource(id = R.drawable.ic_broken_image),
            modifier = Modifier
                .size(190.dp)
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun PokemonStatsBar(stat: Stats){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        var cur_text = ""
        cur_text = when (stat.stat.name) {
            "hp" -> stringResource(id = R.string.HP)
            "attack" -> stringResource(id = R.string.ATTACK)
            "defense" -> stringResource(id = R.string.DEFENSE)
            "special-attack" -> stringResource(id = R.string.SPECIAL_ATTACK)
            "special-defense" -> stringResource(id = R.string.SPECIAL_DEFENSE)
            "speed" -> stringResource(id = R.string.SPEED)
            else -> ""
        }
        Text(
            text = cur_text.uppercase(),
            modifier = Modifier
                .padding(start = 32.dp)
        )
        PokemonProgressBar(stat)

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AbilityGrid(abilities: List<abilities>, modifier: Modifier = Modifier){
    Box(modifier = modifier.heightIn(max = 200.dp)) {
        FlowRow {
            for(ability in abilities){
                AbilityCard(ability)
            }
        }
    }
}

@Composable
fun AbilityCard(ability: abilities, modifier: Modifier = Modifier){
    val rnd = Random()
    val color = Color(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    Card(
        modifier = modifier
            .padding(5.dp)
            .wrapContentSize(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp,color),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = ability.ability.name,
            maxLines = 1,
            modifier = Modifier.padding(5.dp)
        )
    }
}


@Preview
@Composable
fun DetailsScreenPreview(){
    Retrofit_4Theme {
        DetailsFullScreen(pokemon = PokemonStats(listOf(),1,1,1,"", listOf()))
    }
}