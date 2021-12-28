package com.furkanarslan.appcenthw.controller;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoTask;
import com.furkanarslan.appcenthw.service.TodoService;
import com.furkanarslan.appcenthw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;
    private final UserService userService;

    @PostMapping("")
    public TodoTask createTodo(@RequestBody TodoTask todo, Authentication authentication) {
        AppUser owner = userService.getUser(authentication.getName()) ;
        todo.setOwner(owner);
        return todoService.saveTodo(todo);
    }

    @GetMapping("")
    public List<TodoTask> getTodos(){
        return todoService.getTodos();
    }
}
