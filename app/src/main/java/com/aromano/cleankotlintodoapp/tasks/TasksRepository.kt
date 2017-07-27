package com.aromano.cleankotlintodoapp.tasks

import com.aromano.cleankotlintodoapp.data.Response
import com.aromano.cleankotlintodoapp.domain.model.Task


open class TasksRepository(private val local: TasksDataSource, private val remote: TasksDataSource) {

    private var cache: List<Task>? = null

    fun getTasks(forceReload: Boolean = false): Response<List<Task>?, Error?> {

        if (forceReload) {
            val res = remote.getTasks()
            res.success?.let {
                cache = it
                refreshLocalDataSource(it)
            }

            return res
        }

        cache?.let { return Response(it, null) }

        val res = local.getTasks()
        cache = res.success
        return res
    }

    fun saveTask(task: Task): Error? {
        local.saveTask(task)?.let { return it }
        remote.saveTask(task)?.let { return it }
        return null
    }

    fun invalidateCache() {
        cache = null
    }

    private fun refreshLocalDataSource(tasks: List<Task>) {
        remote.deleteAllTasks()
        tasks.forEach { remote.saveTask(it) }
    }

}