package com.furkanarslan.appcenthw.controller;

import com.furkanarslan.appcenthw.model.AppUser;
import com.furkanarslan.appcenthw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class UserController {
    private final UserService userService;

    // TODO: Add endpoints about user.


}