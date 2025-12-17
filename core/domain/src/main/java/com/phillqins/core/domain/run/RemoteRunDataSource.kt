package com.phillqins.core.domain.run

import com.phillqins.core.domain.util.DataError
import com.phillqins.core.domain.util.EmptyResult
import com.phillqins.core.domain.util.Result

interface RemoteRunDataSource {

    suspend fun getRuns(): Result<List<Run>, DataError.Network>
    suspend fun postRun(run: Run, mapPicture: ByteArray): Result<Run, DataError.Network>
    suspend fun deleteRun(id: String): EmptyResult<DataError.Network>

}