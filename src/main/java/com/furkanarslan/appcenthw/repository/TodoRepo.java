package com.furkanarslan.appcenthw.repository;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepo extends JpaRepository<TodoTask, Long> {
    List<TodoTask> findAllByOwner(AppUser user);
}
