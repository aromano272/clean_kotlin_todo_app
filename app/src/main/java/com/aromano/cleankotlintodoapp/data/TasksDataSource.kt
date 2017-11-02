package com.aromano.cleankotlintodoapp.data

import com.aromano.cleankotlintodoapp.domain.model.Task


interface TasksDataSource {

    fun getTasks(): Response<List<Task>>

    fun saveTask(task: Task): Throwable?

    fun deleteAllTasks(): Throwable?

    fun completeTask(id: Int): Response<Void?>

    fun activateTask(id: Int): Response<Void?>

}