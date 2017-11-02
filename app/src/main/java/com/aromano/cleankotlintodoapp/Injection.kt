package com.aromano.cleankotlintodoapp

import com.aromano.cleankotlintodoapp.data.local.AppDatabase
import com.aromano.cleankotlintodoapp.data.local.TasksLocalDataSource
import com.aromano.cleankotlintodoapp.data.local.TasksRemoteDataSource
import com.aromano.cleankotlintodoapp.data.TasksRepository
import com.aromano.cleankotlintodoapp.domain.usecase.ActivateTask
import com.aromano.cleankotlintodoapp.domain.usecase.CompleteTask
import com.aromano.cleankotlintodoapp.domain.usecase.GetTasks


object Injection {

    fun provideDatabase(): AppDatabase {
        return App.db
    }

    fun provideTasksRepository(): TasksRepository {
        return TasksRepository(TasksLocalDataSource(provideDatabase()), TasksRemoteDataSource())
    }

    fun provideUseCaseHandler(): UseCaseHandler {
        return UseCaseHandler.getInstance()
    }

    fun provideGetTasks(repository: TasksRepository): GetTasks {
        return GetTasks(repository)
    }

    fun provideCompleteTask(repository: TasksRepository): CompleteTask {
        return CompleteTask(repository)
    }

    fun provideActivateTask(repository: TasksRepository): ActivateTask {
        return ActivateTask(repository)
    }

}