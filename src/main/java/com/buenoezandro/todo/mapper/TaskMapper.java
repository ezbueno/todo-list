package com.buenoezandro.todo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.buenoezandro.todo.dto.TaskDTO;
import com.buenoezandro.todo.model.Task;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "deadLine", target = "deadLine")
    Task toModel(TaskDTO taskDTO);

    TaskDTO toDTO(Task task);

    List<TaskDTO> toDTO(List<Task> tasks);
}
