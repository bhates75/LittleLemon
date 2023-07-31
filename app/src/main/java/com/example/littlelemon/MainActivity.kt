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
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.littlelemon.composables.Navigation
import com.example.littlelemon.composables.Onboarding
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    //Creates variables to hold the data from shared preferences throughout the app.
    private val loggedInLiveData = MutableLiveData<Boolean>()
    private val userFirstName = MutableLiveData<String>()
    private val userLastName = MutableLiveData<String>()
    private val userEmail = MutableLiveData<String>()
    //Creates a shared preference containing data for the profile info and start screen.
    private val sharedPreferences by lazy{
        getSharedPreferences("llPrefs", ComponentActivity.MODE_PRIVATE)
    }
    private val httpClient = HttpClient(Android){
        install(ContentNegotiation){
            json(contentType = ContentType("text", "plain"))
        }
    }
    val database by lazy {
        Room.databaseBuilder(applicationContext, LLDatabase::class.java, "llDatabase")
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        //Initializes live data to shared preferences with an initial values.
        loggedInLiveData.value = sharedPreferences.getBoolean("LoggedIn", false)
        userFirstName.value = sharedPreferences.getString("FirstName", "")
        userLastName.value = sharedPreferences.getString("LastName", "")
        userEmail.value = sharedPreferences.getString("Email", "")
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(sharedPreferences, loggedInLiveData, userFirstName, userLastName, userEmail, database)
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO){
            if(database.menuItemDao().isEmpty()) {
                val menuItems = fetchMenu()
                saveMenuItemsToDatabase(menuItems)
            }
        }
    }

    private suspend fun fetchMenu(): List<MenuNetworkItem>{
        return httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetworkData>()
            .menu
    }

    private fun saveMenuItemsToDatabase(menuItems: List<MenuNetworkItem>){
        val menuItemsToRoom = menuItems.map { it.toMenuItemRoom() }
        database.menuItemDao().insertItems(*menuItemsToRoom.toTypedArray())
    }
}