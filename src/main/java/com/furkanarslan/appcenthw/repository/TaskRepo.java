package com.furkanarslan.appcenthw.repository;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findAllByOwner(AppUser user);

    Task findByIdAndOwnerId(Long todoId, Long OwnerId);
}
