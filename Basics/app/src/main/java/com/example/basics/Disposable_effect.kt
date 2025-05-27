package com.example.basics

import android.media.MediaPlayer
import android.util.Log
import android.view.ViewTreeObserver
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


// Disposable Effect used for cleann up, the key is similar as launched effect
// As intial compostion starts,  side effect part will be execute  and perform the clean up for the current task and re-intiate the compostion
//button uses for changing the state and then changes the states and make clean and rebegin

@Composable
fun Disposable() {
    var state = remember { mutableStateOf(false) }

    DisposableEffect(key1 = state) {
        Log.d("Disposable Effect" , "Started ")
        onDispose { 
            Log.d("Disposable Effect", "Cleanning the disposable side")
        }
    }

    Button(onClick = {state.value = !state.value}){
        Text("Change State ")
    }
}

@Composable
fun MediaComposable()  {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val mediaPlayer = MediaPlayer.create(context,R.raw.glass)
        mediaPlayer.start()
        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}


@Composable
fun subkeyboard() {
    TextField(value = "", onValueChange = {})
}

@Composable
fun Keyboard_Visible(){
    val view  = LocalView.current
    DisposableEffect(key1 = Unit) {
        val listner = ViewTreeObserver.OnGlobalLayoutListener { // layout specifc changes maded then listner should be called
            val insets = ViewCompat.getRootWindowInsets(view) // get screen rectangles , (rectangle --> insets)
            val isKeyboardVisible = insets.isVisible(WindowInsetsCompat.Type.ime()) // check whether keyboard is there within insets
            Log.d("Keyboard Visibility " , " Keyboard is Visible ${isKeyboardVisible.toString()}")
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listner) //

        // Remove the event bind part
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listner)
        }
    }


}