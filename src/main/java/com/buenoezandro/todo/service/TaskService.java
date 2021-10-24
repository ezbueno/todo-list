package com.buenoezandro.todo.service;

import com.buenoezandro.todo.dto.TaskDTO;
import com.buenoezandro.todo.exception.TaskNotFoundException;
import com.buenoezandro.todo.mapper.TaskMapper;
import com.buenoezandro.todo.model.Task;
import com.buenoezandro.todo.repository.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private static final TaskMapper TASK_MAPPER = TaskMapper.INSTANCE;

    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) {
        taskDTO.setId(null);
        var taskToSave = TASK_MAPPER.toModel(taskDTO);
        var savedTask = this.taskRepository.save(taskToSave);
        return TASK_MAPPER.toDTO(savedTask);
    }

    @Transactional(readOnly = true)
    public List<TaskDTO> listAllTasks() {
        return this.taskRepository
                .findAll()
                .stream()
                .map(TASK_MAPPER::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<TaskDTO> findTaskById(Long id) {
        var task = this.findByIdOrThrow(id);
        return ResponseEntity.ok(TASK_MAPPER.toDTO(task));
    }

    @Transactional
    public ResponseEntity<TaskDTO> updateTaskById(Long id, TaskDTO taskDTO) {
        var taskToUpdate = this.findByIdOrThrow(id);
        taskToUpdate.setTitle(taskDTO.getTitle());
        taskToUpdate.setDescription(taskDTO.getDescription());
        taskToUpdate.setDeadLine(taskDTO.getDeadLine());
        var updatedTask = this.taskRepository.save(taskToUpdate);
        return ResponseEntity.ok(TASK_MAPPER.toDTO(updatedTask));
    }

    @Transactional
    public ResponseEntity<TaskDTO> deleteTaskById(Long id) {
        var task = this.findByIdOrThrow(id);
        this.taskRepository.deleteById(task.getId());
        return ResponseEntity.noContent().build();
    }

    private Task findByIdOrThrow(Long id) {
        return this.taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }
}
