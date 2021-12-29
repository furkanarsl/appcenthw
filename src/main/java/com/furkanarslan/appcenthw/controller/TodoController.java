package com.furkanarslan.appcenthw.controller;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoTask;
import com.furkanarslan.appcenthw.service.TodoService;
import com.furkanarslan.appcenthw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;
    private final UserService userService;

    @GetMapping("")
    public List<TodoTask> getTodos(Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        return todoService.getTodosForUser(owner);
    }

    @GetMapping("/{id}")
    public TodoTask getTodo(@PathVariable Long id, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        TodoTask todo = todoService.getTodo(id);
        if (todo != null && !todo.getOwner().equals(owner)) {
            throw new AccessDeniedException("Unauthorized");
        } else if (todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        } else {
            return todo;
        }
    }

    @PostMapping("")
    public TodoTask createTodo(@RequestBody TodoTask todo, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());
        todo.setOwner(owner);
        return todoService.saveTodo(todo);
    }


    @PutMapping("/{id}")
    public TodoTask updateTodo(@PathVariable Long id, Authentication authentication) {
        // TODO: Add updating the todo.
        AppUser owner = userService.getUser(authentication.getName());

        return this.todoService.getTodo(id);
    }

    @DeleteMapping("/{id}")
    public void removeTodo(@PathVariable Long id, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName());

        todoService.removeTodo(id);
    }
}
