package com.phillqins.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.phillqins.core.database.dao.RunDao
import com.phillqins.core.database.dao.RunPendingSyncDao
import com.phillqins.core.database.entity.DeletedRunSyncEntity
import com.phillqins.core.database.entity.RunEntity
import com.phillqins.core.database.entity.RunPendingSyncEntity

@Database(
    entities = [
        RunEntity::class,
        RunPendingSyncEntity::class,
        DeletedRunSyncEntity::class
    ],
    version = 2
)
abstract class RunDatabase: RoomDatabase() {
    abstract val runDao: RunDao
    abstract val runPendingSyncDao: RunPendingSyncDao
}