package com.aromano.cleankotlintodoapp.domain.usecase

import com.aromano.cleankotlintodoapp.UseCase
import com.aromano.cleankotlintodoapp.data.Response
import com.aromano.cleankotlintodoapp.data.TasksRepository

class ActivateTask(
    private val tasksRepository: TasksRepository
) : UseCase<ActivateTask.RequestValues, ActivateTask.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues) {
        val result = tasksRepository.activateTask(requestValues.taskId)
        when (result) {
            is Response.Success -> callback.onSuccess(ResponseValue())
            is Response.Error   -> callback.onError(result.error)
        }
    }

    data class RequestValues(
        val taskId: Int
    ) : UseCase.RequestValues

    class ResponseValue : UseCase.ResponseValue

}