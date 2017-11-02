package com.aromano.cleankotlintodoapp.tasks

import com.aromano.cleankotlintodoapp.domain.model.Task

interface TasksContract {

    interface View {

        fun showLoading(show: Boolean)

        fun showTasks(tasks: List<Task>)

        fun showError(message: String?)

        fun showAddNewTaskView()

        fun showNewlyAddedTask(addedTask: Task)

        fun showCompleteTaskError(task: Task, message: String?)

        fun showActivateTaskError(task: Task, message: String?)

        fun showEditTaskView(task: Task)

        fun replaceEditedTask(task: Task)

    }

    interface Presenter {

        fun start()

        fun refreshClicked()

        fun addNewTaskClicked()

        fun onAddNewTaskResult(addedTask: Task)

        fun completeTask(task: Task)

        fun taskClicked(task: Task)

        fun onEditTaskResult(task: Task)

        fun activateTask(task: Task)

    }

}

