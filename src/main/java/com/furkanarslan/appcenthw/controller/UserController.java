package com.furkanarslan.appcenthw.controller;

import com.furkanarslan.appcenthw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class UserController {
    private final UserService userService;

    // TODO: Add endpoints about user.


}