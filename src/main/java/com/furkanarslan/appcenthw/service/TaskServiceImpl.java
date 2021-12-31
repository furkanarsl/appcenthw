package com.furkanarslan.appcenthw.service;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.Task;
import com.furkanarslan.appcenthw.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;

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
    public Task getTaskForUser(Long id, Long userId) {
        return taskRepo.findByIdAndOwnerId(id, userId);
    }

    @Override
    public void removeTask(Long id) {
        Task todo = this.getTask(id);
        taskRepo.delete(todo);
    }


    @Override
    public Task saveTask(Task todo) {
        return taskRepo.save(todo);
    }
}
