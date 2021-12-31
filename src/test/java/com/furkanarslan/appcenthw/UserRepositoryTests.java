package com.furkanarslan.appcenthw;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepo userRepo;

    @Test
    public void testSaveUser(){
        AppUser appUser = new AppUser("Name","test","test");
        testEntityManager.persistAndFlush(appUser);
        assert(appUser.getId() != null);
    }

    @Test
    public void getCorrectUserByUsername(){
        AppUser appUser = new AppUser("Name","test","test");
        testEntityManager.persistAndFlush(appUser);
        assertThat("test").isEqualTo(userRepo.findByUsername("test").getUsername());
    }

    @Test
    public void getUserByUsernameWrongUsername(){
        AppUser appUser = new AppUser("Name","test","test");
        AppUser appUser2 = new AppUser("Name","abc","test");
        testEntityManager.persistAndFlush(appUser);
        testEntityManager.persistAndFlush(appUser2);
        assertThat("test").isNotEqualTo(userRepo.findByUsername("abc").getUsername());
    }
}
