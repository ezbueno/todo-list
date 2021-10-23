package com.buenoezandro.todo.controller;

import com.buenoezandro.todo.dto.TaskDTO;
import com.buenoezandro.todo.service.TaskService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class TaskController {
    
    private final TaskService taskService;
    
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @PostMapping(path = "/tasks")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return this.taskService.createTask(taskDTO);
    }
    
    @GetMapping(path = "/tasks")
    public List<TaskDTO> getAllTasks() {
        return this.taskService.listAllTasks();
    }
    
    @GetMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return this.taskService.findTaskById(id);
    }
}
