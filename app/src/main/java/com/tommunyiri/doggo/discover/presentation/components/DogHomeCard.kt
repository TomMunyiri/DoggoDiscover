package com.tommunyiri.doggo.discover.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tommunyiri.doggo.discover.BuildConfig
import com.tommunyiri.doggo.discover.domain.model.DogInfo

@Composable
fun DogHomeCard(
    dogInfo: DogInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            AsyncImage(
                model = "${BuildConfig.DOG_IMAGE_URL}${dogInfo.referenceImageId}.jpg",
                contentDescription = "${dogInfo.name} image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = AppIcons.Pet,
                    contentDescription = "Pet icon",
                    tint = androidx.compose.ui.graphics.Color.White
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    text = dogInfo.name,
                    color = androidx.compose.ui.graphics.Color.White
                )
            }
        }
    }
}
