package com.tommunyiri.doggo.discover.presentation.components

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.tommunyiri.doggo.discover.BuildConfig
import com.tommunyiri.doggo.discover.R
import com.tommunyiri.doggo.discover.domain.model.DogInfo

@Composable
fun DogHomeCard(
    dogInfo: DogInfo,
    modifier: Modifier = Modifier,
    onDogClick: (DogInfo) -> Unit = {},
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onDogClick(dogInfo) }
                .testTag("DogItemCard"),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Box {
            val imageUrl = remember { mutableStateOf("${BuildConfig.DOG_IMAGE_URL}${dogInfo.referenceImageId}.jpg") }

            AsyncImage(
                model = imageUrl.value,
                contentDescription = dogInfo.name,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                onState = { state ->
                    if (state is AsyncImagePainter.State.Error && imageUrl.value.endsWith(".jpg")) {
                        imageUrl.value = "${BuildConfig.DOG_IMAGE_URL}${dogInfo.referenceImageId}.png"
                    }
                },
            )

            Row(
                modifier =
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = AppIcons.Pet,
                    contentDescription = stringResource(R.string.pet_icon),
                    tint = Color.White,
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    text = dogInfo.name,
                    color = Color.White,
                    style =
                        TextStyle(
                            fontSize = 16.sp,
                            shadow =
                                Shadow(
                                    color = Color.Black.copy(alpha = 4f),
                                    offset = Offset(4f, 4f),
                                    blurRadius = 4f,
                                ),
                        ),
                )
            }
        }
    }
}
