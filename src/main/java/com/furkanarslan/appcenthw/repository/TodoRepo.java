package com.furkanarslan.appcenthw.repository;

import com.furkanarslan.appcenthw.model.TodoTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<TodoTask, Long> {
}
