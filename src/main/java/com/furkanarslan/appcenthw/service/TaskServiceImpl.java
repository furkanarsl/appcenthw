package com.furkanarslan.appcenthw.service;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.Task;
import com.furkanarslan.appcenthw.model.TodoList;
import com.furkanarslan.appcenthw.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final TodoListService todoListService;

    @Override
    public List<Task> getTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTask(Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    @Override
    public Task updateTask(Task todo) {
        return taskRepo.save(todo);
    }

    @Override
    public List<Task> getTasksForUser(AppUser user) {
        return taskRepo.findAllByOwner(user);
    }

    @Override
    public Task getTaskForUser(Long id, AppUser user) {
        Task task = taskRepo.findByIdAndOwnerId(id, user.getId());
        checkTaskOwner(task, user);
        return task;
    }

    @Override
    public void removeTask(Long id) {
        Task todo = this.getTask(id);
        taskRepo.delete(todo);
    }

    @Override
    public Task saveTask(Task todo) {
        TodoList todolist = todoListService.getTodoList(todo.getList().getId());
        if(todo.getOwner() != todolist.getOwner()){
            throw new AccessDeniedException("Unauthorized");
        }
        return taskRepo.save(todo);
    }

    public void checkTaskOwner(Task task, AppUser owner) throws AccessDeniedException, ResponseStatusException {
        if (task != null && !task.getOwner().equals(owner)) {
            throw new AccessDeniedException("Unauthorized");
        } else if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
    }
}
