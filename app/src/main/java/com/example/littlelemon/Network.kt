package com.example.littlelemon

import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkData(
    val menu: List<MenuNetworkItem>
)

@Serializable
data class MenuNetworkItem(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
){
    fun toMenuItemRoom() =
        MenuItemRoom(
            id,
            title,
            description,
            price,
            image,
            category
        )
}