package com.aromano.cleankotlintodoapp.tasks

import com.aromano.cleankotlintodoapp.domain.model.Task

interface TasksContract {

    interface View {

        fun showLoading(show: Boolean)

        fun showTasks(tasks: List<Task>)

    }

    interface Presenter {

        fun start()

        fun refresh();

    }

}

