package com.phillqins.run.domain

import com.phillqins.core.domain.location.LocationTimeStamps

object LocationDataCalculator {
    fun getTotalDistanceMeters(locations: List<LocationTimeStamps>): Int{
        return locations
            .sumOf { timeStampsPerLine->
                timeStampsPerLine.zipWithNext{location1, location2 ->
                    location1.location.location.distanceTo(location2.location.location)
                }.sum().toInt()
            }
    }
}