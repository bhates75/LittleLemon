package com.example.littlelemon.composables

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.littlelemon.Home
import com.example.littlelemon.MainActivity
import com.example.littlelemon.R

@Composable
fun Onboarding(
    navController: NavHostController,
    prefs: SharedPreferences,
    loggedInLiveData: MutableLiveData<Boolean>,
    userFirstName: MutableLiveData<String>,
    userLastName: MutableLiveData<String>,
    userEmail: MutableLiveData<String>
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier.size(240.dp, 120.dp)
            )
        }
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.DarkGray,
        ) {
            Text(
                text = "Let's get to know you!",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 26.sp,
                modifier = Modifier.padding(34.dp)
            )
        }
        Box(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "Personal Information",
                Modifier.padding(start = 16.dp, top = 34.dp, bottom = 34.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
        var firstName by remember {
            mutableStateOf("")
        }
        Box(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "First Name",
                Modifier.padding(bottom = 4.dp, start = 16.dp),
                fontSize = 12.sp,
            )

        }
        OutlinedTextField(
            value = firstName,
            onValueChange = { name ->
                firstName = name
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        var lastName by remember {
            mutableStateOf("")
        }
        Box(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "Last Name",
                Modifier.padding(top = 24.dp, bottom = 4.dp, start = 16.dp),
                fontSize = 12.sp,
            )

        }
        OutlinedTextField(
            value = lastName,
            onValueChange = { name ->
                lastName = name
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        var email by remember {
            mutableStateOf("")
        }
        Box(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "Email Address",
                Modifier.padding(top = 24.dp, bottom = 4.dp, start = 16.dp),
                fontSize = 12.sp,
            )
        }
        OutlinedTextField(
            value = email,
            onValueChange = { e ->
                email = e
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        Button(
            onClick = {
                if(firstName.isBlank() || lastName.isBlank() || email.isBlank()){
                    Log.d("MESS", "Login Failed")
                }
                else{
                    //Changes the login shared preference to true.
                    prefs.edit().putBoolean("LoggedIn", true).apply()
                    //Updates the value of the live data so it'll update where state is used.
                    loggedInLiveData.value = true
                    prefs.edit().putString("FirstName", firstName).apply()
                    userFirstName.value = firstName
                    prefs.edit().putString("LastName", lastName).apply()
                    userLastName.value = lastName
                    prefs.edit().putString("Email", email).apply()
                    userEmail.value = email
                    navController.navigate(Home.route)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(text = "Register")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewOnboarding() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier.size(240.dp, 120.dp)
            )
        }
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.DarkGray,
        ) {
            Text(
                text = "Let's get to know you!",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 26.sp,
                modifier = Modifier.padding(34.dp)
            )
        }
        Box(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "Personal Information",
                Modifier.padding(start = 16.dp, top = 34.dp, bottom = 34.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
        var firstName by remember {
            mutableStateOf("")
        }
        Box(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "First Name",
                Modifier.padding(bottom = 4.dp, start = 16.dp),
                fontSize = 12.sp,
            )

        }
        OutlinedTextField(
            value = firstName,
            onValueChange = { name ->
                firstName = name
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        var lastName by remember {
            mutableStateOf("")
        }
        Box(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "Last Name",
                Modifier.padding(top = 24.dp, bottom = 4.dp, start = 16.dp),
                fontSize = 12.sp,
            )

        }
        OutlinedTextField(
            value = lastName,
            onValueChange = { name ->
                lastName = name
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        var email by remember {
            mutableStateOf("")
        }
        Box(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "Email Address",
                Modifier.padding(top = 24.dp, bottom = 4.dp, start = 16.dp),
                fontSize = 12.sp,
            )
        }
        OutlinedTextField(
            value = email,
            onValueChange = { e ->
                email = e
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 34.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(text = "Register")
        }
    }
}