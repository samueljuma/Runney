package com.phillqins.auth.domain

import com.phillqins.core.domain.util.DataError
import com.phillqins.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
}