package com.aromano.cleankotlintodoapp.data

import com.aromano.cleankotlintodoapp.domain.model.Task


class TasksRepository(private val local: TasksDataSource, private val remote: TasksDataSource) {

    private var cache: List<Task>? = null

    fun getTasks(forceReload: Boolean = false): Response<List<Task>> {
        if (forceReload) {
            val result = remote.getTasks()
            when (result) {
                is Response.Success -> {
                    cache = result.value
                    refreshLocalDataSource(result.value)
                }
                is Response.Error -> {
                    print(result.error)
                }
            }
            return result
        }

        cache?.let { return Response.Success(it) }

        val result = local.getTasks()
        when (result) {
            is Response.Success -> cache = result.value
        }
        return result
    }

    fun saveTask(task: Task): Throwable? {
        local.saveTask(task)?.let { return it }
        remote.saveTask(task)?.let { return it }
        return null
    }

    fun completeTask(id: Int): Response<Void?> {
        val remoteRes = remote.completeTask(id)

        return when (remoteRes) {
            is Response.Success -> local.completeTask(id)
            is Response.Error   -> remoteRes
        }
    }

    fun activateTask(id: Int): Response<Void?> {
        val remoteRes = remote.activateTask(id)

        return when (remoteRes) {
            is Response.Success -> local.activateTask(id)
            is Response.Error   -> remoteRes
        }
    }

    fun invalidateCache() {
        cache = null
    }

    private fun refreshLocalDataSource(tasks: List<Task>) {
        local.deleteAllTasks()
        tasks.forEach { local.saveTask(it) }
    }

}