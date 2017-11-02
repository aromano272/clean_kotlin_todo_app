package com.aromano.cleankotlintodoapp

import org.mockito.Mockito

object Match {

    fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

}