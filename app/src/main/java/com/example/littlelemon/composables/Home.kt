package com.example.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.spacing

@OptIn(ExperimentalTextApi::class)
@Composable
fun Home(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(navController)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ){
                Text(
                    text = stringResource(R.string.restaurant_name),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    text = stringResource(R.string.restaurant_location),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(top = MaterialTheme.spacing.extreme)
                )
                Text(
                    text = stringResource(R.string.restaurant_description),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxWidth(.6f)
                        .padding(top = 40.dp)
                )
                Image(
                    painter = painterResource(R.drawable.hero_image),
                    contentDescription = stringResource(R.string.banner_image),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth(.35f)
                        .fillMaxHeight(.45f)
                        .clip(RoundedCornerShape(MaterialTheme.spacing.small))
                        .align(Alignment.CenterEnd)
                )
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    LittleLemonTheme {
        Home(rememberNavController())
    }
}