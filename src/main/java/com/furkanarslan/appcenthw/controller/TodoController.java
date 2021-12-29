package com.furkanarslan.appcenthw.controller;

import com.furkanarslan.appcenthw.dto.TodoDto;
import com.furkanarslan.appcenthw.mapper.TodoMapper;
import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoTask;
import com.furkanarslan.appcenthw.service.TodoService;
import com.furkanarslan.appcenthw.service.UserService;
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
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;
    private final UserService userService;
    private final TodoMapper todoMapper;

    @GetMapping("")
    public ResponseEntity<List<TodoDto>> getTodos(Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        List<TodoDto> todos = todoMapper.TodoTasksToTodoDtos(todoService.getTodosForUser(owner));
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id, Authentication authentication) throws AccessDeniedException, ResponseStatusException {
        AppUser owner = userService.getUser(authentication.getName());
        TodoTask todo = todoService.getTodo(id);
        checkTodoOwner(todo, owner);
        TodoDto todoDto = todoMapper.TodoTaskToTodoDto(todo);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoTask todo, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        todo.setOwner(owner);
        todoService.saveTodo(todo);
        return new ResponseEntity<>(todoMapper.TodoTaskToTodoDto(todo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, Authentication authentication, @RequestBody TodoDto todo) {
        AppUser owner = userService.getUser(authentication.getName());
        todo.setId(id);
        TodoTask currentTodo = todoService.getTodo(id);
        checkTodoOwner(currentTodo, owner);
        todoMapper.update(currentTodo, todo);
        return new ResponseEntity<>(todoMapper.TodoTaskToTodoDto(currentTodo), HttpStatus.OK);
    }

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
