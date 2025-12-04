package com.phillqins.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}