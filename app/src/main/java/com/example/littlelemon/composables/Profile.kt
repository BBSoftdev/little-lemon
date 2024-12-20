package com.example.littlelemon.composables

import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.Onboarding
import com.example.littlelemon.R
import com.example.littlelemon.SharedPreferencesKeys.EMAIL
import com.example.littlelemon.SharedPreferencesKeys.FIRST_NAME
import com.example.littlelemon.SharedPreferencesKeys.LAST_NAME
import com.example.littlelemon.SharedPreferencesKeys.LITTLE_LEMON
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.Typography
import com.example.littlelemon.ui.theme.spacing

@Composable
fun Profile(navController: NavController){
    val context = LocalContext.current
    val sharedPreferences by lazy { context.getSharedPreferences(LITTLE_LEMON, MODE_PRIVATE) }

    val firstName = sharedPreferences.getString(FIRST_NAME, stringResource(R.string.not_found))
    val lastName = sharedPreferences.getString(LAST_NAME, stringResource(R.string.not_found))
    val email = sharedPreferences.getString(EMAIL, stringResource(R.string.not_found))

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
    ){
        Header(navController)
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f)
                .padding(MaterialTheme.spacing.large)
        ) {
            Text(
                text = stringResource(R.string.personal_information),
                style = Typography.titleLarge
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "${stringResource(R.string.first_name)}:",
                    style = Typography.labelMedium
                )
                Text(
                    text = firstName!!,
                    style = Typography.bodyLarge
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "${stringResource(R.string.last_name)}:",
                    style = Typography.labelMedium
                )
                Text(
                    text = lastName!!,
                    style = Typography.bodyLarge
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "${stringResource(R.string.email)}:",
                    style = Typography.labelMedium
                )
                Text(
                    text = email!!,
                    style = Typography.bodyLarge
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.large)
        ){
            PrimaryButton(
                text = stringResource(R.string.log_out),
                onClick = {
                    sharedPreferences.edit().clear().apply()
                    navController.navigate(Onboarding.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    LittleLemonTheme {
        Profile(rememberNavController())
    }
}