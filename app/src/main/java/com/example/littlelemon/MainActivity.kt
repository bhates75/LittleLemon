package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.example.littlelemon.composables.Navigation
import com.example.littlelemon.composables.Onboarding
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    private val loggedInLiveData = MutableLiveData<Boolean>()
    private val sharedPreferences by lazy{
        getSharedPreferences("LoggedIn", ComponentActivity.MODE_PRIVATE)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        loggedInLiveData.value = sharedPreferences.getBoolean("LoggedIn", false)
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(sharedPreferences, loggedInLiveData)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LittleLemonTheme {
        Greeting("Android")
    }
}