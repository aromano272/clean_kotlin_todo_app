package com.aromano.cleankotlintodoapp.data

import com.aromano.cleankotlintodoapp.domain.model.Task
import java.util.*

sealed class Response<out T> {
    data class Success<out T>(val value: T): Response<T>()
    data class Error<out T>(val error: Throwable): Response<T>()
}