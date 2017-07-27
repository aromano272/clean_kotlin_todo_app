package com.aromano.cleankotlintodoapp


abstract class UseCase<Q : UseCase.RequestValues, P : UseCase.ResponseValue> {

    // code shared between all request and response values
    interface RequestValues

    interface ResponseValue

    lateinit var requestValues: Q
    lateinit var responseValues: P
    lateinit var callback: UseCaseCallback<P>

    internal fun run() {
        executeUseCase(requestValues)
    }

    protected abstract fun executeUseCase(requestValues: Q)


    interface UseCaseCallback<in R> {
        fun onSuccess(response: R)
        fun onError(error: Error)
    }

}