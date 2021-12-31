package com.furkanarslan.appcenthw.service;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoList;
import com.furkanarslan.appcenthw.repository.TodoListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (todoListRepo.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Todo list does not exists.");
        }
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
    public TodoList getTodoListForUser(Long id, AppUser owner) {
        TodoList todoList = this.getTodoList(id);
        checkTodoListOwner(todoList, owner);
        return todoList;
    }


    @Override
    public void removeTodoList(TodoList todoList) {
        todoListRepo.delete(todoList);
    }

    private void checkTodoListOwner(TodoList todoList, AppUser owner) {
        if (todoList != null && !todoList.getOwner().equals(owner)) {
            throw new AccessDeniedException("Unauthorized");
        } else if (todoList == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo list not found");
        }
    }
}
