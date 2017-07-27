package com.aromano.cleankotlintodoapp


class UseCaseHandler private constructor(val scheduler: UseCaseScheduler) {

    companion object {

        private lateinit var inst: UseCaseHandler

        fun getInstance(): UseCaseHandler {
            if (inst == null) inst = UseCaseHandler(UseCaseThreadPoolScheduler())
            return inst
        }

    }

    fun <T : UseCase.RequestValues, R : UseCase.ResponseValue> execute(useCase: UseCase<T, R>, values: T, callback: UseCase.UseCaseCallback<R>) {
        useCase.requestValues = values
        // this UiWrapper is used to send the response through the UI Thread, seeing that the UseCase
        // is running on a Background Thread by default, sending the response through it would cause
        // an exception if we tried to update the UI
        useCase.callback = UiCallbackWrapper(callback, this)

        scheduler.execute(Runnable {
            useCase.run()
        })
    }

    fun <V : UseCase.ResponseValue> notifyResponse(response: V, useCaseCallback: UseCase.UseCaseCallback<V>) {
        scheduler.notifyResponse(response, useCaseCallback)
    }

    private fun <V : UseCase.ResponseValue> notifyError(
        error: Error, useCaseCallback: UseCase.UseCaseCallback<V>) {
        scheduler.onError(error, useCaseCallback)
    }

    private class UiCallbackWrapper<in V : UseCase.ResponseValue>(private val callback: UseCase.UseCaseCallback<V>,
                                                                  private val useCaseHandler: UseCaseHandler) : UseCase.UseCaseCallback<V> {

        override fun onSuccess(response: V) {
            useCaseHandler.notifyResponse(response, callback)
        }

        override fun onError(error: Error) {
            useCaseHandler.notifyError(error, callback)
        }
    }


}