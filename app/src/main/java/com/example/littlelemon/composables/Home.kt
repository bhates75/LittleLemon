package com.example.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.LLDatabase
import com.example.littlelemon.Profile
import com.example.littlelemon.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.littlelemon.MenuItemRoom

@Composable
fun Home(navController: NavHostController, database: LLDatabase){
    val menuItems by database.menuItemDao().getAll().observeAsState(emptyList())
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier.size(150.dp, 75.dp)
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(80.dp, 75.dp)
                        .clickable {
                            navController.navigate(Profile.route)
                        },
                    alignment = Alignment.TopEnd,
                )
            }
        }
        MenuItemsDisplay(mItems = menuItems)
    }
}

@Composable
fun MenuItemsDisplay(mItems: List<MenuItemRoom>){
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(20.dp)
    ){
        items(
            items = mItems,
            itemContent = {menuItem ->
                Divider(
                    color = Color.Black,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = menuItem.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 5.dp,bottom = 5.dp)
                    )
                    Text(
                        text = menuItem.description,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .fillMaxWidth(.7f)
                            .padding(bottom = 5.dp)
                    )
                    Text(
                        text = "$" + menuItem.price + ".00",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier.size(150.dp, 75.dp)
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(80.dp, 75.dp),
                    alignment = Alignment.TopEnd
                )
            }
        }
    }
}
