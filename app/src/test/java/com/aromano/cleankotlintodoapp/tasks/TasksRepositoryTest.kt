package com.aromano.cleankotlintodoapp.tasks


import com.aromano.cleankotlintodoapp.Match
import com.aromano.cleankotlintodoapp.data.Response
import com.aromano.cleankotlintodoapp.data.TasksDataSource
import com.aromano.cleankotlintodoapp.data.TasksRepository
import com.aromano.cleankotlintodoapp.domain.model.Task
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.Assert.fail
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class TasksRepositoryTest {

    lateinit var repository: TasksRepository

    @Mock
    lateinit var local: TasksDataSource

    @Mock
    lateinit var remote: TasksDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = TasksRepository(local, remote)
    }

    val successList = listOf(
        Task(1, "1", "1"),
        Task(2, "2", "2"),
        Task(3, "3", "3"),
        Task(4, "4", "4")
    )

    val errorThrowable: Throwable = IllegalStateException("Error Throwable")

    val successRes = Response.Success(successList)

    val errorRes = Response.Error<List<Task>>(errorThrowable)

    @Test
    fun `getting tasks with forceload should not hit the local datastore`() {
        whenever(remote.getTasks()).thenReturn(successRes)
        repository.getTasks(true)

        verify(local, never()).getTasks()
    }

    @Test
    fun `getting tasks with forceload should hit the server and save the information on success`() {
        whenever(remote.getTasks()).thenReturn(successRes)
        repository.getTasks(true)

        verify(local).deleteAllTasks()
        verify(local, times(successList.size)).saveTask(Match.any())
        verify(remote).getTasks()
    }

    @Test
    fun `getting tasks with forceload should hit the server and return error on failure`() {
        whenever(remote.getTasks()).thenReturn(errorRes)
        val result = repository.getTasks(true)

        when (result) {
            is Response.Error -> assertThat(result.error, `is`(errorThrowable))
            else              -> fail("Expected: Response.Error() | Got: Response.Success()")
        }
    }



    //    @Test
    //    fun testGetTasksForceReload() {
    //        whenever(remote.getTasks()).thenReturn(successRes)
    //        repository.getTasks(true)
    //
    //        verify(local, never()).getTasks()
    //        verify(remote).getTasks()
    //    }
    //
    //    @Test
    //    fun testGetTasksValidCache() {
    //        repository.invalidateCache()
    //        repository.getTasks()
    //        reset(local, remote)
    //
    //        repository.getTasks()
    //
    //        verify(local, never()).getTasks()
    //        verify(remote, never()).getTasks()
    //    }
    //
    //    @Test
    //    fun testSaveTasksAfterGetTasks() {
    //        repository.invalidateCache()
    //        repository.getTasks()
    //    }


}
