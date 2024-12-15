package com.example.littlelemon.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.littlelemon.database.MenuItem
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.spacing

@Composable
fun MenuItems(menuItems: List<MenuItem>){
    LazyColumn(
        modifier = Modifier.fillMaxHeight()
    ) {
        items(menuItems) {
            MenuItem(it)
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.surface)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItem(menuItem: MenuItem){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = MaterialTheme.spacing.medium)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(.75f)
        ){
            Text(
                text = menuItem.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = menuItem.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(
                        top = MaterialTheme.spacing.small,
                        bottom = MaterialTheme.spacing.medium
                    )
            )
            Text(
                text = "$${"%.2f".format(menuItem.price)}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        GlideImage(
            model = menuItem.image,
            loading = placeholder(ColorPainter(MaterialTheme.colorScheme.primary)),
            contentDescription = menuItem.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(90.dp)
                .width(80.dp)
                .align(Alignment.Bottom)
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuItemsPreview(){
    LittleLemonTheme {
        val menuItems = listOf(
            MenuItem(
                1,
                "Beer IPA",
                "Delicious brewed IPA from our local brewery Chicago Beer Inc. right here in Chicago.",
                12.50,
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
                "drinks"
            ),
            MenuItem(
                1,
                "Beer IPA",
                "Delicious brewed IPA from our local brewery Chicago Beer Inc. right here in Chicago.",
                12.50,
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
                "drinks"
            ),
            MenuItem(
                1,
                "Beer IPA",
                "Delicious brewed IPA from our local brewery Chicago Beer Inc. right here in Chicago.",
                12.50,
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
                "drinks"
            ),
            MenuItem(
                1,
                "Beer IPA",
                "Delicious brewed IPA from our local brewery Chicago Beer Inc. right here in Chicago.",
                12.50,
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
                "drinks"
            ),
            MenuItem(
                1,
                "Beer IPA",
                "Delicious brewed IPA from our local brewery Chicago Beer Inc. right here in Chicago.",
                12.50,
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
                "drinks"
            ),
            MenuItem(
                1,
                "Beer IPA",
                "Delicious brewed IPA from our local brewery Chicago Beer Inc. right here in Chicago.",
                12.50,
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
                "drinks"
            ),
            MenuItem(
                1,
                "Beer IPA",
                "Delicious brewed IPA from our local brewery Chicago Beer Inc. right here in Chicago.",
                12.50,
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
                "drinks"
            )
        )
        MenuItems(menuItems)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuItemPreview(){
    LittleLemonTheme {
        val menuItem = MenuItem(
            1,
            "Beer IPA",
            "Delicious brewed IPA from our local brewery Chicago Beer Inc. right here in Chicago.",
            12.50,
            "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
            "drinks"
        )
        MenuItem(menuItem)
    }
}