package com.aromano.cleankotlintodoapp


class TestUseCaseScheduler : UseCaseScheduler {

    override fun execute(runnable: Runnable) {
        runnable.run()
    }

    override fun <V : UseCase.ResponseValue> notifyResponse(response: V, callback: UseCase.UseCaseCallback<V>) {
        callback.onSuccess(response)
    }

    override fun <V : UseCase.ResponseValue> onError(error: Throwable, callback: UseCase.UseCaseCallback<V>) {
        callback.onError(error)
    }
}