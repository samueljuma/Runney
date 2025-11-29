package com.phillqins.auth.data

import com.phillqins.auth.domain.AuthRepository
import com.phillqins.core.data.networking.post
import com.phillqins.core.domain.util.DataError
import com.phillqins.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
): AuthRepository {
    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(email = email, password = password)
        )
    }
}