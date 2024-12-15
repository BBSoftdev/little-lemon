package com.example.littlelemon

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.littlelemon.composables.Navigation
import com.example.littlelemon.database.MenuNetwork
import com.example.littlelemon.database.MenuNetwork.MenuItemNetwork
import com.example.littlelemon.database.MenuRepository
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
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val menuRepository by lazy { MenuRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
                Scaffold(modifier = Modifier.fillMaxSize()) {  innerPadding ->
                    Navigation()
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            if(menuRepository.isEmpty())
                saveMenuToDatabase(fetchMenu())
        }

    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> = httpClient
        .get(getString(R.string.fetch_menu_items_url))
        .body<MenuNetwork>()
        .menu

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItems = menuItemsNetwork.map { it.toMenuItem() }
        menuRepository.insertAllMenuItems(*menuItems.toTypedArray())
    }
}