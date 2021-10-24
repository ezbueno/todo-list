package com.buenoezandro.todo.controller;

import com.buenoezandro.todo.dto.TaskDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Api(value = "To-Do Management")
public interface TaskControllerDocs {

    @ApiOperation(value = "Task creation operation")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "The task was successfully created"),
        @ApiResponse(code = 500, message = "Task was not created")
    })
    TaskDTO createTask(TaskDTO taskDTO);

    @ApiOperation(value = "Fetch all tasks")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return all tasks"),})
    List<TaskDTO> getAllTasks();

    @ApiOperation(value = "Fetch task by id operation")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The task was successfully found"),
        @ApiResponse(code = 404, message = "Task not found")
    })
    ResponseEntity<TaskDTO> getTaskById(Long id);

    @ApiOperation(value = "Update task by id operation")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The task was successfully updated"),
        @ApiResponse(code = 404, message = "Task not found")
    })
    ResponseEntity<TaskDTO> updateTask(Long id, TaskDTO taskDTO);

    @ApiOperation(value = "Delete task by id operation")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "The task was successfully deleted"),
        @ApiResponse(code = 404, message = "Task not found")
    })
    ResponseEntity<TaskDTO> deleteTask(Long id);
}
