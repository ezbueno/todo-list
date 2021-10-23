package com.buenoezandro.todo.service;

import com.buenoezandro.todo.dto.TaskDTO;
import com.buenoezandro.todo.mapper.TaskMapper;
import com.buenoezandro.todo.model.Task;
import com.buenoezandro.todo.repository.TaskRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private static final TaskMapper TASK_MAPPER = TaskMapper.INSTANCE;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) {
        taskDTO.setId(null);
        var taskToSave = TASK_MAPPER.toModel(taskDTO);
        var savedTask = this.taskRepository.save(taskToSave);
        return TASK_MAPPER.toDTO(savedTask);
    }

    @Transactional(readOnly = true)
    public List<TaskDTO> listAllTasks() {
        List<Task> tasks = this.taskRepository.findAll();
        return TASK_MAPPER.toDTO(tasks);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<TaskDTO> findTaskById(Long id) {
        return this.taskRepository
                .findById(id)
                .map(task -> ResponseEntity.ok(TASK_MAPPER.toDTO(task)))
                .orElse(ResponseEntity.notFound().build());
    }
}
