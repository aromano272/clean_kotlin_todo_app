package com.aromano.cleankotlintodoapp.tasks

import com.aromano.cleankotlintodoapp.data.Response
import com.aromano.cleankotlintodoapp.domain.model.Task


interface TasksDataSource {

    fun getTasks(): Response<List<Task>?, Error?>

    fun saveTask(task: Task): Error?

    fun deleteAllTasks(): Error?

}