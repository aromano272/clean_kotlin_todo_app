package com.aromano.cleankotlintodoapp.tasks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.aromano.cleankotlintodoapp.Injection
import com.aromano.cleankotlintodoapp.R
import com.aromano.cleankotlintodoapp.domain.model.Task
import com.aromano.cleankotlintodoapp.ui.base.BaseMVPActivity

class TasksActivity : BaseMVPActivity<TasksContract.View>(), TasksContract.View {

    private lateinit var presenter: TasksPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        presenter = TasksPresenter(
            this,
            Injection.provideUseCaseHandler(),
            Injection.provideGetTasks(
                Injection.provideTasksRepository()
            ),
            Injection.provideCompleteTask(
                Injection.provideTasksRepository()
            ),
            Injection.provideActivateTask(
                Injection.provideTasksRepository()
            )
        )
        enableLifeCycleManagement(presenter)
    }

    override fun showCompleteTaskError(task: Task, message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEditTaskView(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replaceEditedTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showActivateTaskError(task: Task, message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTasks(tasks: List<Task>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAddNewTaskView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNewlyAddedTask(addedTask: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
