package com.aromano.cleankotlintodoapp.tasks

import com.aromano.cleankotlintodoapp.TestUseCaseScheduler
import com.aromano.cleankotlintodoapp.UseCaseHandler
import com.aromano.cleankotlintodoapp.data.Response
import com.aromano.cleankotlintodoapp.data.TasksRepository
import com.aromano.cleankotlintodoapp.domain.model.Task
import com.aromano.cleankotlintodoapp.domain.usecase.ActivateTask
import com.aromano.cleankotlintodoapp.domain.usecase.CompleteTask
import com.aromano.cleankotlintodoapp.domain.usecase.GetTasks
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class TasksPresenterTest {

    val stubTask = Task(1, "title", "description")

    val successList = listOf(
        Task(1, "1", "1"),
        Task(2, "2", "2"),
        Task(3, "3", "3"),
        Task(4, "4", "4")
    )

    val errorThrowable: Throwable = IllegalStateException("Error Throwable")

    lateinit var presenter: TasksPresenter

    @Mock
    lateinit var view: TasksContract.View

    @Mock
    lateinit var repository: TasksRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TasksPresenter(
            view,
            UseCaseHandler(TestUseCaseScheduler()),
            GetTasks(repository),
            CompleteTask(repository),
            ActivateTask(repository))
    }

    @Test
    fun `when view starts it should try loading tasks`() {
        whenever(repository.getTasks(false)).thenReturn(Response.Success(successList))

        presenter.start()

        verify(view).showLoading(true)
        verify(view).showTasks(successList)
        verify(view).showLoading(false)
    }

    @Test
    fun `when loading tasks gives an error it should display that error`() {
        whenever(repository.getTasks(false)).thenReturn(Response.Error(errorThrowable))
        presenter.start()

        verify(view).showLoading(true)
        verify(view).showError(errorThrowable.message)
        verify(view).showLoading(false)
    }

    @Test
    fun `when refreshing tasks it should display new tasks if it succeeds`() {
        whenever(repository.getTasks(true)).thenReturn(Response.Success(successList))
        presenter.refreshClicked()

        verify(view).showLoading(true)
        verify(view).showTasks(successList)
        verify(view).showLoading(false)
    }

    @Test
    fun `when refreshing tasks it should display error if it fails`() {
        whenever(repository.getTasks(true)).thenReturn(Response.Error(errorThrowable))
        presenter.refreshClicked()

        verify(view).showLoading(true)
        verify(view).showError(errorThrowable.message)
        verify(view).showLoading(false)
    }

    @Test
    fun `when addNewTask button is clicked it should show the AddNewTask view`() {
        presenter.addNewTaskClicked()

        verify(view).showAddNewTaskView()
    }

    @Test
    fun `after a new task has been added the view should add it to the list`() {
        presenter.onAddNewTaskResult(stubTask)

        verify(view).showNewlyAddedTask(stubTask)
    }

    @Test
    fun `when completing a task it should alter it's state in the repository`() {
        whenever(repository.completeTask(stubTask.id)).thenReturn(Response.Success(null))

        presenter.completeTask(stubTask)

        verify(repository).completeTask(stubTask.id)
    }

    @Test
    fun `when completing a task, if it gives an error, it should display that error`() {
        whenever(repository.completeTask(stubTask.id)).thenReturn(Response.Error(errorThrowable))

        presenter.completeTask(stubTask)

        verify(repository).completeTask(stubTask.id)
        verify(view).showCompleteTaskError(stubTask, errorThrowable.message)
    }

    @Test
    fun `when activating a task it should alter it's state in the repository`() {
        whenever(repository.activateTask(stubTask.id)).thenReturn(Response.Success(null))

        presenter.activateTask(stubTask)

        verify(repository).activateTask(stubTask.id)
    }

    @Test
    fun `when activating a task, if it gives an error, it should display that error`() {
        whenever(repository.activateTask(stubTask.id)).thenReturn(Response.Error(errorThrowable))

        presenter.activateTask(stubTask)

        verify(repository).activateTask(stubTask.id)
        verify(view).showActivateTaskError(stubTask, errorThrowable.message)
    }

    @Test
    fun `when a task is clicked it should display EditTask view`() {
        presenter.taskClicked(stubTask)

        verify(view).showEditTaskView(stubTask)
    }

    @Test
    fun `after a task has been edited, it should be replaced in the view`() {
        presenter.onEditTaskResult(stubTask)

        verify(view).replaceEditedTask(stubTask)
    }



}