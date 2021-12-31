package com.furkanarslan.appcenthw.service;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.Task;

import java.util.List;

public interface TaskService {
    public Task saveTask(Task task);

    public Task getTask(Long id);

    public List<Task> getTasks();

    public List<Task> getTasksForUser(AppUser user);

    public Task getTaskForUser(Long id, AppUser user);

    public Task updateTask(Task todo);

    public void removeTask(Long id);
}
