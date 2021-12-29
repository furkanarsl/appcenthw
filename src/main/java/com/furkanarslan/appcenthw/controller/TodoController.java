package com.furkanarslan.appcenthw.controller;

import com.furkanarslan.appcenthw.dto.TodoDto;
import com.furkanarslan.appcenthw.mapper.TodoMapper;
import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoTask;
import com.furkanarslan.appcenthw.service.TodoService;
import com.furkanarslan.appcenthw.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Todos")
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;
    private final UserService userService;
    private final TodoMapper todoMapper;

    @Operation(summary = "Get all todos", description = "Get all todos for the currently logged in user.")
    @ApiResponse(responseCode = "200", description = "All todos for the user.")
    @GetMapping("")
    public ResponseEntity<List<TodoDto>> getTodos(Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        List<TodoDto> todos = todoMapper.TodoTasksToTodoDtos(todoService.getTodosForUser(owner));
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @Operation(summary = "Get a todo detail.", description = "Get a todo detail for the currently logged in user.")
    @ApiResponse(responseCode = "200", description = "Todo with the given id if the user owns the content.")
    @ApiResponse(responseCode = "403", description = "If user doesn't own the content.", content = @Content)
    @ApiResponse(responseCode = "404", description = "Todo not found.", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id, Authentication authentication) throws AccessDeniedException, ResponseStatusException {
        AppUser owner = userService.getUser(authentication.getName());
        TodoTask todo = todoService.getTodo(id);
        checkTodoOwner(todo, owner);
        TodoDto todoDto = todoMapper.TodoTaskToTodoDto(todo);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @Operation(summary = "Create todo.", description = "Create a new todo and save it for the currently logged in user.")
    @ApiResponse(responseCode = "200", description = "Returns the newly created todo object.")
    @PostMapping("")
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todo, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        TodoTask todoTask = todoMapper.TodoDtoToTodoTask(todo);
        todoTask.setOwner(owner);
        todoService.saveTodo(todoTask);
        return new ResponseEntity<>(todoMapper.TodoTaskToTodoDto(todoTask), HttpStatus.CREATED);
    }

    @Operation(summary = "Update todo.", description = "Update details of a todo and save it for the currently logged in user.")
    @ApiResponse(responseCode = "200", description = "Returns the newly updated todo object.")
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, Authentication authentication, @RequestBody TodoDto todo) {
        AppUser owner = userService.getUser(authentication.getName());
        todo.setId(id);
        TodoTask currentTodo = todoService.getTodo(id);
        checkTodoOwner(currentTodo, owner);
        todoMapper.update(currentTodo, todo);
        return new ResponseEntity<>(todoMapper.TodoTaskToTodoDto(currentTodo), HttpStatus.OK);
    }

    @Operation(summary = "Delete todo.", description = "Delete a todo for the currently logged in user.")
    @ApiResponse(responseCode = "204", description = "Returns nothing with a status code of 204.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTodo(@PathVariable Long id, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        TodoTask todo = todoService.getTodo(id);
        checkTodoOwner(todo, owner);
        todoService.removeTodo(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    private void checkTodoOwner(TodoTask todo, AppUser owner) throws AccessDeniedException, ResponseStatusException {
        if (todo != null && !todo.getOwner().equals(owner)) {
            throw new AccessDeniedException("Unauthorized");
        } else if (todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
    }
}
