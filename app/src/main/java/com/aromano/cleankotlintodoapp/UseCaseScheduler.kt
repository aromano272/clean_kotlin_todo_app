package com.aromano.cleankotlintodoapp


interface UseCaseScheduler {

    fun execute(runnable: Runnable)

    fun <V : UseCase.ResponseValue> notifyResponse(response: V, callback: UseCase.UseCaseCallback<V>)

    fun <V : UseCase.ResponseValue> onError(error: Error, callback: UseCase.UseCaseCallback<V>)

}