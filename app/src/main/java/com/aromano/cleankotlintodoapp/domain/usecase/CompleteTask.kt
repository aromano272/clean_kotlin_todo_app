package com.aromano.cleankotlintodoapp.domain.usecase

import com.aromano.cleankotlintodoapp.UseCase
import com.aromano.cleankotlintodoapp.data.Response
import com.aromano.cleankotlintodoapp.data.TasksRepository
import com.aromano.cleankotlintodoapp.domain.model.Task

class CompleteTask(
    private val tasksRepository: TasksRepository
) : UseCase<CompleteTask.RequestValues, CompleteTask.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues) {
        val result = tasksRepository.completeTask(requestValues.taskId)
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