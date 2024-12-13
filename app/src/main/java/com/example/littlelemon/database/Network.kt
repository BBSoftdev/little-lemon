package com.example.littlelemon.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>,
) {
    @Serializable
    data class MenuItemNetwork(
        @SerialName("id")
        val id: Int,
        @SerialName("title")
        val title: String,
        @SerialName("description")
        val description: String,
        @SerialName("price")
        val price: Double,
        @SerialName("image")
        val image: String
    ) {
        fun toMenuItem() = MenuItem(id, title, description, price, image)
    }
}