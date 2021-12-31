package com.furkanarslan.appcenthw.service;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoList;

import java.util.List;

public interface TodoListService {
    public TodoList saveTodoList(TodoList todoList);

    public TodoList getTodoList(Long id);

    public List<TodoList> getTodoLists();

    public List<TodoList> getTodoListsForUser(AppUser owner);

    public void removeTodoList(TodoList todoList);

}
