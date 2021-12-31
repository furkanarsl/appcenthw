package com.furkanarslan.appcenthw;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.model.TodoList;
import com.furkanarslan.appcenthw.repository.TodoListRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TodoListRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TodoListRepo todoListRepo;

    @Test
    private void testTodoListCreate(){
        AppUser user = new AppUser("Name", "test", "test");
        testEntityManager.persist(user);
        TodoList todoList = new TodoList(null, "todolistname", user, null);
        testEntityManager.persist(todoList);
        assertThat(todoList.getId()).isNotNull();
    }

    @Test
    private void testTodoListOwnerValid(){
        AppUser user = new AppUser("Name", "test", "test");
        testEntityManager.persist(user);
        TodoList todoList = new TodoList(null, "todolistname", user, null);
        testEntityManager.persist(todoList);
        assert (todoList.getOwner() == user);
    }

    @Test
    private void testTodoListNameCorrect(){
        AppUser user = new AppUser("Name", "test", "test");
        testEntityManager.persist(user);
        TodoList todoList = new TodoList(null, "todolistname", user, null);
        testEntityManager.persist(todoList);
        assert (todoList.getName().equals("todolistname"));
    }


}
