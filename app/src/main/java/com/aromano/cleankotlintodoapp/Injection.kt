package com.aromano.cleankotlintodoapp

import com.aromano.cleankotlintodoapp.data.local.AppDatabase
import com.aromano.cleankotlintodoapp.data.local.TasksLocalDataSource
import com.aromano.cleankotlintodoapp.data.local.TasksRemoteDataSource
import com.aromano.cleankotlintodoapp.tasks.TasksRepository


object Injection {

    fun provideDatabase(): AppDatabase {
        return App.db
    }

    fun provideTasksRepository(): TasksRepository {
        return TasksRepository(TasksLocalDataSource(provideDatabase()), TasksRemoteDataSource())
    }

}