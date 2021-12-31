package com.furkanarslan.appcenthw.controller;

import com.furkanarslan.appcenthw.dto.TodoListInDto;
import com.furkanarslan.appcenthw.dto.TodoListOutDetailDto;
import com.furkanarslan.appcenthw.dto.TodoListOutDto;
import com.furkanarslan.appcenthw.mapper.TodoListMapper;
import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoList;
import com.furkanarslan.appcenthw.service.TodoListService;
import com.furkanarslan.appcenthw.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Todo Lists")
@RequestMapping("/todo_list")
public class TodoListController {
    private final TodoListService todoListService;
    private final TodoListMapper todoListMapper;
    private final UserService userService;


    @Operation(summary = "Get all todo lists.", description = "Get all todo lists for the currently logged in user.")
    @ApiResponse(responseCode = "200", description = "All todo lists for the user.")
    @GetMapping("")
    public ResponseEntity<List<TodoListOutDto>> getTodoLists(Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());

        List<TodoList> todoLists = todoListService.getTodoListsForUser(owner);

        return new ResponseEntity<>(todoListMapper.TodoListsToTodoListOutDtos(todoLists), HttpStatus.OK);
    }

    @Operation(summary = "Get a todo list detail.", description = "Get a todo list detail with all the tasks for the currently logged in user.")
    @ApiResponse(responseCode = "200", description = "Todo list with the given id if the user owns the content.")
    @ApiResponse(responseCode = "403", description = "If user doesn't own the content.", content = @Content)
    @ApiResponse(responseCode = "404", description = "Task not found.", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<TodoListOutDetailDto> getTodoListDetail(@PathVariable Long id, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        TodoList todoList = todoListService.getTodoListForUser(id, owner);
        return new ResponseEntity<>(todoListMapper.TodoListToTodoListOutDetailDto(todoList), HttpStatus.OK);
    }

    @Operation(summary = "Create a todo list.", description = "Create a new todo list and save it for the currently logged in user")
    @PostMapping("")
    public ResponseEntity<TodoListOutDetailDto> createTodoList(@RequestBody TodoListInDto todoListInDto, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        TodoList newTodoList = todoListMapper.TodoListInDtoToTodoList(todoListInDto);
        newTodoList.setOwner(owner);
        todoListService.saveTodoList(newTodoList);
        return new ResponseEntity<>(todoListMapper.TodoListToTodoListOutDetailDto(newTodoList), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete todo list.", description = "Delete a todo list for the currently logged in user.")
    @ApiResponse(responseCode = "204", description = "Returns nothing with a status code of 204.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTodoList(@PathVariable Long id, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        TodoList todoList = todoListService.getTodoListForUser(id, owner);
        todoListService.removeTodoList(todoList);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update a todo list.", description = "Update details of a todo list and save it for the currently logged in user")
    @PutMapping("/{id}")
    public ResponseEntity<TodoListOutDto> updateTodoList(@PathVariable Long id, @RequestBody TodoListInDto todoListInDto, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        todoListInDto.setId(id);
        TodoList currentTodoList = todoListService.getTodoListForUser(id, owner);
        todoListMapper.update(currentTodoList, todoListInDto);
        return new ResponseEntity<>(todoListMapper.TodoListToTodoListOutDto(currentTodoList), HttpStatus.OK);
    }
}
