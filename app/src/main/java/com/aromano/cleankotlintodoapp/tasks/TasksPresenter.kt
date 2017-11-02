package com.aromano.cleankotlintodoapp.tasks

import com.aromano.cleankotlintodoapp.UseCase
import com.aromano.cleankotlintodoapp.UseCaseHandler
import com.aromano.cleankotlintodoapp.domain.model.Task
import com.aromano.cleankotlintodoapp.domain.usecase.ActivateTask
import com.aromano.cleankotlintodoapp.domain.usecase.GetTasks
import com.aromano.cleankotlintodoapp.domain.usecase.CompleteTask
import com.aromano.cleankotlintodoapp.ui.base.BasePresenter


class TasksPresenter(view: TasksContract.View,
                     private val useCaseHandler: UseCaseHandler,
                     private val getTasks: GetTasks,
                     private val completeTask: CompleteTask,
                     private val activateTask: ActivateTask
) : BasePresenter<TasksContract.View>(view), TasksContract.Presenter {

    override fun start() {
        getView()?.showLoading(true)

        useCaseHandler.execute(getTasks, GetTasks.RequestValues(false), object : UseCase.UseCaseCallback<GetTasks.ResponseValue> {
            override fun onSuccess(response: GetTasks.ResponseValue) {
                getView()?.showLoading(false)
                getView()?.showTasks(response.tasks)
            }

            override fun onError(error: Throwable) {
                getView()?.showLoading(false)
                getView()?.showError(error.message)
            }
        })
    }

    override fun refreshClicked() {
        getView()?.showLoading(true)

        useCaseHandler.execute(getTasks, GetTasks.RequestValues(true), object : UseCase.UseCaseCallback<GetTasks.ResponseValue> {
            override fun onSuccess(response: GetTasks.ResponseValue) {
                getView()?.showLoading(false)
                getView()?.showTasks(response.tasks)
            }

            override fun onError(error: Throwable) {
                getView()?.showLoading(false)
                getView()?.showError(error.message)
            }
        })
    }

    override fun addNewTaskClicked() {
        getView()?.showAddNewTaskView()
    }

    override fun onAddNewTaskResult(addedTask: Task) {
        getView()?.showNewlyAddedTask(addedTask)
    }

    override fun completeTask(task: Task) {
        useCaseHandler.execute(completeTask, CompleteTask.RequestValues(task.id), object : UseCase.UseCaseCallback<CompleteTask.ResponseValue> {
            override fun onSuccess(response: CompleteTask.ResponseValue) {

            }

            override fun onError(error: Throwable) {
                getView()?.showCompleteTaskError(task, error.message)
            }
        })
    }

    override fun activateTask(task: Task) {
        useCaseHandler.execute(activateTask, ActivateTask.RequestValues(task.id), object : UseCase.UseCaseCallback<ActivateTask.ResponseValue> {
            override fun onSuccess(response: ActivateTask.ResponseValue) {

            }

            override fun onError(error: Throwable) {
                getView()?.showActivateTaskError(task, error.message)
            }
        })
    }

    override fun taskClicked(task: Task) {
        getView()?.showEditTaskView(task)
    }

    override fun onEditTaskResult(task: Task) {
        getView()?.replaceEditedTask(task)
    }

}