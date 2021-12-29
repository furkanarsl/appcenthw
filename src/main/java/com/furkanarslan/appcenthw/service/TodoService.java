package com.furkanarslan.appcenthw.service;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoTask;

import java.util.List;

public interface TodoService {
    public TodoTask saveTodo(TodoTask todo);

    public TodoTask getTodo(Long id);

    public List<TodoTask> getTodos();

    public List<TodoTask> getTodosForUser(AppUser user);

    public TodoTask getTodoForUser(Long id, Long userId);

    public TodoTask updateTodo(TodoTask todo);

    public void removeTodo(Long id);
}
