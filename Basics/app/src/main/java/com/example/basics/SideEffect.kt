package com.example.basics

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


// within existing approach, as many times we are making callable to Sideeffect it will fetch the numbers and it will take time consuming
// Launched Effect helps us to make it executable on the basis of key and helps to reduce the number of calls effect handler
// coroutine scope launched so that it will not effect the app background effects

@Composable
fun SideEffect() {
    var Number : MutableState<List<String>>  = remember{ mutableStateOf(emptyList<String>()) }
    LaunchedEffect(key1 = Unit) {
        Number.value = ListOfUser()
    }

    LazyColumn {
        items(Number.value) { item ->
            Text(text = item)
        }
    }
}

fun ListOfUser() : List<String> {
    return listOf("one","two","three")
}


// helps us to make counter increasbale with the help of counter
@Composable
fun Counter() {
    val cnt : MutableState<Int> = remember { mutableStateOf(0) }
    LaunchedEffect(key1 = false) {
        Log.d("message","Button has been Clicked that many times ${cnt.value}")
    }
    Button(onClick =  {cnt.value ++ }) {
        Text("You have been Clicked ${cnt.value}")
    }
}
