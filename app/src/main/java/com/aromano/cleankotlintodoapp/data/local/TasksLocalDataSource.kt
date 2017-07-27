package com.aromano.cleankotlintodoapp.data.local

import com.aromano.cleankotlintodoapp.data.Response
import com.aromano.cleankotlintodoapp.domain.model.Task
import com.aromano.cleankotlintodoapp.tasks.TasksDataSource


class TasksLocalDataSource(
    val db: AppDatabase
) : TasksDataSource {

    override fun getTasks(): Response<List<Task>?, Error?> {
        return Response(db.taskDao().getTasks(), null)
    }

    override fun saveTask(task: Task): Error? {
        return null
    }

    override fun deleteAllTasks(): Error? {
        return null
    }
}