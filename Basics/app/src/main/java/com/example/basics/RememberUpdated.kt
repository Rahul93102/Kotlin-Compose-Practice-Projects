package com.example.basics

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.delay



@Composable
fun Counters(value : Int) {
    val counter = remember { mutableStateOf(0) }
    LaunchedEffect (key1 = Unit){
        delay(1000)
        counter.value = 10
    }
    sub_counter(counter.value)
}

// within launched effect, when we update the value in firstones is 10 but when we logged in second launcedeffect it shows value to 0
// When calls reaches to first launchedeffect value to 10 it leads to recomposition but second launchedeffect takes the value of 0 and shows in the current Log
// LaunchedEffect uses the intital value not updated or recompostion values or Intial value or when key changes
// we can use remmeberupdated state to make use the remember updated state

@Composable
fun sub_counter(value: Int) {
    val updated = rememberUpdatedState(value)
    LaunchedEffect(key1 = Unit) {
        delay(1000)
        Log.d("vals","value: ${value}") // output --> 0
        Log.d("vals","value: ${updated}") // output --> 10

    }
    Text("The value of sub_counter is ${value}")
}


// When we have not clicked the button then it will show a function  and when we changed the state through button then it will shows through b
fun a() {  Log.d("","It's function a ") }

fun b() { Log.d("","It's function b ") }

@Composable
fun second_remmeber() {
    var state = remember{ mutableStateOf(::a)}
    Button(onClick = {state.value = ::b}) {
        Text("Click to change the state ")
    }
    LandingScreen(state.value)
}

@Composable
fun LandingScreen(onClick : () ->  Unit) {
    val currentTimeout by rememberUpdatedState(onClick)
    LaunchedEffect (key1 = true){
        delay(10000)
        currentTimeout()
    }
}




