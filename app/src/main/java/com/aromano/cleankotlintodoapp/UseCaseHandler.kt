package com.aromano.cleankotlintodoapp

// public constructor because we need to mock it during tests
class UseCaseHandler (val scheduler: UseCaseScheduler) {

    companion object {

        private var inst: UseCaseHandler? = null

        @Synchronized
        fun getInstance(): UseCaseHandler {
            if (inst == null) inst = UseCaseHandler(UseCaseThreadPoolScheduler())
            return inst!!
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
        error: Throwable, useCaseCallback: UseCase.UseCaseCallback<V>) {
        scheduler.onError(error, useCaseCallback)
    }

    private class UiCallbackWrapper<in V : UseCase.ResponseValue>(private val callback: UseCase.UseCaseCallback<V>,
                                                                  private val useCaseHandler: UseCaseHandler) : UseCase.UseCaseCallback<V> {

        override fun onSuccess(response: V) {
            useCaseHandler.notifyResponse(response, callback)
        }

        override fun onError(error: Throwable) {
            useCaseHandler.notifyError(error, callback)
        }
    }


}