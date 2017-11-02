package com.aromano.cleankotlintodoapp.data.local

import com.aromano.cleankotlintodoapp.data.Response
import com.aromano.cleankotlintodoapp.domain.model.Task
import com.aromano.cleankotlintodoapp.data.TasksDataSource


class TasksRemoteDataSource : TasksDataSource {

    override fun getTasks(): Response<List<Task>> {
        return Response.Success(emptyList())
    }

    override fun saveTask(task: Task): Throwable? {
        return null
    }

    override fun deleteAllTasks(): Throwable? {
        return null
    }

    override fun completeTask(id: Int): Response<Void?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activateTask(id: Int): Response<Void?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}