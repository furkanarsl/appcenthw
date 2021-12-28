package com.furkanarslan.appcenthw.repository;

import com.furkanarslan.appcenthw.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String Username);
}