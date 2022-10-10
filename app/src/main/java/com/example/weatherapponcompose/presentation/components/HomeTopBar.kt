package com.example.weatherapponcompose.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.ui.Modifier
import com.example.weatherapponcompose.presentation.ui.theme.DeepBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(title: String) {
    SmallTopAppBar(
        title = { Text(title) },
        modifier = Modifier.background(DeepBlue),
        )


}