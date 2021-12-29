package com.furkanarslan.appcenthw.service;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoTask;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService{
    public TodoTask saveTodo(TodoTask todo);
    public TodoTask getTodo(Long id);
    public List<TodoTask> getTodos();
    public List<TodoTask> getTodosForUser(AppUser user);
    public void removeTodo(Long id);
}
