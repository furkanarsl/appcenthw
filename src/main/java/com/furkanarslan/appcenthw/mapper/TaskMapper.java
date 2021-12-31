package com.furkanarslan.appcenthw.mapper;

import com.furkanarslan.appcenthw.dto.TaskDto;
import com.furkanarslan.appcenthw.dto.TaskInDto;
import com.furkanarslan.appcenthw.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto TaskToTaskDto(Task task);

    List<TaskDto> TasksToTaskDtos(List<Task> tasks);

    void update(@MappingTarget Task task, TaskDto taskDto);

    Task TaskDtoToTask(TaskDto task);

    @Mapping(source = "list", target = "list")
    Task TaskInDtoToTask(TaskInDto task);
}
