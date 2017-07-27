package com.aromano.cleankotlintodoapp.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.aromano.cleankotlintodoapp.domain.model.Task

@Database(entities = arrayOf(Task::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

}