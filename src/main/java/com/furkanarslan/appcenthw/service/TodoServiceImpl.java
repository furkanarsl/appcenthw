package com.furkanarslan.appcenthw.service;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoTask;
import com.furkanarslan.appcenthw.repository.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepo todoRepo;

    @Override
    public List<TodoTask> getTodos() {
        return todoRepo.findAll();
    }

    @Override
    public TodoTask getTodo(Long id) {
        return todoRepo.findById(id).orElse(null);
    }

    @Override
    public List<TodoTask> getTodosForUser(AppUser user){
        return todoRepo.findAllByOwner(user);
    }
    @Override
    public void removeTodo(Long id) {
        TodoTask todo = this.getTodo(id);
        todoRepo.delete(todo);
    }

    @Override
    public TodoTask saveTodo(TodoTask todo) {
        return todoRepo.save(todo);
    }
}
