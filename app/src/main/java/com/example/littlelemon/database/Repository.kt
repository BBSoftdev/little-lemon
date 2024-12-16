package com.example.littlelemon.database

import android.content.Context

class MenuRepository(context: Context) {

    private val database = AppDatabase.getInstance(context)

    fun getAllMenuItems() = database.menuDao().getAllMenuItems()

    fun insertAllMenuItems(vararg menuItems: MenuItem) = database.menuDao().insertAll(*menuItems)

    fun isEmpty() = database.menuDao().isEmpty()

}