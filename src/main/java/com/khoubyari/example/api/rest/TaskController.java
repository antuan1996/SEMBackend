package com.khoubyari.example.api.rest;

import com.khoubyari.example.dao.jpa.TaskRepository;
import com.khoubyari.example.domain.Task;
import com.khoubyari.example.domain.User;
import com.khoubyari.example.exception.DataFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/tasks")
public class TaskController extends AbstractRestHandler {
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    Task createTask(@RequestBody Task task, Principal principal, @AuthenticationPrincipal UserDetails userDetails){
        task.setCreatedDate(LocalDate.now());
        task.setCreatorId(((User)userDetails).getId());
        return taskRepository.save(task);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<Task> getTasks(){
        return (ArrayList<Task>) taskRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Task retrieveTask(@PathVariable("id") Long id) {
        Task task = taskRepository.findOne(id);
        checkResourceFound(task);
        return task;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody Task updateTasks(@PathVariable("id") Long id, @RequestBody Task task){
        checkResourceFound(taskRepository.findOne(id));
        if (id != task.getId()) throw new DataFormatException("ID doesn't match!");
        Task oldTask = taskRepository.findOne(id);
        LocalDate oldDate = LocalDate.parse(oldTask.getCreatedDate());
        task.setCreatedDate(oldDate);
        task.setCreatorId(oldTask.getCreatorId());
        return taskRepository.save(task);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTasks(@PathVariable("id") Long id){
        checkResourceFound(taskRepository.findOne(id));
        taskRepository.delete(id);
    }
}
