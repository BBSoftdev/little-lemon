package com.example.littlelemon.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "menu_item")
data class MenuItem(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String
)

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu_item")
    fun getAllMenuItems(): LiveData<List<MenuItem>>

    @Insert
    fun insertAll(vararg menuItems: MenuItem)

    @Query("SELECT (SELECT COUNT(*) FROM menu_item) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
}