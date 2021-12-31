package com.furkanarslan.appcenthw.service;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoList;
import com.furkanarslan.appcenthw.repository.TodoListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoListServiceImpl implements TodoListService {
    private final TodoListRepo todoListRepo;

    @Override
    public TodoList saveTodoList(TodoList todoList) {
        return todoListRepo.save(todoList);
    }

    @Override
    public TodoList getTodoList(Long id) {
        return todoListRepo.getById(id);
    }

    @Override
    public List<TodoList> getTodoLists() {
        return todoListRepo.findAll();
    }

    @Override
    public List<TodoList> getTodoListsForUser(AppUser owner) {
        return todoListRepo.findAllByOwner(owner);
    }

    @Override
    public void removeTodoList(TodoList todoList) {
        todoListRepo.delete(todoList);
    }
}
