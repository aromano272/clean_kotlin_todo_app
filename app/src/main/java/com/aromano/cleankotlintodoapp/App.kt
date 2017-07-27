package com.aromano.cleankotlintodoapp

import android.app.Application
import android.arch.persistence.room.Room
import com.aromano.cleankotlintodoapp.data.local.AppDatabase


class App : Application() {

    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        App.db = Room
            .databaseBuilder(applicationContext, AppDatabase::class.java, "CleanDB")
            .build()
    }
}