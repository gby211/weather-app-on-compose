package com.example.weatherapponcompose.domain.location

import android.location.Location

interface LocationTracker {
    // костыль
    suspend fun getCurrentLocation(): Location?

}