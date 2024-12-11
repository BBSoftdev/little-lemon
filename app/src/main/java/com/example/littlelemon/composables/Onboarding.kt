package com.example.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.Typography

@Composable
fun Onboarding(){
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
            OutlinedTextField(
                onValueChange = {},
                value = "",
                placeholder = { Text("Please enter your first name")},
                label = { Text("First name") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                onValueChange = {},
                value = "",
                placeholder = { Text("Please enter your last name")},
                label = { Text("Last name") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                onValueChange = {},
                value = "",
                placeholder = { Text("Please enter your email")},
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            PrimaryButton(
                text = "Register",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

    }

}
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(10.dp)
//                    ) {
//                        Image(
//                            painter = painterResource(R.drawable.logo),
//                            contentDescription = stringResource(R.string.header_logo),
//                            modifier = Modifier
//                                .fillMaxSize()
//                        )
//                    }
//                }
//            )
//        })
//        { paddingValues ->
//            Column(
//                modifier = Modifier.padding(paddingValues = paddingValues)
//            ){
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight(0.15f)
//                        .background(MaterialTheme.colorScheme.primary)
//                ){
//                    Text(
//                        text = stringResource(R.string.let_s_get_to_know_you),
//                        style = Typography.headlineSmall,
//                        textAlign = TextAlign.Center,
//                        color = MaterialTheme.colorScheme.onPrimary,
//                    )
//                }
//                Box(
//                    contentAlignment = Alignment.CenterStart,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight(0.15f)
//                ){
//                    Text(
//                        text = stringResource(R.string.personal_information),
//                        style = Typography.titleLarge
//                    )
//                }
//            }
//        }

@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    LittleLemonTheme {
        Onboarding()
    }
}
