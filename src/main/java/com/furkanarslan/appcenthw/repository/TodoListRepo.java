package com.furkanarslan.appcenthw.repository;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepo extends JpaRepository<TodoList, Long> {
    List<TodoList> findAllByOwner(AppUser user);

}
