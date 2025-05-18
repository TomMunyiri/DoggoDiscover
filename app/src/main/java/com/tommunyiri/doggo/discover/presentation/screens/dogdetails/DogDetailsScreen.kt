package com.tommunyiri.doggo.discover.presentation.screens.dogdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.tommunyiri.doggo.discover.BuildConfig
import com.tommunyiri.doggo.discover.R
import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.presentation.components.AppIcons
import com.tommunyiri.doggo.discover.presentation.components.TopAppBarComponent

@Composable
fun DogDetailsScreen(
    dogInfo: DogInfo?,
    navController: NavHostController,
) {
    Scaffold(topBar = {
        TopAppBarComponent(
            stringResource(R.string.dog_details),
            onBackButtonClick = { navController.navigateUp() },
        )
    }) { paddingValues ->
        dogInfo?.let { dog ->
            Column(
                modifier =
                    Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
            ) {
                Box {
                    val imageUrl = remember { mutableStateOf("${BuildConfig.DOG_IMAGE_URL}${dog.referenceImageId}.jpg") }

                    AsyncImage(
                        model = imageUrl.value,
                        contentDescription = dog.name,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                        contentScale = ContentScale.Crop,
                        onState = { state ->
                            if (state is AsyncImagePainter.State.Error && imageUrl.value.endsWith(".jpg")) {
                                imageUrl.value = "${BuildConfig.DOG_IMAGE_URL}${dog.referenceImageId}.png"
                            }
                        },
                    )

                    // Title overlay at the bottom of the image
                    Box(
                        modifier =
                            Modifier
                                .align(Alignment.BottomStart)
                                .fillMaxWidth()
                                .background(Color.Black.copy(alpha = 0.5f))
                                .padding(16.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = AppIcons.Pet,
                                contentDescription = stringResource(R.string.pet_icon),
                                tint = Color.White,
                            )
                            Text(
                                text = dog.name,
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 8.dp),
                            )
                        }
                    }
                }

                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                ) {
                    InfoSection(
                        stringResource(R.string.weight),
                        "${dog.metricWeight} ${stringResource(R.string.kgs)} (${dog.imperialWeight}${
                            stringResource(R.string.pounds)
                        })",
                    )
                    InfoSection(
                        stringResource(R.string.height),
                        "${dog.metricHeight} ${stringResource(R.string.cms)} (${dog.imperialHeight} ${
                            stringResource(R.string.inches)
                        })",
                    )
                    InfoSection(stringResource(R.string.life_span), dog.lifeSpan)
                    InfoSection(
                        stringResource(R.string.origin),
                        dog.origin ?: stringResource(R.string.unknown),
                    )
                    InfoSection(
                        stringResource(R.string.bred_for),
                        dog.bredFor ?: stringResource(R.string.unknown),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.temperament),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = dog.temperament ?: stringResource(R.string.unknown),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        } ?: run {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(stringResource(R.string.info_unavailable))
            }
        }
    }
}

@Composable
private fun InfoSection(
    title: String,
    value: String,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}