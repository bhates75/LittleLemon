package com.example.littlelemon.composables

import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.Home
import com.example.littlelemon.Onboarding
import com.example.littlelemon.Profile



@Composable
fun Navigation(prefs: SharedPreferences, loggedInLiveData: MutableLiveData<Boolean>, userFirstName: MutableLiveData<String>, userLastName: MutableLiveData<String>, userEmail: MutableLiveData<String>){
    //Creates a state out of the passed in livedata so it'll update.
    val selected = loggedInLiveData.observeAsState(initial = false)
    val navController = rememberNavController()
    //Initializes the start destination of the app based on whether the user is logged in or not.
    val start: String = if(selected.value == true){
        Home.route
    } else {
        Onboarding.route
    }
    NavHost(navController = navController, startDestination = start){
        composable(Onboarding.route){
            Onboarding(navController, prefs, loggedInLiveData, userFirstName, userLastName, userEmail)
        }
        composable(Home.route){
            Home(navController)
        }
        composable(Profile.route){
            Profile(navController, prefs, userFirstName, userLastName, userEmail)
        }
    }
}