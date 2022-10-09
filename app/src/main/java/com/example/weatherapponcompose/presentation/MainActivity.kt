package com.example.weatherapponcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapponcompose.domain.weather.WeatherInfo
import com.example.weatherapponcompose.presentation.components.WeatherCard
import com.example.weatherapponcompose.presentation.ui.theme.DarkBlue
import com.example.weatherapponcompose.presentation.ui.theme.DeepBlue
import com.example.weatherapponcompose.presentation.ui.theme.WeatherAppOnComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )


        setContent {
            WeatherAppOnComposeTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBlue)
                ) {
                    WeatherCard(state = viewModel.state, backgroundColor = DeepBlue)
                }

            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherAppOnComposeTheme {

    }
}