package com.furkanarslan.appcenthw.service;

import com.furkanarslan.appcenthw.model.TodoTask;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService{
    public TodoTask saveTodo(TodoTask todo);
    public List<TodoTask> getTodos();
}
