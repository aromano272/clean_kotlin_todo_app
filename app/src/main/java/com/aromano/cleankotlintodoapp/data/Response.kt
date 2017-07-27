package com.aromano.cleankotlintodoapp.data

data class Response<out S, out E>(val success: S?, val error: E?)
