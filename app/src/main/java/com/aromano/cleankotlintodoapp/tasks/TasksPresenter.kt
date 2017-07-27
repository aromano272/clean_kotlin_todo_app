package com.aromano.cleankotlintodoapp.tasks

import com.aromano.cleankotlintodoapp.ui.base.BasePresenter


class TasksPresenter(view: TasksContract.View, val repository: TasksRepository) : BasePresenter<TasksContract.View>(view), TasksContract.Presenter {

    override fun start() {
        getView()?.showLoading(true)
        getView()?.showTasks(emptyList())
        getView()?.showLoading(false)
    }

    override fun refresh() {
        repository.invalidateCache()

        getView()?.showLoading(true)
        getView()?.showTasks(emptyList())
        getView()?.showLoading(false)
    }
}