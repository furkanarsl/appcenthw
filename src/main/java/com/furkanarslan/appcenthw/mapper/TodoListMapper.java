package com.furkanarslan.appcenthw.mapper;

import com.furkanarslan.appcenthw.dto.TodoListInDto;
import com.furkanarslan.appcenthw.dto.TodoListOutDetailDto;
import com.furkanarslan.appcenthw.dto.TodoListOutDto;
import com.furkanarslan.appcenthw.dto.TodoListTaskInDto;
import com.furkanarslan.appcenthw.model.TodoList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoListMapper {

    TodoList TodoListTaskInDtoToTodoList(TodoListTaskInDto todoListTaskInDto);

    @Mapping(source = "id", target = "id")
    TodoListOutDto TodoListToTodoListOutDto(TodoList todoList);

    List<TodoListOutDto> TodoListsToTodoListOutDtos(List<TodoList> todoLists);

    @Mapping(source = "tasks", target = "tasks")
    TodoListOutDetailDto TodoListToTodoListOutDetailDto(TodoList todolist);

    TodoList TodoListInDtoToTodoList(TodoListInDto todoListInDto);

    @Mapping(source = "name", target = "name")
    void update(@MappingTarget TodoList todoList, TodoListInDto todoListInDto);

}
