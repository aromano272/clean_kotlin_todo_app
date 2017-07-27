package com.aromano.cleankotlintodoapp.data.local

import com.aromano.cleankotlintodoapp.data.Response
import com.aromano.cleankotlintodoapp.domain.model.Task
import com.aromano.cleankotlintodoapp.tasks.TasksDataSource


class TasksRemoteDataSource : TasksDataSource {

    override fun getTasks(): Response<List<Task>?, Error?> {
        return Response(emptyList(), null)
    }

    override fun saveTask(task: Task): Error? {
        return null
    }

    override fun deleteAllTasks(): Error? {
        return null
    }
}