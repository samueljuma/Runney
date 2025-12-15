package com.phillqins.run.domain

import com.phillqins.core.domain.location.LocationTimeStamp
import com.phillqins.core.domain.location.LocationTimeStamps
import kotlin.time.Duration

data class RunData(
    val distanceMeters: Int = 0,
    val pace: Duration = Duration.ZERO,
    val locations: List<LocationTimeStamps> = emptyList()
)
