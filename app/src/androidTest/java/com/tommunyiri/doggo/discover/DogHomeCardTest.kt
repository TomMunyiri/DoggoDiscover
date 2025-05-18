package com.tommunyiri.doggo.discover

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.tommunyiri.doggo.discover.domain.model.DogInfo
import com.tommunyiri.doggo.discover.presentation.components.DogHomeCard
import org.junit.Rule
import org.junit.Test

class DogHomeCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDogHomeCard() {
        with(composeTestRule) {
            val dogInfo =
                DogInfo(
                    bredFor = "test",
                    breedGroup = "",
                    description = "",
                    imperialHeight = "12",
                    metricHeight = "13",
                    history = "",
                    id = 123,
                    lifeSpan = "12 years",
                    name = "German Shepherd",
                    origin = "Kenya",
                    referenceImageId = "HkC31gcNm",
                    temperament = "friendly",
                    imperialWeight = "12-14",
                    metricWeight = "13-16",
                )
            setContent {
                DogHomeCard(dogInfo = dogInfo)
            }
            // Assertions using tags
            onNodeWithTag("DogItemCard").assertExists()

            // Actions
            onNodeWithTag("DogItemCard").performClick()
        }
    }
}