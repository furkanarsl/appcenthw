package com.furkanarslan.appcenthw.service;

import com.furkanarslan.appcenthw.model.AppUser;

public interface UserService {
    AppUser saveUser(AppUser user);

    AppUser getUser(String username);

    void updateUsername(String username);

    void updatePassword(String password);

}