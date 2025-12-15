package com.phillqins.run.domain

import com.phillqins.core.domain.location.LocationWithAltitude
import kotlinx.coroutines.flow.Flow

interface LocationObserver {
    fun observerLocation(interval: Long): Flow<LocationWithAltitude>
}