package com.aromano.cleankotlintodoapp.tasks;


import com.aromano.cleankotlintodoapp.domain.model.Task;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class TasksPresenterTest {

    private TasksPresenter presenter;

    @Mock
    private TasksRepository repository;

    @Mock
    private TasksContract.View view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new TasksPresenter(view, repository);
    }

    @Test
    public void testStart() {
        presenter.start();

        verify(view).showLoading(true);
        verify(view).showTasks(new ArrayList<Task>(0));
        verify(view).showLoading(false);
    }

    @Test
    public void testRefresh() {
        presenter.refresh();

        verify(repository).invalidateCache();
        verify(view).showLoading(true);
        verify(view).showTasks(new ArrayList<Task>(0));
        verify(view).showLoading(false);
    }


}
