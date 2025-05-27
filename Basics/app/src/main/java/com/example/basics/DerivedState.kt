package com.example.basics

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun Counters() {
   val state = remember { mutableStateOf(0) }
   LaunchedEffect(Unit) {
       for(i in 1..10) {
           delay(1000)
           state.value++
       }
   }
    Text(text = state.value.toString())
}

// produce state --> state produce & asyncrous updatation
@Composable
fun produceState( ) {
    val state = produceState(0){
        for(i in 1..10){
            delay(1000)
            value += 1
        }
    }

    Text(text = state.value.toString())
}

// Intial value assign , produces function to update the function and as function updates the value and recompostion --> Ui update
@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun Loader() {
    val degree = produceState(initialValue = 0){
        while(true){
            delay(10)
            value = (value + 10) % 360
        }
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(1f)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Image(
                imageVector = Icons.Default.Refresh,
                contentDescription = "",
                modifier =  Modifier
                    .size(50.dp)
                    .rotate(degree.value.toFloat())
            )
            Text("Loading")
        }
    }
}


// Derived State used states to derive new states as used states updates it will also update the derive states
@SuppressLint("ProduceStateDoesNotAssignValue", "UnrememberedMutableState")
@Composable
fun Derived_State() {
    val tableOf = remember { mutableStateOf(5) }
//    val index = remember { mutableStateOf(10) }
    val index = produceState(initialValue = 10) {
        for(i in 1..10){
            delay(1000)
            value += 1
        }
    }

    val message = derivedStateOf{
        "${tableOf.value} * ${index.value} = ${tableOf.value * index.value}"
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier =  Modifier.fillMaxSize(1f)
    ) {
        Text(text = message.value)
    }
}













