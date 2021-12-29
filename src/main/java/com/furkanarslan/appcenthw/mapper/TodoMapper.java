package com.furkanarslan.appcenthw.mapper;

import com.furkanarslan.appcenthw.dto.TodoDto;
import com.furkanarslan.appcenthw.model.TodoTask;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    TodoDto TodoTaskToTodoDto(TodoTask todo);
    List<TodoDto> TodoTasksToTodoDtos(List<TodoTask> todos);
    void update(@MappingTarget TodoTask todo, TodoDto todoDto);
    TodoTask TodoDtoToTodoTask(TodoDto todo);
}
