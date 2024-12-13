package com.example.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.Home
import com.example.littlelemon.Onboarding
import com.example.littlelemon.Profile
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Header(navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationRoute = navBackStackEntry?.destination?.route

    var onHome = currentDestinationRoute == Home.route
    var onStart = onHome || currentDestinationRoute == Onboarding.route

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.12f)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(R.string.header_logo),
            modifier = Modifier
                .fillMaxSize(.5f)
        )
        if(onHome)
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = stringResource(R.string.profile_image),
                modifier = Modifier
                    .fillMaxHeight(.5f)
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 24.dp)
                    .clickable { navController.navigate(Profile.route) }
            )

        if(!onStart)
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.go_back_icon),
                modifier = Modifier
                    .fillMaxHeight(.5f)
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 24.dp)
                    .clickable { navController.popBackStack() }
            )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderPreview(){
    LittleLemonTheme {
        Header(rememberNavController())
    }
}