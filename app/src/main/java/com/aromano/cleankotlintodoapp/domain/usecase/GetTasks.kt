package com.aromano.cleankotlintodoapp.domain.usecase

import com.aromano.cleankotlintodoapp.UseCase
import com.aromano.cleankotlintodoapp.data.Response
import com.aromano.cleankotlintodoapp.data.TasksRepository
import com.aromano.cleankotlintodoapp.domain.model.Task


class GetTasks(
    private val tasksRepository: TasksRepository
) : UseCase<GetTasks.RequestValues, GetTasks.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues) {
        val result = tasksRepository.getTasks(requestValues.forceUpdate)
        when (result) {
            is Response.Success -> callback.onSuccess(ResponseValue(result.value))
            is Response.Error   -> callback.onError(result.error)
        }
    }

    data class RequestValues(
        val forceUpdate: Boolean
    ) : UseCase.RequestValues

    data class ResponseValue(
        val tasks: List<Task>
    ) : UseCase.ResponseValue

}