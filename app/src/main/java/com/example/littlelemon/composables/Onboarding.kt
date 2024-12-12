package com.example.littlelemon.composables

import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.Home
import com.example.littlelemon.R
import com.example.littlelemon.SharedPreferencesKeys.EMAIL
import com.example.littlelemon.SharedPreferencesKeys.FIRST_NAME
import com.example.littlelemon.SharedPreferencesKeys.LAST_NAME
import com.example.littlelemon.SharedPreferencesKeys.LITTLE_LEMON
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.Typography

@Composable
fun Onboarding(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
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
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f)
                .background(MaterialTheme.colorScheme.primary)
        ){
            Text(
                text = stringResource(R.string.let_s_get_to_know_you),
                style = Typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .padding(24.dp)
        ){
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

            var firstName by remember { mutableStateOf("") }
            OutlinedTextField(
                onValueChange = {
                    firstName = it
                },
                value = firstName,
                placeholder = { Text(stringResource(R.string.please_enter_your_first_name))},
                label = { Text("First name") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
            )

            var lastName by remember { mutableStateOf("") }
            OutlinedTextField(
                onValueChange = { lastName = it },
                value = lastName,
                placeholder = { Text(stringResource(R.string.please_enter_your_last_name))},
                label = { Text(stringResource(R.string.last_name)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
            )

            var email by remember { mutableStateOf("") }
            OutlinedTextField(
                onValueChange = { email = it},
                value = email,
                placeholder = { Text(stringResource(R.string.please_enter_your_email))},
                label = { Text(stringResource(R.string.email)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            val context = LocalContext.current
            val sharedPreferences by lazy { context.getSharedPreferences(LITTLE_LEMON, MODE_PRIVATE) }

            Box(Modifier.fillMaxSize()) {
                PrimaryButton(
                    text = stringResource(R.string.register),
                    onClick = {
                        var message: String
                        if (firstName.isBlank() || lastName.isBlank() || email.isBlank())
                            message = "Registration unsuccessful. Please enter all data."
                        else {
                            message = "Registration successful!"
                            sharedPreferences.edit()
                                .putString(FIRST_NAME, firstName)
                                .putString(LAST_NAME, lastName)
                                .putString(EMAIL, email)
                                .apply()

                            navController.navigate(Home.route)
                        }
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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
fun OnboardingPreview(){
    val navController = rememberNavController()
    LittleLemonTheme {
        Onboarding(navController)
    }
}
