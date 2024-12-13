package com.example.littlelemon.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit, modifier: Modifier){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        shape = RoundedCornerShape(16),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.tertiary
        ),
        modifier = modifier
    ) {
        Text( text )
    }
}

@Preview(showBackground = false)
@Composable
fun PrimaryButtonPreview(){
    LittleLemonTheme {
        PrimaryButton(
            text = stringResource(R.string.click_here),
            onClick = {},
            modifier = Modifier)
    }
}