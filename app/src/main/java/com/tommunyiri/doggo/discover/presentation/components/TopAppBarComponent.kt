package com.tommunyiri.doggo.discover.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tommunyiri.doggo.discover.R

/**
 * Created by Tom Munyiri on 01/03/2024.
 * Company: Eclectics International Ltd
 * Email: munyiri.thomas@eclectics.io
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    title: String,
    onBackButtonClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { onBackButtonClick.invoke() }) { // Handle back press
                Icon(
                    AppIcons.BackArrow,
                    contentDescription = stringResource(R.string.back_button),
                )
            }
        },
        actions = actions
    )
}
