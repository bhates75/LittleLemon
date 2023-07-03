package com.example.littlelemon.composables

import android.content.SharedPreferences
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.littlelemon.Onboarding
import com.example.littlelemon.R

@Composable
fun Profile(navController: NavHostController, prefs: SharedPreferences, userFirstName: MutableLiveData<String>, userLastName: MutableLiveData<String>, userEmail: MutableLiveData<String>){
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row{
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier.size(240.dp, 120.dp)
            )
        }

        Text(
            text = "Profile Information",
            modifier = Modifier.padding(top = 24.dp),
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold
        )

        Column(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp, top = 12.dp)
        ) {
            Text(
                text = "First Name",
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 34.dp, bottom = 4.dp)
            )
            Text(
                text = userFirstName.value ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 34.dp)
            )
            Text(
                text = "Last Name",
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = userLastName.value ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 34.dp)
            )
            Text(
                text = "Email",
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = userEmail.value ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 34.dp)
            )
        }
        Button(
            onClick = {
                prefs.edit().putBoolean("LoggedIn", false).commit()
                prefs.edit().clear().commit()
                navController.navigate(Onboarding.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(text = "Log Out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row{
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier.size(240.dp, 120.dp)
            )
        }

        Text(
            text = "Profile Information",
            modifier = Modifier.padding(top = 24.dp),
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold
        )

        Column(
            modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp, top = 12.dp)
        ) {
            Text(
                text = "First Name",
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 34.dp, bottom = 4.dp)
            )
            Text(
                text = "Profile Information",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 34.dp)
            )
            Text(
                text = "Last Name",
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Profile Information",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 34.dp)
            )
            Text(
                text = "Email",
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Profile Information",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 34.dp)
            )
        }
        Button(
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(text = "Log Out")
        }
    }
}