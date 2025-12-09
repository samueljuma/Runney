package com.phillqins.run.location

import android.location.Location
import com.phillqins.core.domain.location.LocationWithAltitude

fun Location.toLocationWithAltitude(): LocationWithAltitude{
    return LocationWithAltitude(
        location = com.phillqins.core.domain.location.Location(
            lat = latitude,
            long = longitude
        ),
        altitude = altitude
    )
}