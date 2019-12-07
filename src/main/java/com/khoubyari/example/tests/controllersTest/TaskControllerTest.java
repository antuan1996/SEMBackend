package com.khoubyari.example.tests.controllersTest;
import com.khoubyari.example.api.rest.TaskController;
import com.khoubyari.example.dao.jpa.TaskRepository;
import com.khoubyari.example.domain.Task;
import com.khoubyari.example.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {
    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskController taskController;


    @Test
    public void testGetTasksList() {
        Task task = new Task();
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(task);
        when(taskRepository.findAll()).thenReturn(tasks);
        ArrayList<Task> result = taskController.getTasks();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testCreateTask() {
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
        assertEquals((long) result.getId(), responseTask.getId());
        assertEquals((long) result.getCreatorId(), user.getId());
    }

    @Test
    public void testRetrieveTask() {
        long searchId = 10;
        Task responseTask = new Task();
        responseTask.setId(searchId);
        when(taskRepository.findOne(searchId)).thenReturn(responseTask);
        Task result = taskController.retrieveTask(searchId);
        assertEquals((long) result.getId(), searchId);
    }

    @Test
    public void testUpdateTask() {
        long searchId = 10;
        Task requestTask = new Task();
        requestTask.setCreatedDate(LocalDate.now());
        requestTask.setId(searchId);
        Task updatedTask = new Task();
        updatedTask.setId(searchId);
        updatedTask.setTitle("newTitle");
        when(taskRepository.findOne(searchId)).thenReturn(requestTask);
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);
        Task result = taskController.updateTasks(searchId, requestTask);
        assertEquals(result.getTitle(), updatedTask.getTitle());
    }

    @Test
    public void testDeleteTask() {
        long searchId = 10;
        Task updatedTask = new Task();
        updatedTask.setId(searchId);
        when(taskRepository.findOne(searchId)).thenReturn(updatedTask);
        taskController.deleteTasks(searchId);
        verify(taskRepository).delete(searchId);
    }
}