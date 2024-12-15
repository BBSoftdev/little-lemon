package com.example.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.MenuCategories
import com.example.littlelemon.R
import com.example.littlelemon.database.MenuRepository
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.spacing

@Composable
fun Home(navController: NavController) {
    val menuRepository = MenuRepository(LocalContext.current)
    val menuItemsDatabase by menuRepository.getAllMenuItems().observeAsState(emptyList())
    var menuItems = menuItemsDatabase

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(navController)

        var searchPhrase by remember { mutableStateOf("") }

        HeroBanner(setSearchPhrase = { searchPhrase = it })

        if (searchPhrase.isNotEmpty())
            menuItems = menuItems.filter {
                it.title.contains(searchPhrase, ignoreCase = true)
            }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.spacing.medium)
        ){
            var selectedCategoryOrdinal by remember { mutableIntStateOf(-1) }
            if(selectedCategoryOrdinal >= 0)
                menuItems = menuItems.filter{
                    it.category.equals(
                        MenuCategories.entries[selectedCategoryOrdinal].toString(),
                        ignoreCase = true
                    )
                }

            CategoryPicker(setSelectedCategory = { selectedCategoryOrdinal = it })
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.surface)
            MenuItems(menuItems)
        }
    }
}

@Composable
private fun HeroBanner(setSearchPhrase: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.42f)
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f)
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
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(.6f)
            )
            Image(
                painter = painterResource(R.drawable.hero_image),
                contentDescription = stringResource(R.string.banner_image),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth(.35f)
                    .fillMaxHeight(.6f)
                    .clip(RoundedCornerShape(MaterialTheme.spacing.small))
                    .align(Alignment.BottomEnd)
            )
        }

        var input by remember { mutableStateOf("") }

        TextField(
            value = input,
            onValueChange = {
                input = it
                setSearchPhrase(input)
            },
            placeholder = { Text("Enter search phrase") },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            shape = RoundedCornerShape(MaterialTheme.spacing.small),
            leadingIcon = { Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search)
            ) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.medium)
        )
    }
}

@Composable
private fun CategoryPicker(setSelectedCategory: (Int) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = MaterialTheme.spacing.extraLarge)
    ) {
        Text(
            text = stringResource(R.string.order_for_delivery),
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium)
        ) {
            var selectedCategory by remember { mutableIntStateOf(-1) }
            for (category in MenuCategories.entries) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                        .background(
                            if(selectedCategory == category.ordinal)
                                MaterialTheme.colorScheme.surfaceVariant
                            else MaterialTheme.colorScheme.surface
                        )
                        .padding(MaterialTheme.spacing.small)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            selectedCategory =
                                if(selectedCategory != category.ordinal) category.ordinal else -1
                            setSelectedCategory(selectedCategory)
                        }
                ) {
                    Text(
                        text = category.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
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