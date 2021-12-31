package com.furkanarslan.appcenthw;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.Task;
import com.furkanarslan.appcenthw.model.TodoList;
import com.furkanarslan.appcenthw.repository.TaskRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;

@DataJpaTest
public class TaskRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TaskRepo taskRepo;


    @Test
    public void testSaveTask() {
        AppUser user = new AppUser("Name", "test", "test");
        testEntityManager.persist(user);
        TodoList todoList = new TodoList(null, "todolistname", user, null);
        testEntityManager.persist(todoList);
        Task task = new Task(null, "todo task", false, new Date(), 0L, user, todoList);
        testEntityManager.persistAndFlush(task);
        assert (task.getId() != null);
    }

    @Test
    public void TestTaskHasCorrectOwner() {
        AppUser user = new AppUser("Name", "test", "test");
        testEntityManager.persist(user);
        TodoList todoList = new TodoList(null, "todolistname", user, null);
        testEntityManager.persist(todoList);
        Task task = new Task(null, "todo task", false, new Date(), 0L, user, todoList);
        testEntityManager.persistAndFlush(task);
        assert (task.getOwner() == user);
    }


}
