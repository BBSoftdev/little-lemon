package com.example.littlelemon.composables

import android.content.Context.MODE_PRIVATE
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.Home
import com.example.littlelemon.Onboarding
import com.example.littlelemon.Profile
import com.example.littlelemon.SharedPreferencesKeys

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val context = LocalContext.current

    val sharedPreferences  by lazy { context.getSharedPreferences("LittleLemon", MODE_PRIVATE) }
    val userData = sharedPreferences.getString(SharedPreferencesKeys.FIRST_NAME, null)

    val startDestination = if (userData == null) Onboarding.route else Home.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Onboarding.route) {
            Onboarding(navController)
        }
        composable(Home.route) {
            Home()
        }
        composable(Profile.route) {
            Profile()
        }
    }

}