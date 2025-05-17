package com.tommunyiri.doggo.discover.presentation.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * Created by Tom Munyiri on 09/01/2025.
 * Company:
 * Email:
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBarComponent(title: String) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
    )
}
