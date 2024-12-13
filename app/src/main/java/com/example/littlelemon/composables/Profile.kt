package com.example.littlelemon.composables

import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.Home
import com.example.littlelemon.Onboarding
import com.example.littlelemon.R
import com.example.littlelemon.SharedPreferencesKeys.EMAIL
import com.example.littlelemon.SharedPreferencesKeys.FIRST_NAME
import com.example.littlelemon.SharedPreferencesKeys.LAST_NAME
import com.example.littlelemon.SharedPreferencesKeys.LITTLE_LEMON
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.Typography

@Composable
fun Profile(navController: NavController){
    val context = LocalContext.current
    val sharedPreferences by lazy { context.getSharedPreferences(LITTLE_LEMON, MODE_PRIVATE) }

    val firstName = sharedPreferences.getString(FIRST_NAME, stringResource(R.string.not_found))
    val lastName = sharedPreferences.getString(LAST_NAME, stringResource(R.string.not_found))
    val email = sharedPreferences.getString(EMAIL, stringResource(R.string.not_found))

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Header(navController)
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(30.dp)
        ){
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f)
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.15f)
                ){
                    Text(
                        text = stringResource(R.string.personal_information),
                        style = Typography.titleLarge
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = "${stringResource(R.string.first_name)}:",
                        style = Typography.bodyLarge
                    )
                    Text(
                        text = firstName!!,
                        style = Typography.bodyLarge
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = "${stringResource(R.string.last_name)}:",
                        style = Typography.bodyLarge
                    )
                    Text(
                        text = lastName!!,
                        style = Typography.bodyLarge
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = "${stringResource(R.string.email)}:",
                        style = Typography.bodyLarge
                    )
                    Text(
                        text = email!!,
                        style = Typography.bodyLarge
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize()
            )
            {
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

}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    LittleLemonTheme {
        Profile(rememberNavController())
    }
}