package com.buenoezandro.todo.controller;

import com.buenoezandro.todo.dto.TaskDTO;
import com.buenoezandro.todo.service.TaskService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1")
public class TaskController implements TaskControllerDocs {

    private final TaskService taskService;

    @PostMapping(path = "/tasks")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        log.info("Saving new task [{}] to the database", taskDTO);
        return this.taskService.createTask(taskDTO);
    }

    @GetMapping(path = "/tasks")
    public List<TaskDTO> getAllTasks() {
        log.info("Fetching all tasks");
        return this.taskService.listAllTasks();
    }

    @GetMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        log.info("Fetching task with id [{}]", id);
        return this.taskService.findTaskById(id);
    }

    @PutMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        log.info("Updating task with id [{}] and showing updated task [{}]", id, taskDTO);
        return this.taskService.updateTaskById(id, taskDTO);
    }

    @DeleteMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id) {
        log.info("Deleting task with id [{}]", id);
        return this.taskService.deleteTaskById(id);
    }
}
