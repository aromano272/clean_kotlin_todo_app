package com.aromano.cleankotlintodoapp.data.local

import android.arch.persistence.room.*
import com.aromano.cleankotlintodoapp.domain.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * from tasks")
    fun getTasks(): List<Task>

    @Insert
    fun createTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

}