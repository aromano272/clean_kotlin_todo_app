package com.aromano.cleankotlintodoapp

import android.os.Handler
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


class UseCaseThreadPoolScheduler : UseCaseScheduler {

    private val POOL_SIZE = 2
    private val MAX_POOL_SIZE = 4
    private val TIMEOUT = 30L

    private val handler = Handler()

    private val threadPoolExecutor = ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT, TimeUnit.SECONDS, ArrayBlockingQueue<Runnable>(POOL_SIZE))

    override fun execute(runnable: Runnable) {
        threadPoolExecutor.execute(runnable)
    }

    override fun <V : UseCase.ResponseValue> notifyResponse(response: V, callback: UseCase.UseCaseCallback<V>) {
        handler.post { callback.onSuccess(response) }
    }

    override fun <V : UseCase.ResponseValue> onError(error: Error, callback: UseCase.UseCaseCallback<V>) {
        handler.post { callback.onError(error) }
    }
}