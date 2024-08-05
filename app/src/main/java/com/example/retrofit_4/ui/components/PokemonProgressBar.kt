package com.example.retrofit_4.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.retrofit_4.data.models.Stats
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PokemonProgressBar(stat: Stats, modifier: Modifier = Modifier){
    var statWidth : Int by remember {
        mutableIntStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            while (statWidth < stat.base_stat) {
                delay(5)
                statWidth++
            }
        }
    }

    Box(modifier = Modifier
        .height(20.dp)
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
        .animateContentSize()
    ){
        Box(modifier = Modifier
            .fillMaxHeight()
            .width(statWidth.dp)
            .background(Color.Red, shape = MaterialTheme.shapes.medium)
            .animateContentSize(),
            contentAlignment = Alignment.CenterEnd
        ){
            Text(
                text = "${stat.base_stat}/300",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                modifier = Modifier.padding(end = 4.dp)
            )
        }
    }
}