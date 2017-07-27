package com.aromano.cleankotlintodoapp.tasks


import com.aromano.cleankotlintodoapp.data.Response
import com.aromano.cleankotlintodoapp.domain.model.Task
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import org.mockito.Mockito.*

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

    val successRes = Response(listOf(
        Task(1, "1", "1"),
        Task(2, "2", "2"),
        Task(3, "3", "3"),
        Task(4, "4", "4")
    ), null)

    @Test
    fun testGetTasksForceReload() {
        `when`(remote.getTasks()).thenReturn(successRes)
        repository.getTasks(true)

        verify(local, never()).getTasks()
        verify(remote).getTasks()
    }

    @Test
    fun testGetTasksValidCache() {
        repository.invalidateCache()
        repository.getTasks()
        reset(local, remote)

        repository.getTasks()

        verify(local, never()).getTasks()
        verify(remote, never()).getTasks()
    }

    @Test
    fun testSaveTasksAfterGetTasks() {
        repository.invalidateCache()
        repository.getTasks()
    }


}
