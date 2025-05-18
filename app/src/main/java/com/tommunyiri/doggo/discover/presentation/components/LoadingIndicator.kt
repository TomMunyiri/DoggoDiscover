package com.tommunyiri.doggo.discover.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tommunyiri.doggo.discover.R

@Composable
fun LoadingIndicator() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.loading_green_dog_lottie),
    )
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))

        LottieAnimation(
            composition = composition,
            modifier =
                Modifier
                    .size(150.dp)
                    .padding(1.dp)
                    .align(Alignment.CenterHorizontally),
            iterations = LottieConstants.IterateForever,
        )
        Text(
            text = stringResource(R.string.loading_text),
            modifier =
                Modifier
                    .padding(1.dp)
                    .align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.weight(1.0f))
    }
}