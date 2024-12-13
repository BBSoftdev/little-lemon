package com.example.littlelemon.database

import android.content.Context
import androidx.room.Room

class MenuRepository(context: Context) {

    private val database =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "database"
        ).build()

    fun getAllMenuItems() = database.menuDao().getAllMenuItems()

    fun insertAllMenuItems(vararg menuItems: MenuItem) = database.menuDao().insertAll(*menuItems)

    fun isEmpty() = database.menuDao().isEmpty()

}