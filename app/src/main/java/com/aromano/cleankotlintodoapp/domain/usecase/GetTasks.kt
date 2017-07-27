package com.aromano.cleankotlintodoapp.domain.usecase

import com.aromano.cleankotlintodoapp.UseCase
import com.aromano.cleankotlintodoapp.tasks.TasksRepository
import com.aromano.cleankotlintodoapp.domain.model.Task


class GetTasks(
    val tasksRepository: TasksRepository
) : UseCase<GetTasks.RequestValues, GetTasks.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues) {
        val (res, err) = tasksRepository.getTasks(requestValues.forceUpdate)
        err?.let { callback.onError(it) }
        res?.let { callback.onSuccess(ResponseValue(it)) }
    }

    data class RequestValues(
        val forceUpdate: Boolean
    ) : UseCase.RequestValues

    data class ResponseValue(
        val tasks: List<Task>
    ) : UseCase.ResponseValue

}