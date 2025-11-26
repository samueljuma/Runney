package com.phillqins.core.domain.util

sealed interface DataError: Error {

    enum class Network: DataError {
        REQUEST_TIMEOUT,
        UNAUTHORIZED,
        BAD_REQUEST,
        FORBIDDEN,
        NOT_FOUND,
        INTERNAL_SERVER_ERROR,
        CONFLICT,
        TOO_MANY_REQUESTS,
        SERVER_ERROR,
        PAYLOAD_TOO_LARGE,
        UNKNOWN,
        SERIALIZATION_ERROR,
    }

    enum class Local: DataError {
        UNKNOWN,
        IO_ERROR,
        DISK_FULL,
        OUT_OF_MEMORY,
    }

}