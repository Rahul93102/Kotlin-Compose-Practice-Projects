package com.example.basics

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


// whenever Button is Clciked it will not update the UI with updated count , recompostion updates UI we need mutable state to Update the Ui
// whenever we called again and again it will loose the state so we need to use remember to remember the last value or state
// whenever we moved or break the compostion then we lose our last updated value whcih can be saved through bundle and saves the values
// we have hosted the state to the parent composnents to pass the value to both of the subparts there
@Composable
fun parentCompoent() {
    var cnt : MutableState<Int> = remember { mutableStateOf(0) }
    StateHosting(cnt,{cnt.value ++})
    messageBar(cnt)
    Text("Message sent ")
}
@Composable
fun StateHosting(cnt : MutableState<Int>,onIncrease : () ->  Unit) {
//    var cnt : MutableState<Int> = rememberSaveable(){mutableStateOf(0)}
//    var cnt : MutableState<Int> = remember{mutableStateOf(0)}

    Column(verticalArrangement = Arrangement.Center) {
        Text("You have Clicked the Button ${cnt.value}")
        Button(
            onClick = {
                onIncrease()
            Log.d("Button Clicked ","Started Clicking ${cnt.value}")
            }
        ) {
            Text("Send Notification")
        }
    }
}


@Composable
fun messageBar(cnt : MutableState<Int>) {
      Card (
          modifier = Modifier.fillMaxWidth()
      ){
          Row(
               Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
          ) {
              Image(
                  imageVector =Icons.Default.Favorite,
                  contentDescription = "",
                  Modifier.padding(8.dp)
              )

              Text("Message Sent so far ${cnt.value}")
          }
      }
}