package com.khoubyari.example.tests;
import com.khoubyari.example.api.rest.TaskController;
import com.khoubyari.example.dao.jpa.TaskRepository;
import com.khoubyari.example.domain.Task;
import com.khoubyari.example.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RestAPITest
{
    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskController taskController;


    @Test
    public void testGetTasksList()
    {
        Task task = new Task();
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(task);
        when(taskRepository.findAll()).thenReturn(tasks);
        ArrayList<Task> result = taskController.getTasks();
        assertEquals(result.size(), 1);
    }

    @Test
    public void createTask() throws URISyntaxException
    {
        Task sendTask = new Task();
        sendTask.setTitle("title");
        sendTask.setDescription("description");
        User user = new User();
        user.setId(10);
        Task responseTask = new Task();
        responseTask.setId(120);
        responseTask.setTitle("title");
        responseTask.setDescription("description");
        responseTask.setCreatorId(user.getId());
        when(taskRepository.save(any(Task.class))).thenReturn(responseTask);
        Task result = taskController.createTask(sendTask, null, user);
        assertEquals((long)result.getId(), responseTask.getId());
        assertEquals((long)result.getCreatorId(), user.getId());
    }
}