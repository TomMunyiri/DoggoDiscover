package com.tommunyiri.doggo.discover.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FullScreenError(
    errorTitle: String,
    errorDesc: String,
    imageVector: ImageVector,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))
        Icon(
            imageVector,
            contentDescription = errorTitle,
            Modifier
                .align(Alignment.CenterHorizontally),
        )
        Text(
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            text = errorTitle,
            modifier =
                Modifier
                    .padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
        )
        Text(
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            text = errorDesc,
            modifier =
                Modifier
                    .padding(top = 1.dp, start = 16.dp, end = 16.dp, bottom = 1.dp)
                    .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.weight(1.0f))
    }
}