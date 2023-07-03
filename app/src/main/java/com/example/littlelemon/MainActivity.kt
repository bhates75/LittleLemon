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
    private val userFirstName = MutableLiveData<String>()
    private val userLastName = MutableLiveData<String>()
    private val userEmail = MutableLiveData<String>()
    private val sharedPreferences by lazy{
        getSharedPreferences("LoggedIn", ComponentActivity.MODE_PRIVATE)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        //Initializes live data to shared preferences with an initial boolean value.
        loggedInLiveData.value = sharedPreferences.getBoolean("LoggedIn", false)
        userFirstName.value = sharedPreferences.getString("First Name", "")
        userLastName.value = sharedPreferences.getString("Last Name", "")
        userEmail.value = sharedPreferences.getString("Email", "")
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(sharedPreferences, loggedInLiveData, userFirstName, userLastName, userEmail)
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